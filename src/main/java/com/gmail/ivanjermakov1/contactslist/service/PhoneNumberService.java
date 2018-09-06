package com.gmail.ivanjermakov1.contactslist.service;

import com.gmail.ivanjermakov1.contactslist.entity.PhoneNumber;
import com.gmail.ivanjermakov1.contactslist.exception.InvalidPhoneNumberException;
import com.gmail.ivanjermakov1.contactslist.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class PhoneNumberService {
	
	private final Repository<PhoneNumber> phoneNumberRepository;
	
	@Autowired
	public PhoneNumberService(Repository<PhoneNumber> phoneNumberRepository) {
		this.phoneNumberRepository = phoneNumberRepository;
	}
	
	public void add(PhoneNumber phoneNumber) throws InvalidPhoneNumberException, SQLException {
		if (!phoneNumber.valid()) throw new InvalidPhoneNumberException();
		phoneNumberRepository.add(phoneNumber);
	}
	
}
