package com.dev.encurta_ai;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Tag(name = "URL Shortener", description = "Endpoints for URL shortening service")
@OpenAPIDefinition(
		info = @Info(
				title = "URL Shortener",
				version = "1.0",
				description = "API to shorten url",
				contact = @Contact(name = "Alan Araujo Rodrigues", email = "alanaraujotrs@gmail.com")
		)
)

@SpringBootApplication
public class EncurtaAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EncurtaAiApplication.class, args);
	}

}
