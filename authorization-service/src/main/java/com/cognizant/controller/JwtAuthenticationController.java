package com.cognizant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.entity.JwtRequest;
import com.cognizant.entity.JwtResponse;
import com.cognizant.entity.User;
import com.cognizant.exception.AuthorizationException;
import com.cognizant.service.JwtUserDetailsService;
import com.cognizant.util.JwtTokenUtil;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/auth")
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	
	@PostMapping(value = "/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
			throws AuthorizationException {
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		User u=userDetailsService.getUserByName(userDetails.getUsername());
		return ResponseEntity.ok(new JwtResponse(token,u));
	}

	private Authentication  authenticate(String username, String password) throws AuthorizationException {
		try {
			Authentication auth=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			return auth;
		} catch (DisabledException e) {
			throw new AuthorizationException("USER_DISABLED");
		} catch (BadCredentialsException e) {
			e.printStackTrace();
			throw new AuthorizationException("INVALID_CREDENTIALS");
		}
	}

	@PostMapping(value = "/register")
	public ResponseEntity<String> saveUser(@RequestBody User user) throws Exception {
		log.info("register {}",user);
		userDetailsService.save(user);
		System.out.println(user);
		return new ResponseEntity<String>("User Created Successfully!",HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/authorize")
	public boolean authorizeTheRequest(@RequestHeader(value = "Authorization", required = true) String requestTokenHeader) {
		String jwtToken = null;
		String userName = null;
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				userName = jwtTokenUtil.getUsernameFromToken(jwtToken);
				
			} catch (IllegalArgumentException | ExpiredJwtException e) {
				return false;
			}
		}
		return userName!=null;
	}
	
	@GetMapping(value = "/validate/{token}")
	public ResponseEntity<?> validate(@PathVariable String token) {
		
		if(jwtTokenUtil.validateToken(token))
			return ResponseEntity.ok().build();
		else
			return ResponseEntity.badRequest().build();
	}
	
}
