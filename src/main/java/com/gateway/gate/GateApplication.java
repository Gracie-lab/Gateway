package com.gateway.gate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;

@SpringBootApplication
public class GateApplication {

	public static void main(String[] args) {
		SpringApplication.run(GateApplication.class, args);



	}

//	@Bean
//	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
//		return builder.routes()
//				.route("path_route", r -> r.path("/product").and().method("POST", "PUT", "DELETE")
//						.and().readBody(String.class, requestBody -> {return true;}).and().host("vendor.test-gateway-store.com")
//						.uri("http://localhost:8080"))
////				.route("path_route", r -> r.path("/product/**").and().method("GET")
////						.and().readBody(String.class, requestBody -> {return true;})
////						.filters(f -> f.addResponseHeader("Cache-Control", "max-age=300"))
////						.uri("http://localhost:8082"))
//				.build();
//	}
//
////	.readBody(String.class, i -> !StringUtils.isEmpty(i))


}
