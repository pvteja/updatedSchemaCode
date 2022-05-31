package com.apicurio.schemaregistry.service;

import java.io.InputStream;

import org.springframework.http.ResponseEntity;

import com.apicurio.schemaregistry.domain.RegistryCreation;
import com.apicurio.schemaregistry.domain.RegistryList;

import io.apicurio.registry.events.dto.ArtifactId;
import io.apicurio.registry.rest.v2.beans.ArtifactMetaData;
import io.apicurio.registry.rest.v2.beans.ArtifactSearchResults;

public interface RegistryService {
	
	public ResponseEntity<ArtifactMetaData> createSchemaRegistry(RegistryCreation registry);
	
	public ResponseEntity<String> getSchemaFromRegistry(String artifactId);
	
	public ResponseEntity<ArtifactSearchResults> listAllSchema();

}
