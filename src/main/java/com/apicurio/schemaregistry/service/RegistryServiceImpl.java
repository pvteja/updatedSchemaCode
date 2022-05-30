package com.apicurio.schemaregistry.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.apicurio.schemaregistry.domain.RegistryCreation;
import com.apicurio.schemaregistry.service.util.RegistryUtil;
import io.apicurio.registry.rest.client.RegistryClient;

@Service
public class RegistryServiceImpl implements RegistryService {
	
	
	@Autowired
	private RegistryClient registryClient;
	

	@Override
	public ResponseEntity<String> createSchemaRegistry(RegistryCreation registry) {
		
		String artifactId = UUID.randomUUID().toString();

        RegistryUtil.createSchemaInServiceRegistry(registryClient, artifactId, registry.getSchema());

        //Wait for the artifact to be available.
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        RegistryUtil.getSchemaFromRegistry(registryClient, artifactId);
		
		return ResponseEntity.ok().body("Success");
	}

}
