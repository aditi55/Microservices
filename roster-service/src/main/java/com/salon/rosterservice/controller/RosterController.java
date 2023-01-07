package com.salon.rosterservice.controller;

import com.salon.rosterservice.dto.RosterResponse;
import com.salon.rosterservice.exceptions.StylistNameNotFoundException;
import com.salon.rosterservice.model.Roster;
import com.salon.rosterservice.service.RosterService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roster")
@RequiredArgsConstructor
public class RosterController {

    private final RosterService rosterService;

    //Fetches details of stylist
    @GetMapping("/find/{stylist-name}")
    public java.util.Optional<Roster> findByStylistName(@RequestBody @PathVariable("stylist-name") String stylistName) throws StylistNameNotFoundException {
        return rosterService.findByStylistName(stylistName);
    }

    //Displays available slots for stylist
	@GetMapping()
    @ResponseStatus(HttpStatus.OK)
	public List<RosterResponse> isAvailable (@RequestParam List<String> stylistName) {
        return rosterService.isAvailable(stylistName);
    }

}
