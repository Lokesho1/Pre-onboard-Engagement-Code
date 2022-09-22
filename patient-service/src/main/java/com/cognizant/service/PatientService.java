package com.cognizant.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cognizant.entity.Doctor;
import com.cognizant.entity.Patient;
import com.cognizant.exception.NotFoundException;

@Service
public interface PatientService {
	
	public Patient createPatient(Patient patient);
	public void updatePatient(Patient patient)throws NotFoundException;
	public Patient getPatient(int id)throws NotFoundException;
	public List<Patient> getPatient()throws NotFoundException;
	public void deletePatient(int id) throws NotFoundException;
//	public Doctor getDoctorForPatient(int id)throws PatientNotFoundException;

}
