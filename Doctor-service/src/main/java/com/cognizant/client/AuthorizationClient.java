package com.cognizant.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cognizant.entity.User;

@FeignClient(name = "authorization-service", url = "http://localhost:8090/auth")
public interface AuthorizationClient {

	@PostMapping(value = "/register")
	public ResponseEntity<String> saveUser(@RequestBody User user) throws Exception;
	
}
