package com.gmail.ivanjermakov1.contactslist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootConfig {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootConfig.class, args);
	}
	
}
