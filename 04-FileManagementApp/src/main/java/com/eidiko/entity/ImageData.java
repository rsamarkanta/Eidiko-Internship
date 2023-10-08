package com.eidiko.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "IMAGE_DATA")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ImageData {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String type;

	@Lob
	@Column(name = "imageData")
	private byte[] imageData;



}
