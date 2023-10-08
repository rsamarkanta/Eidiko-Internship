package com.eidiko.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "JWT_SHIFT_TABLE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShiftTime {
	@Id
	@GeneratedValue()
	private Long shiftId;
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalTime shiftStartTime;
	private LocalTime shiftEndTime;
	private Long modifyBy = getEmpId();
	private String weekOff;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, targetEntity = Employee.class)
	@JoinColumn(name = "empId")
	@JsonBackReference
	public Employee employee;

	@JsonProperty("empId")
	public Long getEmpId() {
		return employee != null ? employee.getEmpId() : null;
	}

}
