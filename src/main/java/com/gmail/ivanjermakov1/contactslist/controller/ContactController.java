package com.gmail.ivanjermakov1.contactslist.controller;

import com.gmail.ivanjermakov1.contactslist.entity.Contact;
import com.gmail.ivanjermakov1.contactslist.exception.InvalidContactException;
import com.gmail.ivanjermakov1.contactslist.exception.NoSuchEntityException;
import com.gmail.ivanjermakov1.contactslist.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@RestController
public class ContactController {
	
	private final ContactService contactService;
	
	@Autowired
	public ContactController(ContactService contactService) {
		this.contactService = contactService;
	}
	
	@RequestMapping("contact/init")
	@GetMapping
	public Contact init() {
		return new Contact();
	}
	
	@RequestMapping("contact/insert")
	@PostMapping
	public Integer insert(@RequestBody Contact contact) throws InvalidContactException, SQLException {
		return contactService.insert(contact);
	}
	
	@RequestMapping("contact/edit")
	@PostMapping
	public void edit(@RequestBody Contact contact) throws InvalidContactException, SQLException {
		contactService.edit(contact);
	}
	
	@RequestMapping("contact/selectAll")
	@GetMapping
	public Set<Contact> selectAll() throws SQLException {
		return contactService.selectAll();
	}
	
	@RequestMapping("contact/selectById")
	@GetMapping
	public Contact selectById(@RequestParam("id") int id) throws SQLException, NoSuchEntityException {
		return contactService.selectById(id);
	}
	
	@RequestMapping("contact/select")
	@GetMapping
	public Set<Contact> select(@RequestParam("amount") int amount, @RequestParam("offset") int offset) throws SQLException {
		return contactService.select(amount, offset);
	}
	
	@RequestMapping("contact/remove")
	@PostMapping
	public void remove(@RequestBody List<Integer> ids) throws SQLException {
		contactService.remove(ids);
	}
	
}
