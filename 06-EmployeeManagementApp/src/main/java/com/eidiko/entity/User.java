package com.eidiko.entity;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Table(name = "JWT_USER")
public class User {
	@Id
	@GeneratedValue
	private Integer id;
	@Column(unique = true)
	private String empName;
	@Column(unique = true)
	private String username;
	private String password;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "jwtRolesTab", joinColumns = @JoinColumn(name = "fk_id"))
	private Set<String> roles;
}
