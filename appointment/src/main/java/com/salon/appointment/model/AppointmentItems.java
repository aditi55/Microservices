package com.salon.appointment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "apptlist")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String serviceName;
    private BigDecimal price;
    private String stylistName;
    private Integer slotNumber;
}
