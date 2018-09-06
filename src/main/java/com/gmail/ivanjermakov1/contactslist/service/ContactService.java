package com.gmail.ivanjermakov1.contactslist.service;

import com.gmail.ivanjermakov1.contactslist.entity.Contact;
import com.gmail.ivanjermakov1.contactslist.repository.ContactsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {
	
	private final ContactsRepository contactsRepository;
	
	@Autowired
	public ContactService(ContactsRepository contactsRepository) {
		this.contactsRepository = contactsRepository;
	}
	
	public void add(Contact contact) {
		if (contact.isValid()) {
			contactsRepository.add(contact);
		}
	}
	
}
