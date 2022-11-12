package com.salon.salonservice.controller;
import com.salon.salonservice.dto.ServiceRequest;
import com.salon.salonservice.dto.ServiceResponse;
import com.salon.salonservice.service.SalonServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salonservice")
@RequiredArgsConstructor
public class SalonServiceController {
    private final SalonServiceService salonServiceService;

    @PostMapping("/add")
    public ResponseEntity addService(@RequestBody ServiceRequest serviceRequest){
        salonServiceService.addService(serviceRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<ServiceResponse> getAllSalonServices(){
        return salonServiceService.getAllSalonServices();
    }
}
