package com.salon.appointment.service;

import brave.Span;
import brave.Tracer;
import com.salon.appointment.dto.AppointmentRequest;
import com.salon.appointment.dto.AppointmentItemsDto;
import com.salon.appointment.dto.RosterResponse;
import com.salon.appointment.enums.StatusEnum;
import com.salon.appointment.exception.StylistNotFoundException;
import com.salon.appointment.model.Appointment;
import com.salon.appointment.model.AppointmentItems;
import com.salon.appointment.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final WebClient.Builder webClientBuilder;
    private final Tracer tracer;

    public String bookAppointment(AppointmentRequest appointmentRequest) {
        Appointment appointment = new Appointment();
        appointment.setAppointmentNumber((UUID.randomUUID().toString()));
        appointment.setApptStatus(StatusEnum.booked);
        List<AppointmentItems> appointmentItems = appointmentRequest.getAppointmentItemsDtoList()
                .stream()
                .map(appointmentItemsDto -> mapToDto(appointmentItemsDto))
                .toList();

        appointment.setAppointmentItemsList(appointmentItems);

        List<String> stylistNames = appointment.getAppointmentItemsList().stream().map(AppointmentItems::getStylistName).toList();

        Span rosterServiceLookup = tracer.nextSpan().name("RosterServiceLookup");
        try (Tracer.SpanInScope inScope = tracer.withSpanInScope(rosterServiceLookup.start())) {
            rosterServiceLookup.tag("call", "roster-service");
            RosterResponse[] rosterResponseArray = webClientBuilder.build().get()
                    .uri("http://roster-service/api/roster/", uriBuilder -> uriBuilder.queryParam("stylistName", stylistNames).build())
                    .retrieve()
                    .onStatus(
                            HttpStatus.INTERNAL_SERVER_ERROR::equals,
                            response -> response.bodyToMono(String.class).map(StylistNotFoundException::new))

                    .bodyToMono(RosterResponse[].class)
                    .block();
            boolean allStylistsAvailable = Arrays.stream(rosterResponseArray).allMatch(RosterResponse::isAvailable);
            if (allStylistsAvailable) {
                appointmentRepository.save(appointment);
                return "Appointment Booked Successfully";
            } else {
                throw new StylistNotFoundException("Stylist is not available at the moment, please select a different stylist.");
            }
        } finally
        {
            rosterServiceLookup.flush();
            }
        }
        private AppointmentItems mapToDto (AppointmentItemsDto appointmentItemsDto){
            AppointmentItems appointmentItems = new AppointmentItems();
            appointmentItems.setServiceName(appointmentItemsDto.getServiceName());
            appointmentItems.setPrice(appointmentItemsDto.getPrice());
            appointmentItems.setStylistName(appointmentItemsDto.getStylistName());
            appointmentItems.setSlotNumber(appointmentItemsDto.getSlotNumber());
            return appointmentItems;
        }
    }

