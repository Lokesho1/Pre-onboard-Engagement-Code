package com.cognizant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cognizant.client.AuthorizationClient;
import com.cognizant.client.DoctorServiceClient;
import com.cognizant.entity.Doctor;
import com.cognizant.entity.Patient;
import com.cognizant.entity.User;
import com.cognizant.exception.NotFoundException;
import com.cognizant.service.PatientService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@Slf4j
@Controller
@RequestMapping("/patient")
public class PatientController {
	
	@Autowired
	DoctorServiceClient doctorServiceClient;
	
	@Autowired
	AuthorizationClient authorizationClient;
	
	@Autowired
	private PatientService patientService;
	
	@PostMapping("/create")
	public ResponseEntity<String> createPatient(@RequestHeader("Authorization") String token, @RequestBody Patient patient) throws Exception{
		log.info("create Patient {}",patient);
		Patient p=patientService.createPatient(patient);
		log.info("created Patient {}",patient);
		try {
			doctorServiceClient.assignPatient(token, patient.getDoctor().getId(), patient.getId());
		} catch (Exception e) {
			log.info("{}",e);
			e.printStackTrace();
		}
		User  user=new User(p.getName(),p.getName()+p.getPhoneNo(),"patient",p.getId());
		authorizationClient.saveUser(user);
		return new ResponseEntity<String>("Patient Created Successfully",HttpStatus.OK);
	}
	
	@PostMapping("/update")
	public ResponseEntity<String> updatePatient(@RequestHeader("Authorization") String token, @RequestBody Patient patient) throws NotFoundException{
		log.info("inside udate {}",patient);
		patientService.updatePatient(patient);
		try {
			doctorServiceClient.assignPatient(token, patient.getDoctor().getId(), patient.getId());
		} catch (Exception e) {
			log.info("{}",e);
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Patient Updated Successfully",HttpStatus.OK);
	}
	
	@GetMapping("/getPatients")
	public ResponseEntity<?> getPatient()throws NotFoundException{
		log.info("h1");
		List<Patient> p= patientService.getPatient();
		log.info("{}",p);
		return new ResponseEntity<List<Patient>>(p,HttpStatus.OK);
	}

	@GetMapping("/getPatient/{id}")
	public ResponseEntity<Patient> getPatientById(@RequestHeader("Authorization") String token, @PathVariable int id)throws NotFoundException{
		log.info("hello");
		Patient patient= patientService.getPatient(id);
		log.info("{}",patient);
		return new ResponseEntity<Patient>(patient,HttpStatus.OK);
	}
	
	@GetMapping("/delete/{id}")
	public ResponseEntity<?> deletePatient(@RequestHeader("Authorization") String token, @PathVariable int id) throws Exception{
		log.info("inside delete patient");
		Patient p=patientService.getPatient(id);
		
		try {
			doctorServiceClient.removePatient(token, p.getDoctor().getId(), id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		patientService.deletePatient(id);
		return new ResponseEntity<String>("Patient Deleted Successfully",HttpStatus.OK);
	}
	
//	@GetMapping("getDoctor/{id}")
//	public Doctor getDoctor(@PathVariable int id)throws Exception {
//		return patientService.getDoctorForPatient(id);
//	}
}
