package com.eidiko.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "SCHEDULED_PERFORMANCE")
@NoArgsConstructor
@AllArgsConstructor
public class Performance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long performanceId;
	private int month;
	private int year;
	private String technology;
	private double techRating;
	private double communicationRating;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "empId")
	@JsonBackReference
	private Employee employee;

}