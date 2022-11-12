package com.salon.salonservice.repository;

import com.salon.salonservice.model.SalonService;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SalonServiceRepository extends MongoRepository<SalonService, String>{

}
