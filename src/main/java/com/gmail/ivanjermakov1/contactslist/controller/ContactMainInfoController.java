package com.gmail.ivanjermakov1.contactslist.controller;

import com.gmail.ivanjermakov1.contactslist.entity.ContactMainInfo;
import com.gmail.ivanjermakov1.contactslist.service.ContactMainInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Set;

@RestController
public class ContactMainInfoController {
	
	private final ContactMainInfoService contactMainInfoService;
	
	@Autowired
	public ContactMainInfoController(ContactMainInfoService contactMainInfoService) {
		this.contactMainInfoService = contactMainInfoService;
	}
	
	@RequestMapping("contactMainInfo/selectAll")
	@GetMapping
	public Set<ContactMainInfo> selectAll() throws SQLException {
		return contactMainInfoService.selectAllMainInfo();
	}
	
	@RequestMapping("contactMainInfo/select")
	@GetMapping
	public Set<ContactMainInfo> select(@RequestParam("amount") int amount, @RequestParam("offset") int offset) throws SQLException {
		return contactMainInfoService.select(amount, offset);
	}
	
	
}
