package com.gmail.ivanjermakov1.contactslist.controller;

import com.gmail.ivanjermakov1.contactslist.entity.PhoneNumber;
import com.gmail.ivanjermakov1.contactslist.exception.InvalidPhoneNumberException;
import com.gmail.ivanjermakov1.contactslist.service.PhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PhoneNumberController {
	
	private final PhoneNumberService phoneNumberService;
	
	@Autowired
	public PhoneNumberController(PhoneNumberService phoneNumberService) {
		this.phoneNumberService = phoneNumberService;
	}
	
	
	@RequestMapping("init")
	@GetMapping
	public PhoneNumber init() {
		return new PhoneNumber();
	}
	
	@RequestMapping("add")
	@PostMapping
	public boolean add(PhoneNumber phoneNumber) throws InvalidPhoneNumberException {
		return phoneNumberService.add(phoneNumber);
	}
	
	
}
