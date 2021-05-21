package com.aws.test.core;

import javax.persistence.PersistenceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoService {

	private DemoRepo demoRepo;
	private static final Logger logger = LoggerFactory.getLogger(DemoService.class);

	@Autowired
	public DemoService(DemoRepo demoRepo) {
		this.demoRepo = demoRepo;
	}

	public Model saveModel(ModelDTO modelDTO) {
		Model model = new Model();
		try {
			BeanUtils.copyProperties(modelDTO, model);
			return demoRepo.save(model);
		} catch (PersistenceException e) {
			logger.error("Error in saving the model : {}", e.getMessage());
		}
		return model;
	}

	public Model getModelByCode(String modelCode) {
		Model modelObj = demoRepo.getModelByCode(modelCode);
		if (modelObj == null) {
			logger.error("Error while retrieving the model : {}", modelCode);
		}
		return modelObj;
	}
}
