package com.cognizant.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cognizant.entity.CheckUp;

@Service
public interface CheckUpService {

	public List<CheckUp> getCheckUpByPatientId(int pid);

	public void add(CheckUp checkUp);
}
