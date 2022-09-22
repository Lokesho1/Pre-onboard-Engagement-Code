package com.cts.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
@CrossOrigin
class ApiConfiguration {
	@Autowired
	private GatewayFilter tokenValidationFilter;
	
	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("authorization-service",ps->ps.path("/auth/**")
						.uri("http://localhost:8081"))

				.route("customer-service",ps->ps.path("/patient/**")
						.filters(f->f.filter(tokenValidationFilter).removeRequestHeader("Cookies"))
						.uri("http://localhost:8082"))

				.route("property-service",ps->ps.path("/doctor/**")
				.filters(f->f.filter(tokenValidationFilter).removeRequestHeader("Cookies"))
				.uri("http://localhost:8083"))

				.route("manager-service",ps->ps.path("/checkup/**")
						.filters(f->f.filter(tokenValidationFilter).removeRequestHeader("Cookies"))
						.uri("http://localhost:8084"))
				
				.route("ui-service",ps->ps.path("/**")
				.uri("http://localhost:9000"))
				.build();
				
		}
	
		
		
	}
	
