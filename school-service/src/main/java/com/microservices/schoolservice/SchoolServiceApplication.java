package com.microservices.schoolservice;

import com.netflix.discovery.shared.transport.jersey3.Jersey3TransportClientFactories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SchoolServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolServiceApplication.class, args);
	}

	//this is used for service registry correct function
	@Bean
	public Jersey3TransportClientFactories jersey3TransportClientFactories() {
		return new Jersey3TransportClientFactories();
	}
}
