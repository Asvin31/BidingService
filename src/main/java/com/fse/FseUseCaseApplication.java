package com.fse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@SpringBootApplication
public class FseUseCaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(FseUseCaseApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenApi() {
		return new OpenAPI().info(new Info().title("FSE Use Case").version("1")
				.description("Spring boot application for Fse Use Case").termsOfService("http://swagger.io/terms/")
				.license(new License().name("Developer").url("http://springdoc.org")));

	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://personal.dev.com:3000");
			}
		};
	}

}
