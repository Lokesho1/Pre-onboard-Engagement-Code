package com.cognizant.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.client.DoctorServiceClient;
import com.cognizant.entity.Doctor;
import com.cognizant.entity.Patient;
import com.cognizant.exception.NotFoundException;
import com.cognizant.repository.PatientRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PatientServiceImpl implements PatientService{

//	@Autowired
//	private DoctorServiceClient doctorServiceClient;
	
	@Autowired
	private PatientRepository patientRepository;
	
	@Transactional
	@Override
	public Patient createPatient(Patient patient)  {
		log.info("create patient service {}",patient);
		return patientRepository.save(patient);
//		log.info("created patient service {}",patient);
	}

	@Transactional
	@Override
	public void updatePatient(Patient patient) throws NotFoundException {
		try {
			patientRepository.findById(patient.getId()).get();
			patientRepository.save(patient);
		} catch (Exception e) {
			throw new NotFoundException("Patient Not Found");
		}
	}

	@Transactional
	@Override
	public Patient getPatient(int id) throws NotFoundException {
		try {
			log.info("inside getpatient");
			return patientRepository.findById(id).get();
		} catch (Exception e) {
			throw new NotFoundException("Patient Not Found");
		}
	}

	@Transactional
	@Override
	public void deletePatient(int id) throws NotFoundException {
		try {
			patientRepository.deleteById(id);
		} catch (Exception e) {
			throw new NotFoundException("Patient Not Found");
		}
	}


	@Override
	public List<Patient> getPatient() throws NotFoundException {
		try {
			return patientRepository.findAll();
		}catch (Exception e) {
			throw new NotFoundException("No Patients Available");
		}
	}

}
