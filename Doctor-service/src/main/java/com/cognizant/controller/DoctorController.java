package com.cognizant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.client.AuthorizationClient;
import com.cognizant.entity.Doctor;
import com.cognizant.entity.User;
import com.cognizant.service.DoctorService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/doctor")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private AuthorizationClient authorizationClient;
	
	@PostMapping("/create")
	public ResponseEntity<String> add(@RequestBody Doctor doctor) throws Exception{
		log.info("inside add");
		Doctor d = doctorService.addDoctor(doctor);
		log.info("{}",d);
		String username=d.getName();
		String pass=d.getName()+d.getPhoneNo();
		User user=new User(username,pass,"doctor",d.getId());
		log.info("{}",user);
		authorizationClient.saveUser(user);
		return new ResponseEntity<String>("Doctor Added Successfully", HttpStatus.CREATED);
		
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<?> read(@PathVariable int id) throws Exception{
		log.info("Inside get {}",id);
		Doctor doctor=doctorService.getDoctor(id);
		log.info("Inside get {}",id);
		return new ResponseEntity<Doctor>(doctor,HttpStatus.OK);
	}

	@GetMapping("/getAllDoctors")
	public ResponseEntity<?> read() throws Exception{
		log.info("Inside getDoctors");
		List<Doctor> doctors= doctorService.getDoctor();
		return new ResponseEntity<List<Doctor>>(doctors,HttpStatus.OK);
		
	}
	
	@PostMapping("/update")
	public ResponseEntity<String> update(@RequestBody Doctor doctor) throws Exception{
		doctorService.updateDoctor(doctor);
		return new ResponseEntity<String>("Doctor Updated Successfully", HttpStatus.OK);

	}
	
	@GetMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable int id) throws Exception{
		doctorService.removeDoctor(id);
		return new ResponseEntity<String>("Doctor Deleted Successfully", HttpStatus.OK);

	}

//	@GetMapping("/assignPatient/{dId}/{Pid}")
//	public ResponseEntity<String> assignPatient(@PathVariable int dId,@PathVariable int pId) throws Exception{
//		doctorService.assignPatient(dId,pId);
//		return new ResponseEntity<String>("Doctor Deleted Successfully", HttpStatus.OK);
//		
//	}

	@GetMapping("{dId}/addPatient/{pId}")
	public ResponseEntity<String> assignPatient(@RequestHeader("Authorization") String token, @PathVariable int dId,@PathVariable int pId) throws Exception{
		log.info("inside assign");
		doctorService.assignPatient(token, dId,pId);
		return new ResponseEntity<String>("Doctor assigned Successfully", HttpStatus.OK);
		
	}

	@GetMapping("{dId}/removePatient/{pId}")
	public ResponseEntity<String> removePatient(@RequestHeader("Authorization") String token, @PathVariable int dId,@PathVariable int pId) throws Exception{
		log.info("inside remove");
		doctorService.removePatient(token, dId,pId);
		return new ResponseEntity<String>("Doctor assigned Successfully", HttpStatus.OK);
		
	}
	
	
}
