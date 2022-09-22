package com.cts.filter;

import javax.security.sasl.AuthenticationException;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Slf4j
@Component
public class TokenValidationFilter implements GatewayFilter{
	private final WebClient.Builder builder;
	
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		ServerHttpRequest request=exchange.getRequest();
		log.info("hello");
		String jwt=request.getHeaders()
				.getOrEmpty("Authorization")
				.get(0)
				.substring(7);
		log.info("jwt = {}",jwt);
		return builder.baseUrl("http://localhost:8081/auth")
				.build()
				.get()
				.uri("/validate/"+jwt)
				.retrieve()
				.onStatus(HttpStatus.BAD_REQUEST::equals, cr->Mono.error(new AuthenticationException("Bad token")))
				.bodyToMono(String.class)
				.then(chain.filter(exchange));
		
	}

}
