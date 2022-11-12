package com.salon.rosterservice.repository;

import com.salon.rosterservice.model.Roster;
import org.springframework.data.jpa.repository.JpaRepository;
import com.salon.rosterservice.exceptions.StylistNameNotFoundException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface RosterRepository extends JpaRepository<Roster, Long> {
    public Optional<Roster> findByStylistName(String stylistName);
    List<Roster> findByStylistNameIn(List<String> stylistName);

}
