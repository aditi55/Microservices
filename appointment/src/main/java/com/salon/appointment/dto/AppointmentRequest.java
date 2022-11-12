package com.salon.appointment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentRequest {
    private List<AppointmentItemsDto> appointmentItemsDtoList;
}
