package com.eidiko.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "LOGGING_EMPLOYEE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

	@Id
	@GeneratedValue
	private Long empId;
	private String empName;
	private LocalDate DoJ;
	private String Designation;
	private Long contactNo;
	private String about;
	private String status = "Active";
	private boolean isDeleted = false;

}
