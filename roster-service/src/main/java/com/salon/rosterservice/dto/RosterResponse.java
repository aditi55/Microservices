package com.salon.rosterservice.dto;

import com.salon.rosterservice.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RosterResponse {
    private String stylistName;
    private boolean isAvailable;
    private Integer slotNumber;
    private StatusEnum status;

}
