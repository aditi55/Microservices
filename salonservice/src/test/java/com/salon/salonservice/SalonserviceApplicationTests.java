package com.salon.salonservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.salon.salonservice.dto.ServiceRequest;
import com.salon.salonservice.dto.ServiceResponse;
import com.salon.salonservice.repository.SalonServiceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class SalonserviceApplicationTests {
	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:5.0.10");
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private SalonServiceRepository salonServiceRepository;

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dymDynamicPropertyRegistry) {
		dymDynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}

	@Test
	void shouldaddService() throws Exception {
		ServiceRequest serviceRequest = getServiceRequest();
		String serviceRequestString = objectMapper.writeValueAsString(serviceRequest);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/salonservice/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(serviceRequestString))
				.andExpect(status().isCreated());
		Assertions.assertTrue(salonServiceRepository.findAll().size()==1);
	}

	private ServiceRequest getServiceRequest() {
		return ServiceRequest.builder()
				.name("Manicure")
				.description("Manicure")
				.price(BigDecimal.valueOf(2500))
				.build();
	}

}
