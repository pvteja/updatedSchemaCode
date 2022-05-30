package com.apicurio.schemaregistry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apicurio.schemaregistry.domain.RegistryCreation;
import com.apicurio.schemaregistry.service.RegistryService;

@RequestMapping
@RestController
public class RegistryController {

	@Autowired
	private RegistryService registryService;
	
	@PostMapping("/create-schema-registry")
	private ResponseEntity<String> createSchemaRegistry(RegistryCreation registryCreation){
		
		return registryService.createSchemaRegistry(registryCreation);
	}
	
}
