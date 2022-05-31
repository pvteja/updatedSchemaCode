package com.apicurio.schemaregistry.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegistryCreation {
	
	@JsonProperty("schema")
	private String schema;
	
	public RegistryCreation(String schema) {
		this.schema = schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public String getSchema() {
		return this.schema;
	}

}
