package com.cognizant.service;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cognizant.entity.User;
import com.cognizant.exception.UserAlredyExistsException;
import com.cognizant.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(userName);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + userName);
		}
		log.info("User found");
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
	}
	
	@Transactional
	public void save(User user) throws Exception{
		if(CheckIfUserAlreadyExists(user.getUsername())) {
			throw new UserAlredyExistsException("User with user name "+user.getUsername()+" already exists");
		}
		User newUser = new User();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setRole(user.getRole());
		newUser.setIdentity(user.getIdentity());
		userRepo.save(newUser);
		log.info("user successfully saved!");
	}
	
	public User getUserByName(String username) {
		log.info("User found with username {}", username);
		return userRepo.findByUsername(username);
	}
	
	public boolean CheckIfUserAlreadyExists(String username) {
		return userRepo.findAll().stream().anyMatch(u -> u.getUsername().equals(username));
	}
}
