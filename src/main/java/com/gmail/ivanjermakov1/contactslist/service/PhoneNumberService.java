package com.gmail.ivanjermakov1.contactslist.service;

import com.gmail.ivanjermakov1.contactslist.entity.PhoneNumber;
import com.gmail.ivanjermakov1.contactslist.exception.InvalidPhoneNumberException;
import com.gmail.ivanjermakov1.contactslist.repository.PhoneNumbersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneNumberService {
	
	private final PhoneNumbersRepository phoneNumbersRepository;
	
	@Autowired
	public PhoneNumberService(PhoneNumbersRepository phoneNumbersRepository) {
		this.phoneNumbersRepository = phoneNumbersRepository;
	}
	
	public boolean add(PhoneNumber phoneNumber) throws InvalidPhoneNumberException {
		if (phoneNumber.isValid()) {
			return phoneNumbersRepository.add(phoneNumber);
		}
		throw new InvalidPhoneNumberException();
	}
	
}
