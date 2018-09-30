package com.gmail.ivanjermakov1.contactslist.service;

import com.gmail.ivanjermakov1.contactslist.entity.PhoneNumber;
import com.gmail.ivanjermakov1.contactslist.exception.InvalidPhoneNumberException;
import com.gmail.ivanjermakov1.contactslist.exception.NoSuchEntityException;
import com.gmail.ivanjermakov1.contactslist.repository.PhoneNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Set;

@Service
public class PhoneNumberService {
	
	private final PhoneNumberRepository phoneNumberRepository;
	
	@Autowired
	public PhoneNumberService(PhoneNumberRepository phoneNumberRepository) {
		this.phoneNumberRepository = phoneNumberRepository;
	}
	
	public void insert(PhoneNumber phoneNumber) throws InvalidPhoneNumberException, SQLException {
		if (!phoneNumber.valid()) throw new InvalidPhoneNumberException();
		phoneNumberRepository.insert(phoneNumber);
	}
	
	public void edit(PhoneNumber phoneNumber) throws SQLException, InvalidPhoneNumberException {
		if (!phoneNumber.valid()) throw new InvalidPhoneNumberException();
		phoneNumberRepository.edit(phoneNumber);
	}
	
	public void removeById(int id) throws SQLException {
		phoneNumberRepository.removeById(id);
	}
	
	public PhoneNumber select(int id) throws SQLException, NoSuchEntityException {
		return phoneNumberRepository.select(id);
	}
	
	public Set<PhoneNumber> selectByContactId(int contactId) throws SQLException {
		return phoneNumberRepository.selectByContactId(contactId);
	}
	
}
