package com.eidiko.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "EXCEL_EMPLOYEE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

	@Id
	private Long empId;
	private String empName;
	private Long salary;
	private String city;

}
