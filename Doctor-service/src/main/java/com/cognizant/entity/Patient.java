package com.cognizant.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Patient {

	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
    @Temporal(TemporalType.DATE)
	private Date admissionDate;
    private long phoneNo;
    private String Address;
//	@ManyToOne
//	@JoinColumn(name="doc_id")
//	private Doctor doctor;
	
}
