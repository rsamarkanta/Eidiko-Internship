package com.eidiko;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		log.debug("Application Started");
		log.debug("Main Method Started");
		SpringApplication.run(Application.class, args);

		log.debug("Main Method Ended");
		log.debug("Application is about to Complete");
	}

}
