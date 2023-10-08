package com.eidiko.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailRequest {
	private String name;
	@NotBlank(message = "Email should not be empty ")
	@Email(message = "Enter email properly !")
	private String to;
	private String cc;
	@NotBlank(message = "Mail Subject should not be empty!")
	private String subject;
	@NotBlank(message = "Mail Body should not be empty!")
	private String mailBody;
	private MultipartFile attachment;

}
