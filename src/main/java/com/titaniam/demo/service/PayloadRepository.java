package com.titaniam.demo.service;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.titaniam.demo.model.Payload;

public interface PayloadRepository extends MongoRepository<Payload, String> {

}
