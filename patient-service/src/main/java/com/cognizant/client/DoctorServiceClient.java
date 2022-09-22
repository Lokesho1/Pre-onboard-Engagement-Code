package com.cognizant.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.entity.Doctor;

@FeignClient(name="doctor-service", url="http://localhost:8090/doctor")
public interface DoctorServiceClient {
//	@GetMapping("/get/{id}")
//	public Doctor read(@PathVariable int id) throws Exception;
	
	@GetMapping("{dId}/addPatient/{pId}")
	public ResponseEntity<String> assignPatient(@RequestHeader("Authorization") String token, @PathVariable int dId,@PathVariable int pId) throws Exception;

	@GetMapping("{dId}/removePatient/{pId}")
	public ResponseEntity<String> removePatient(@RequestHeader("Authorization") String token, @PathVariable int dId,@PathVariable int pId) throws Exception;

	
}
