package com.salon.salonservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SalonserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalonserviceApplication.class, args);
	}

}
