package com.cognizant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognizant.entity.CheckUp;

@Repository
public interface CheckUpRepository extends JpaRepository<CheckUp, Integer>{

	@Query("SELECT c FROM CheckUp c WHERE c.patientId = ?1")
	public List<CheckUp> getCheckUpByPatientId(int pid);
}
