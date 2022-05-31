package com.apicurio.schemaregistry.controller;

import java.awt.PageAttributes.MediaType;
import java.io.InputStream;

import javax.ws.rs.Consumes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.apicurio.schemaregistry.domain.RegistryCreation;
import com.apicurio.schemaregistry.service.RegistryService;

import io.apicurio.registry.rest.v2.beans.ArtifactMetaData;
import io.apicurio.registry.rest.v2.beans.ArtifactSearchResults;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RequestMapping
@RestController
public class RegistryController {

	@Autowired
	private RegistryService registryService;
	
	@PostMapping(path="/create-schema-registry",consumes= {"application/json"})
	private ResponseEntity<ArtifactMetaData> createSchemaRegistry(@RequestBody RegistryCreation registry){
		System.out.println("Hellooooo"+registry.getSchema());
		return registryService.createSchemaRegistry(registry);
	}
	
	@GetMapping("/get-schema/{id}")
	private ResponseEntity<String> getSchemaFromRegistry(@PathVariable("id") String artifactId){
		
		return registryService.getSchemaFromRegistry(artifactId);
	}
	
	@GetMapping("/get-schema")
	private ResponseEntity<ArtifactSearchResults> listAllSchema(){
		return registryService.listAllSchema();
	}
}
