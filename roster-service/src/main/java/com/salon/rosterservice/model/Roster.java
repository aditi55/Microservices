package com.salon.rosterservice.model;

import com.salon.rosterservice.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "t_roster")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Roster {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String stylistName;
    private Integer slotNumber;
    private StatusEnum status;
}
