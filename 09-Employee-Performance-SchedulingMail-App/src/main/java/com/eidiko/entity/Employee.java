package com.eidiko.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "SCHEDULED_EMPLOYEE")
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
	@Id
	private long empId;
	private String name;
	private String email;
	private LocalDate dateJoin;
	private String mobile;
	private String status; // active or inactive
	private boolean flag;
	private String about;

	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Skill> skillList;

	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@JsonManagedReference
	private List<Performance> performanceList;

}
