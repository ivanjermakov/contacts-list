package com.gmail.ivanjermakov1.contactslist.service;

import com.gmail.ivanjermakov1.contactslist.entity.PhoneNumber;
import com.gmail.ivanjermakov1.contactslist.exception.InvalidPhoneNumberException;
import com.gmail.ivanjermakov1.contactslist.exception.NoSuchEntityException;
import com.gmail.ivanjermakov1.contactslist.repository.PhoneNumberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Set;

@Service
public class PhoneNumberService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private final PhoneNumberRepository phoneNumberRepository;
	
	@Autowired
	public PhoneNumberService(PhoneNumberRepository phoneNumberRepository) {
		this.phoneNumberRepository = phoneNumberRepository;
	}
	
	public void insert(PhoneNumber phoneNumber) throws InvalidPhoneNumberException, SQLException {
		logger.info("insert phone number: @" + phoneNumber.getId());
		if (!phoneNumber.valid()) throw new InvalidPhoneNumberException();
		phoneNumberRepository.insert(phoneNumber);
	}
	
	public void edit(PhoneNumber phoneNumber) throws SQLException, InvalidPhoneNumberException {
		logger.info("edit phone number: @" + phoneNumber.getId());
		if (!phoneNumber.valid()) throw new InvalidPhoneNumberException();
		phoneNumberRepository.edit(phoneNumber);
	}
	
	public void removeById(int id) throws SQLException {
		logger.info("remove phone number: @" + id);
		phoneNumberRepository.removeById(id);
	}
	
	public PhoneNumber select(int id) throws SQLException, NoSuchEntityException {
		logger.info("select phone number: @" + id);
		return phoneNumberRepository.select(id);
	}
	
	public Set<PhoneNumber> selectByContactId(int contactId) throws SQLException {
		logger.info("select phone number: of@" + contactId);
		return phoneNumberRepository.selectByContactId(contactId);
	}
	
}
