package com.cognizant.service;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.entity.CheckUp;
import com.cognizant.repository.CheckUpRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CheckUpServiceImpl implements CheckUpService{

	@Autowired
	private CheckUpRepository checkUpRepository;
	
	@Transactional
	@Override
	public List<CheckUp> getCheckUpByPatientId(int pid) {
		List<CheckUp> checkUps = checkUpRepository.getCheckUpByPatientId(pid);
		log.info("inside  {}",checkUps);
		return checkUps;
	}

	@Transactional
	@Override
	public void add(CheckUp checkUp) {

		checkUpRepository.save(checkUp);
	}

}
