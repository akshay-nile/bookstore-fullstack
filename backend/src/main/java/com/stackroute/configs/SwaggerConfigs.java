package com.stackroute.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfigs {

	private static final ApiInfo apiInfo = new ApiInfoBuilder()
			.title("Boostore APIs")
			.description("Spring boot assignment for practicing Swagger-2")
			.contact(new Contact("Akshay Nile", null, "akshay.nile@citiustech.com"))
			.build();

	@Bean
	public Docket getCustomSwaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.stackroute.controllers"))
				.build().apiInfo(apiInfo);
	}

}
