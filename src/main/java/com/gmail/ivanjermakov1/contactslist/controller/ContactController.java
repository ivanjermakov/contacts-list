package com.gmail.ivanjermakov1.contactslist.controller;

import com.gmail.ivanjermakov1.contactslist.entity.Contact;
import com.gmail.ivanjermakov1.contactslist.exception.InvalidContactException;
import com.gmail.ivanjermakov1.contactslist.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ContactController {
	
	private final ContactService contactService;
	
	@Autowired
	public ContactController(ContactService contactService) {
		this.contactService = contactService;
	}
	
	@RequestMapping("init")
	@GetMapping
	public Contact init() {
		return new Contact();
	}
	
	@RequestMapping("add")
	@PostMapping
	public boolean add(@RequestBody Contact contact) throws InvalidContactException {
		return contactService.add(contact);
	}
	
}
