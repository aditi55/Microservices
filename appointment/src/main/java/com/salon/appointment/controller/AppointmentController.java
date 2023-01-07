package com.salon.appointment.controller;

import com.salon.appointment.dto.AppointmentRequest;
import com.salon.appointment.service.AppointmentService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()//("/api/appointment")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

    @PostMapping("/book")
    @ResponseStatus(HttpStatus.CREATED)
    public String bookAppointment(@RequestBody AppointmentRequest appointmentRequest) {
        appointmentService.bookAppointment(appointmentRequest);
        return "Appointment Booked Successfully";
    }
}
