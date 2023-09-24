package com.eidiko.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "employee")
public class Employee {

	@Id
	@SequenceGenerator(name = "gen1", sequenceName = "emp_no_seq1", initialValue = 3000, allocationSize = 1)
	@GeneratedValue(generator = "gen1", strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private Float salary;
	private String designation;

}
