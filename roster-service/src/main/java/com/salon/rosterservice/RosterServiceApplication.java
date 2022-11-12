package com.salon.rosterservice;

import com.salon.rosterservice.enums.StatusEnum;
import com.salon.rosterservice.model.Roster;
import com.salon.rosterservice.repository.RosterRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class RosterServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RosterServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(RosterRepository rosterRepository){
		return args -> {
			Roster roster = new Roster();
			roster.setStylistName("Ram");
			roster.setSlotNumber(3);
			roster.setStatus(StatusEnum.booked);

			Roster roster1 = new Roster();
			roster1.setStylistName("Richa");
			roster1.setSlotNumber(1);
			roster1.setStatus(StatusEnum.available);

			Roster roster2 = new Roster();
			roster2.setStylistName("Riya");
			roster2.setSlotNumber(5);
			roster2.setStatus(StatusEnum.booked);

			Roster roster3 = new Roster();
			roster3.setStylistName("Ram");
			roster3.setSlotNumber(1);
			roster3.setStatus(StatusEnum.available);

			rosterRepository.save(roster);
			rosterRepository.save(roster1);
			rosterRepository.save(roster2);
			rosterRepository.save(roster3);
		};
	}
}
