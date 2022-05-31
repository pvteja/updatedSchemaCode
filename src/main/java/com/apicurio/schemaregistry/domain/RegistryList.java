package com.apicurio.schemaregistry.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class RegistryList {
	private List<RegistryCreation> registryList = new ArrayList<RegistryCreation>();


	
	
}
