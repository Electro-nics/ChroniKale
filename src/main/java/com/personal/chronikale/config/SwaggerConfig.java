package com.personal.chronikale.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
@Configuration

public class SwaggerConfig {
	@Bean
	public OpenAPI openAPI() {
		
		String schemeName="bearerScheme";
		
		return new OpenAPI()
				.addSecurityItem(new SecurityRequirement().addList(schemeName))
				.components(new Components().addSecuritySchemes(schemeName, new SecurityScheme()
						.name(schemeName).type(SecurityScheme.Type.HTTP)
						.bearerFormat("JWT")
						.scheme("bearer")
						))
				.info(new Info().title("ChroniKale Backend APIs")
						.description("This documentation includes public APIs and authorized APIs.")
						.version("v1/1.0.0")
						.contact(
								new Contact()
								.name("Goutam Chanda")
								.email("gautamchanda316@gmail.com")
								.url("https://electro-nics.github.io/goutam-chanda-portfolio/")
								
								)
						.license(new License()
								.name("Apache")
								)
						
						)
				.externalDocs(new ExternalDocumentation().
			              description("External Documantation")
			              .url("https://example.com"));
		
	}
	
	/*
	@Bean
	public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(apiInfo())
                .addServersItem(
                        new Server().url("http://localhost:8095").description("Local Server")
//                        new Server().url("https://api.example.com").description("Production Server")
                )
                .addSecurityItem(new SecurityRequirement().addList("BearerAuth"));
    }
	
	
	private Info apiInfo() {
        return new Info()
                .title("ChroniKale Backend APIs")
                .description("This documentation includes public APIs and authorized APIs.")
                .version("v1/1.0.0")
                .contact(new Contact()
                        .name("Goutam Chanda")
                        .email("gautamchanda316@gmail.com")
                        .url("https://electro-nics.github.io/goutam-chanda-portfolio/"));
    }
		*/
		
	}

