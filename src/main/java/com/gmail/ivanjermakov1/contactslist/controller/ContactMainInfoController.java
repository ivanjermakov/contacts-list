package com.gmail.ivanjermakov1.contactslist.controller;

import com.gmail.ivanjermakov1.contactslist.entity.ContactMainInfo;
import com.gmail.ivanjermakov1.contactslist.service.ContactMainInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@RequestMapping("/contactMainInfo/select")
	@GetMapping
	public Set<ContactMainInfo> selectMainInfo() throws SQLException {
		return contactMainInfoService.selectMainInfo();
	}
	
}
