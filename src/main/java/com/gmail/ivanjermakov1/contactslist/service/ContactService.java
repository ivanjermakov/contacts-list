package com.gmail.ivanjermakov1.contactslist.service;

import com.gmail.ivanjermakov1.contactslist.entity.Contact;
import com.gmail.ivanjermakov1.contactslist.exception.InvalidContactException;
import com.gmail.ivanjermakov1.contactslist.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Set;

@Service
public class ContactService {
	
	private final ContactRepository contactRepository;
	
	@Autowired
	public ContactService(ContactRepository contactRepository) {
		this.contactRepository = contactRepository;
	}
	
	public void insert(Contact contact) throws InvalidContactException, SQLException {
		if (!contact.valid()) throw new InvalidContactException();
		contactRepository.insert(contact);
	}
	
	public Set<Contact> selectAll() throws SQLException {
		return contactRepository.selectAll();
	}
	
	public Set<Contact> select(int amount, int offset) throws SQLException {
		return contactRepository.select(amount, offset);
	}
	
}
