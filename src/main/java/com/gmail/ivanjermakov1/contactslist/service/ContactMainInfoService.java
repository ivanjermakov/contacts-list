package com.gmail.ivanjermakov1.contactslist.service;

import com.gmail.ivanjermakov1.contactslist.entity.ContactMainInfo;
import com.gmail.ivanjermakov1.contactslist.repository.ContactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Set;

@Service
public class ContactMainInfoService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private final ContactRepository contactRepository;
	
	@Autowired
	public ContactMainInfoService(ContactRepository contactRepository) {
		this.contactRepository = contactRepository;
	}
	
	public Set<ContactMainInfo> selectAllMainInfo() throws SQLException {
		logger.info("select all main info");
		return contactRepository.selectAllMainInfo();
	}
	
	public Set<ContactMainInfo> select(int amount, int offset) throws SQLException {
		logger.info("select all main info: amount: " + amount + " offset: " + offset);
		return contactRepository.selectMainInfo(amount, offset);
	}
	
}
