package com.salon.appointment.model;

import com.salon.appointment.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "appt")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String appointmentNumber;
    @OneToMany(cascade = CascadeType.ALL)
    private List<AppointmentItems> appointmentItemsList;
    private StatusEnum apptStatus;

}
