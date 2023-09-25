package com.eidiko.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.eidiko.util.RegexUtility;

import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "USER")
@ToString
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;

	@NotEmpty(message = "username should not be empty !")
	@Column(name = "USERNAME", unique = true)
	private String username;

	@NotEmpty(message = "The password should contain at least one uppercase letter,"
			+ " one lowercase letter, and one number & greater than 8 characters.")
	private String password;

	@NotBlank(message = "Email should not be empty ")
	@Email(message = "Enter email properly !")
	private String email;

	@Column(name = "mobilNo")
	@NotNull(message = "Mobile No should be 10 digit only & starts with 6,7,8,9 !")
	private Long mobileNo;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {

		if (RegexUtility.isValidPassword(password))
			this.password = password;
		else
			System.out.println("Invalid Password !");
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(Long mobileNo) {

		if (RegexUtility.isMobileNoValid(mobileNo))
			this.mobileNo = mobileNo;
		else
			System.out.println("Invalid Mobile No !");
	}

	public User(Integer userId, String username, String password, String email,
			@NotNull(message = "Mobile No should not be empty ") Long mobileNo) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.email = email;
		this.mobileNo = mobileNo;
	}

}
