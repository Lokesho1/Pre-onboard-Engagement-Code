package com.cognizant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

}
