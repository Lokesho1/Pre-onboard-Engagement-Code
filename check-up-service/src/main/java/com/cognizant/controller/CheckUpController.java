package com.cognizant.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.entity.CheckUp;
import com.cognizant.service.CheckUpService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/checkup")
public class CheckUpController {

	@Autowired
	private CheckUpService checkUpService;
	
	@GetMapping("/getbypatient/{pid}")
	public ResponseEntity<?> getByPatient(@PathVariable int pid){
		List<CheckUp> checkUps= checkUpService.getCheckUpByPatientId(pid);
		log.info("list {}",checkUps);
		return new ResponseEntity<List<CheckUp>>(checkUps,HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<?> addCheckUp(@RequestBody CheckUp checkUp){
		log.info("{}",checkUp);
		checkUpService.add(checkUp);
		return new ResponseEntity<String>("checkup added successfully",HttpStatus.OK);
	}
}
