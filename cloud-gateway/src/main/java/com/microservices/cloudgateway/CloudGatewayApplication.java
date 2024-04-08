package com.microservices.cloudgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient

public class CloudGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudGatewayApplication.class, args);
	}
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("STUDENT-SERVICE", r -> r.path("/students/**")
						//.filters(f -> f.stripPrefix(1))
						.uri("lb://STUDENT-SERVICE"))
				.route("SCHOOL-SERVICE", r -> r.path("/schools/**")
						//.filters(f -> f.stripPrefix(1))
						.uri("lb://SCHOOL-SERVICE"))
				.route("COURSE-SERVICE", r -> r.path("/courses/**")
						//.filters(f -> f.stripPrefix(1))
						.uri("lb://COURSE-SERVICE"))
				.route("ENROLLMENT-SERVICE", r-> r.path("/enrollments/**")
						.uri("lb://ENROLLMENT-SERVICE"))
				.build();
	}
}
