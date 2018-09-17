package com.gmail.ivanjermakov1.contactslist.service;

import com.gmail.ivanjermakov1.contactslist.entity.ContactMainInfo;
import com.gmail.ivanjermakov1.contactslist.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Set;

@Service
public class ContactMainInfoService {
	
	private final ContactRepository contactRepository;
	
	@Autowired
	public ContactMainInfoService(ContactRepository contactRepository) {
		this.contactRepository = contactRepository;
	}
	
	public Set<ContactMainInfo> selectMainInfo() throws SQLException {
		return contactRepository.selectMainInfo();
	}
	
}
