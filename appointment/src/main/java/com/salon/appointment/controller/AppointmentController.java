package com.salon.appointment.controller;

import com.salon.appointment.dto.AppointmentRequest;
import com.salon.appointment.service.AppointmentService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping()//("/api/appointment")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

    @PostMapping("/book")
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "roster", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "roster")
    @Retry(name = "roster")
    public CompletableFuture<String> bookAppointment(@RequestBody AppointmentRequest appointmentRequest) {
        return CompletableFuture.supplyAsync(()->appointmentService.bookAppointment(appointmentRequest));
    }

    public CompletableFuture<String> fallbackMethod(AppointmentRequest appointmentRequest, RuntimeException runtimeException){
        return CompletableFuture.supplyAsync(()->"Oops! Something went wrong, please book appointment after some time.");
    }
}
