package com.cognizant.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.client.PatientServiceClient;
import com.cognizant.entity.Doctor;
import com.cognizant.entity.Patient;
import com.cognizant.exception.NotFoundException;
import com.cognizant.repository.DoctorRepository;
import com.cognizant.repository.PatientRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	DoctorRepository doctorRepository;
	
	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	PatientServiceClient patientServiceClient;
	
	@Transactional
	@Override
	public Doctor addDoctor(Doctor doctor) {
		return doctorRepository.save(doctor);
	}

	@Transactional
	@Override
	public Doctor getDoctor(int id) throws Exception{
		log.info("get {}", id);
		try {
		Doctor doctor=doctorRepository.findById(id).get();
		return doctor;
		}catch (Exception e) {
			throw new NotFoundException("Doctor Not Found");
		}
	}

	@Transactional
	@Override
	public List<Doctor> getDoctor() throws Exception{
		try {
			return doctorRepository.findAll();
		}catch (Exception e) {
			throw new NotFoundException("No Doctors Found");
		}
	}
	
	@Transactional
	@Override
	public void updateDoctor(Doctor doctor) throws NotFoundException {

		try {
			Set<Patient> patients = doctorRepository.findById(doctor.getId()).get().getPatients();
			doctor.setPatients(patients);
			doctorRepository.save(doctor);
		}catch (Exception e) {
			throw new NotFoundException("Doctor Not Found");
		}
	}

	@Transactional
	@Override
	public void removeDoctor(int id) throws NotFoundException {
		try {
			doctorRepository.findById(id).get();
			doctorRepository.deleteById(id);
		}catch (Exception e) {
			throw new NotFoundException("Doctor Not Found");
		}
	}

	@Override
	public void assignPatient(String token, int dId, int pId) throws NotFoundException {
		try {
			Doctor doctor=doctorRepository.findById(dId).get();
			log.info("doctor {}",doctor);
			Patient patient=patientServiceClient.getPatientById(token, pId);
			patientRepository.save(patient);
			log.info("patient {} ",patient);
			log.info("patient befor {}",doctor.getPatients());
			doctor.getPatients().add(patient);
			log.info("patient after {}",doctor.getPatients());
			log.info("doctor after {}",doctor);
			doctorRepository.save(doctor);
		} catch (Exception e) {
			log.info("exception {}",e);
			throw new NotFoundException("Not Found");
		}
		
	}

	@Transactional
	@Override
	public void removePatient(String token, int dId, int pId) throws NotFoundException {
		Doctor doctor=doctorRepository.findById(dId).get();
		Patient patient=patientServiceClient.getPatientById(token, pId);
		log.info("befor remove {}",doctor);
//		Set<Patient> patients=doctor.getPatients();
//		Set<Patient> newPatients=new HashSet<>();
		doctor.setPatients(doctor.getPatients().stream().filter(p -> p.getId()!=pId).collect(Collectors.toSet()));
//		for(Patient p:patients) {
//			if(p.getId()!=pId) {
//				log.info("{}",
//						p);
//				newPatients.add(p);
//			}
//		}
		log.info("after remove {}",doctor);
//		doctor.setPatients(newPatients);
		
		doctorRepository.save(doctor);
		
		
	}

}
