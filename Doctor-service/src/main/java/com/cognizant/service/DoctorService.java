package com.cognizant.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cognizant.entity.Doctor;
import com.cognizant.exception.NotFoundException;

@Service
public interface DoctorService {

	public Doctor addDoctor(Doctor doctor);
	public Doctor getDoctor(int id) throws Exception;
	public List<Doctor> getDoctor() throws Exception;
	public void updateDoctor(Doctor doctor) throws NotFoundException;
	public void removeDoctor(int id) throws NotFoundException;
	public void assignPatient(String token,int dId, int pId) throws NotFoundException;
	public void removePatient(String token,int dId, int pId)throws NotFoundException;
}
