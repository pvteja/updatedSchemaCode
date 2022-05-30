package com.apicurio.schemaregistry.service;

import org.springframework.http.ResponseEntity;

import com.apicurio.schemaregistry.domain.RegistryCreation;

public interface RegistryService {
	
	public ResponseEntity<String> createSchemaRegistry(RegistryCreation registry);

}
