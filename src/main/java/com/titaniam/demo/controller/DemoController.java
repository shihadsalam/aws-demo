package com.titaniam.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.titaniam.demo.model.Payload;
import com.titaniam.demo.service.PayloadRepository;

@RestController
@RequestMapping("/api/v1/payload")
public class DemoController {

	@Autowired
	private PayloadRepository payloadRepository;
	
	@PostMapping
	public Payload savePayload(@RequestBody Payload payload) {
		return payloadRepository.save(payload);
	}
	
	@GetMapping("/{id}")
	public Payload getPayload(@PathVariable String id) {
		Optional<Payload> optPayload = payloadRepository.findById(id);
		if (optPayload.isPresent()) {
			return optPayload.get();
		}
		return null;
	}
	
}
