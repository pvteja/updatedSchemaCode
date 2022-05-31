package com.apicurio.schemaregistry.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpHeaders;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.apicurio.schemaregistry.domain.RegistryCreation;
import com.apicurio.schemaregistry.domain.RegistryList;
import com.apicurio.schemaregistry.service.util.RegistryUtil;

import io.apicurio.registry.events.dto.ArtifactId;
import io.apicurio.registry.rest.client.RegistryClient;
import io.apicurio.registry.rest.v2.beans.ArtifactMetaData;
import io.apicurio.registry.rest.v2.beans.ArtifactSearchResults;
import io.apicurio.registry.rest.v2.beans.IfExists;
import io.apicurio.registry.types.ArtifactType;

@Service
public class RegistryServiceImpl implements RegistryService {

	@Autowired
	private RegistryClient registryClient;

	@Override
	public ResponseEntity<ArtifactMetaData> createSchemaRegistry(RegistryCreation registry) {

		String artifactId = UUID.randomUUID().toString();
		
//		System.out.println("Hello====>" + registry.toString());
//		System.out.println("Hello====>" + registry.getSchema());
		// ArtifactMetaData result =
		// RegistryUtil.createSchemaInServiceRegistry(registryClient,
		// artifactId,schema);
		try {
			String schema=registry.getSchema();
			final ByteArrayInputStream content = new ByteArrayInputStream(schema.getBytes(StandardCharsets.UTF_8));
			final ArtifactMetaData metaData = registryClient.createArtifact("default", artifactId, ArtifactType.JSON,
					IfExists.RETURN, content);
			assert metaData != null;
			return new ResponseEntity<ArtifactMetaData>(metaData, HttpStatus.OK);
		
		} catch (Exception t) {
			throw t;
		}
		
//        //Wait for the artifact to be available.
//        try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		// return ResponseEntity.ok().body("Success");
	}

	@Override
	public ResponseEntity<String> getSchemaFromRegistry(String artifactId) {
		// ArtifactMetaData result = RegistryUtil.getSchemaFromRegistry(registryClient,
		// artifactId);
		System.out.println(artifactId);
		InputStream streamData = registryClient.getLatestArtifact("default", artifactId);
		String result = null;

		try {
			result = IOUtils.toString(streamData, StandardCharsets.UTF_8);
			System.out.println("HIIIIIII" + result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<String>(result, HttpStatus.OK);
		// String myString = IOUtils.toString(myInputStream, "UTF-8");

//		ByteArrayOutputStream result = new ByteArrayOutputStream();
//		 byte[] buffer = new byte[1024];
//		 for (int length; (length = streamData.read(buffer)) != -1; ) {
//		     result.write(buffer, 0, length);
//		 }
//		 // StandardCharsets.UTF_8.name() > JDK 7

		// return new ResponseEntity<ArtifactMetaData>(result, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ArtifactSearchResults> listAllSchema() {
		// TODO Auto-generated method stub
		ArtifactSearchResults result = registryClient.listArtifactsInGroup("default");
		return new ResponseEntity<ArtifactSearchResults>(result, HttpStatus.OK);
	}

}