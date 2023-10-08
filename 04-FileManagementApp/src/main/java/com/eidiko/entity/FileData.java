package com.eidiko.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "FILE_DATA")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileData {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String type;
	private String filePath;
}
