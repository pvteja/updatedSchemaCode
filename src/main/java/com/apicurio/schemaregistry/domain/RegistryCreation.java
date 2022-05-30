package com.apicurio.schemaregistry.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class RegistryCreation {
	
	private String schema;
	
	public void setSchema(String schema) {
		this.schema = schema;
	}

	public String getSchema() {
		return this.schema;
	}

}
