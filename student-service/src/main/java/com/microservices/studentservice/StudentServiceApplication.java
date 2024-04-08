package com.microservices.studentservice;

import com.netflix.discovery.shared.transport.jersey3.Jersey3TransportClientFactories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class StudentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentServiceApplication.class, args);
	}

	/**
	 * As in this example, the RestTemplate instance is annotated with @LoadBalanced,
	 * which means that it will use a load balancing algorithm to distribute requests to multiple instances of a service.
	 * To use this RestTemplate instance for making RESTful web service calls,
	 * you can simply autowire it into your component class and use it as needed.
	 * https://medium.com/ms-club-of-sliit/lets-build-a-microservice-with-spring-boot-faf39b968857
	 */

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
	@Bean
	public Jersey3TransportClientFactories jersey3TransportClientFactories() {
		return new Jersey3TransportClientFactories();
	}
}
