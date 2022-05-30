package com.apicurio.schemaregistry.service.util;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import io.apicurio.registry.rest.client.RegistryClient;
import io.apicurio.registry.rest.client.RegistryClientFactory;
import io.apicurio.rest.client.JdkHttpClientProvider;
import io.apicurio.rest.client.auth.OidcAuth;
import io.apicurio.rest.client.auth.exception.AuthErrorHandler;
import io.apicurio.rest.client.spi.ApicurioHttpClient;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableWebMvc
@Configuration
public class RegistryConfiguration {
	
	public static String registryUrl = "http://localhost:8090/apis/registry/v2";
	
	@Bean("registryClient")
	public static RegistryClient createProperClient() {
        RegistryClientFactory.setProvider(new JdkHttpClientProvider());

        final String tokenEndpoint = System.getenv("AUTH_TOKEN_ENDPOINT");
        if (tokenEndpoint != null) {
            final String authClient = System.getenv("AUTH_CLIENT_ID");
            final String authSecret = System.getenv("AUTH_CLIENT_SECRET");
            ApicurioHttpClient httpClient = new JdkHttpClientProvider().create(tokenEndpoint, Collections.emptyMap(), null, new AuthErrorHandler());
            return RegistryClientFactory.create(registryUrl, Collections.emptyMap(), new OidcAuth(httpClient, authClient, authSecret));
        } else {
            return RegistryClientFactory.create(registryUrl);
        }
    }
	
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.any())                          
          .build();                                           
    }

}
