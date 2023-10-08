package com.eidiko.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.eidiko.utility.RegexUtility;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "JWT_EMPLOYEEE")
public class Employee {

	@Id
	private Long empId;
	private String empName;
	private LocalDate DoJ;
	private String Designation;

	@Column(name = "contactNo")
	@NotNull(message = "Mobile No should be 10 digit only & starts with 6,7,8,9 !")
	private Long contactNo;
	private String about;
	private String status = "Active";
	private boolean isDeleted;

	@OneToMany(fetch = FetchType.EAGER, targetEntity = ShiftTime.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "empId", referencedColumnName = "empId")
	@JsonManagedReference
	private List<ShiftTime> shift;

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public LocalDate getDoJ() {
		return DoJ;
	}

	public void setDoJ(LocalDate doJ) {
		DoJ = doJ;
	}

	public String getDesignation() {
		return Designation;
	}

	public void setDesignation(String designation) {
		Designation = designation;
	}

	public Long getContactNo() {
		return contactNo;
	}

	public void setContactNo(Long contactNo) {

		if (RegexUtility.isMobileNoValid(contactNo))
			this.contactNo = contactNo;
		else
			System.out.println("Invalid Mobile No !");
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public List<ShiftTime> getShift() {
		return shift;
	}

	public void setShift(List<ShiftTime> shift) {
		this.shift = shift;
	}

	public Employee(Long empId, String empName, LocalDate doJ, String designation,
			@NotNull(message = "Mobile No should be 10 digit only & starts with 6,7,8,9 !") Long contactNo,
			String about, String status, boolean isDeleted, List<ShiftTime> shift) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.DoJ = doJ;
		this.Designation = designation;
		this.contactNo = contactNo;
		this.about = about;
		if (isDeleted) {
			this.status = "InActive";
		} else
			this.status = "Active";
		this.isDeleted = isDeleted;
		this.shift = shift;
	}

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", DoJ=" + DoJ + ", Designation=" + Designation
				+ ", contactNo=" + contactNo + ", about=" + about + ", status=" + status + ", isDeleted=" + isDeleted
				+ ", shift=" + shift + "]";
	}

}
