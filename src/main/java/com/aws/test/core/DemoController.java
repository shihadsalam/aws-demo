package com.aws.test.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling model functionality
 */
@RestController
@RequestMapping(value = "/v1/demo")
public class DemoController {

	private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

	private DemoService demoService;

	@Autowired
	public DemoController(DemoService demoService) {
		this.demoService = demoService;
	}

	@PostMapping
	public ResponseEntity<ModelDTO> createModel(@RequestBody ModelDTO modelDTO) {
		logger.info("Creating new model.. {}", modelDTO);
		try {
			demoService.saveModel(modelDTO);
			return new ResponseEntity<>(modelDTO, getMap("Model created successfully"), HttpStatus.CREATED);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	@GetMapping
	public ResponseEntity<Model> getModel(@RequestParam String code) {
		logger.info("Retrieving new model for the code.. {}", code);
		try {
			Model model = demoService.getModelByCode(code);
			return new ResponseEntity<>(model, getMap("Model retrieved successfully"), HttpStatus.FOUND);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	private MultiValueMap<String, String> getMap(String message) {
		MultiValueMap<String, String> map = new HttpHeaders();
		map.add("message", message);
		return map;
	}

}
