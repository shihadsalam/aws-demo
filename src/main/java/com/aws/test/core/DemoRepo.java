package com.aws.test.core;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ModelRepo interface.
 */
@Repository
public interface DemoRepo extends JpaRepository<Model, Integer> {

	public Model getModelByCode(String code);

}