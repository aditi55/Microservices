package com.salon.salonservice.service;

import com.salon.salonservice.dto.ServiceRequest;
import com.salon.salonservice.dto.ServiceResponse;
import com.salon.salonservice.model.SalonService;
import com.salon.salonservice.repository.SalonServiceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SalonServiceService {
    private final SalonServiceRepository salonServiceRepository;
    public void addService(ServiceRequest serviceRequest){
        SalonService salonService = SalonService.builder()
                .name(serviceRequest.getName())
                .description(serviceRequest.getDescription())
                .price(serviceRequest.getPrice())
                .build();

        salonServiceRepository.save(salonService);
        log.info("Service {} is saved", salonService.getId());
    }
    public List<ServiceResponse> getAllSalonServices(){
        List<SalonService> salonServices = salonServiceRepository.findAll();
        return salonServices.stream().map(this::mapToServiceResponse).toList();
    }
    private  ServiceResponse mapToServiceResponse(SalonService salonService){
        return ServiceResponse.builder()
                .id(salonService.getId())
                .name(salonService.getName())
                .description(salonService.getDescription())
                .price(salonService.getPrice())
                .build();
    }
}
