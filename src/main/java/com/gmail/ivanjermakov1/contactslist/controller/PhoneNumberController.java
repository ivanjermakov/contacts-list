package com.gmail.ivanjermakov1.contactslist.controller;

import com.gmail.ivanjermakov1.contactslist.entity.PhoneNumber;
import com.gmail.ivanjermakov1.contactslist.exception.InvalidPhoneNumberException;
import com.gmail.ivanjermakov1.contactslist.exception.NoSuchEntityException;
import com.gmail.ivanjermakov1.contactslist.service.PhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Set;

@RestController
public class PhoneNumberController {
	
	private final PhoneNumberService phoneNumberService;
	
	@Autowired
	public PhoneNumberController(PhoneNumberService phoneNumberService) {
		this.phoneNumberService = phoneNumberService;
	}
	
	@RequestMapping("number/init")
	@GetMapping
	public PhoneNumber init() {
		return new PhoneNumber();
	}
	
	@RequestMapping("number/insert")
	@PostMapping
	public void insert(@RequestBody PhoneNumber phoneNumber) throws InvalidPhoneNumberException, SQLException {
		phoneNumberService.insert(phoneNumber);
	}
	
	@RequestMapping("number/edit")
	@PostMapping
	public void edit(@RequestBody PhoneNumber phoneNumber) throws InvalidPhoneNumberException, SQLException {
		phoneNumberService.edit(phoneNumber);
	}
	
	@RequestMapping("number/select")
	@GetMapping
	public PhoneNumber select(@RequestParam("id") int id) throws SQLException, NoSuchEntityException {
		return phoneNumberService.select(id);
	}
	
	@RequestMapping("number/selectByContactId")
	@GetMapping
	public Set<PhoneNumber> selectByContactId(@RequestParam("id") int contactId) throws SQLException {
		return phoneNumberService.selectByContactId(contactId);
	}
	
	@RequestMapping("number/remove")
	@GetMapping
	public void removeById(@RequestParam("id") int id) throws SQLException {
		phoneNumberService.removeById(id);
	}
	
}
