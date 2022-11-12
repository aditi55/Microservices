package com.salon.appointment.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentItemsDto {
    private Long id;
    private String serviceName;
    private BigDecimal price;
    private String stylistName;
    private Integer slotNumber;
}
