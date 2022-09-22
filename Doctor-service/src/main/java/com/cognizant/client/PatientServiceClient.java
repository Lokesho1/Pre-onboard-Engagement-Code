package com.cognizant.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.entity.Patient;
import com.cognizant.exception.NotFoundException;

@FeignClient(name = "patient-service", url="http://localhost:8090/patient")
public interface PatientServiceClient {
	@GetMapping("/getPatient/{id}")
	Patient getPatientById(@RequestHeader("Authorization") String token, @PathVariable int id) throws NotFoundException;
//	@RequestHeader(value = "Authorization", required = true) String token,

}
