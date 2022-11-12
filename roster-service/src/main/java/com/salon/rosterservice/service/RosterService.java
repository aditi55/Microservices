package com.salon.rosterservice.service;

import com.salon.rosterservice.dto.RosterResponse;
import com.salon.rosterservice.enums.StatusEnum;
import com.salon.rosterservice.exceptions.StylistNameNotFoundException;
import com.salon.rosterservice.model.Roster;
import com.salon.rosterservice.repository.RosterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class RosterService {
    private final RosterRepository rosterRepository;
    public Optional<Roster> findByStylistName(String stylistName) {
        return rosterRepository.findByStylistName(stylistName);
    }
    @Transactional(readOnly = true)
    public List<RosterResponse> isAvailable(List<String> stylistName) {
        List<Roster> availableStylists = rosterRepository.findByStylistNameIn(stylistName)
                .stream()
                .filter(roster -> roster.getStatus().equals(StatusEnum.available))
                .toList();
            List<RosterResponse> result = availableStylists.stream()
                    .map(roster ->
                            RosterResponse.builder()
                                    .stylistName(roster.getStylistName())
                                    .isAvailable(roster.getStatus().equals(StatusEnum.available))
                                    .slotNumber(roster.getSlotNumber())
                                    .status(roster.getStatus())
                                    .build()
                    ).toList();
        if(!result.isEmpty()) {
            return  result;
        }
        else
            throw new StylistNameNotFoundException("Stylist Booked Already, please select a different stylist");
    }
}

