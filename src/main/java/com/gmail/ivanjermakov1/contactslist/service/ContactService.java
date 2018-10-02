package com.gmail.ivanjermakov1.contactslist.service;

import com.gmail.ivanjermakov1.contactslist.entity.Contact;
import com.gmail.ivanjermakov1.contactslist.exception.InvalidContactException;
import com.gmail.ivanjermakov1.contactslist.exception.NoSuchEntityException;
import com.gmail.ivanjermakov1.contactslist.repository.AddressRepository;
import com.gmail.ivanjermakov1.contactslist.repository.ContactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@Service
public class ContactService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private final AddressRepository addressRepository;
	private final ContactRepository contactRepository;
	
	@Autowired
	public ContactService(ContactRepository contactRepository, AddressRepository addressRepository) {
		this.contactRepository = contactRepository;
		this.addressRepository = addressRepository;
	}
	
	public Integer insert(Contact contact) throws InvalidContactException, SQLException {
		logger.info("insert contact @" + contact.getId());
		if (!contact.valid()) throw new InvalidContactException("invalid contact.");
		return contactRepository.insert(contact);
	}
	
	public void edit(Contact contact) throws InvalidContactException, SQLException {
		logger.info("edit contact @" + contact.getId());
		if (!contact.valid()) throw new InvalidContactException();
		contactRepository.edit(contact);
	}
	
	public Set<Contact> selectAll() throws SQLException {
		logger.info("select all contacts");
		return contactRepository.selectAll();
	}
	
	public Contact selectById(int id) throws SQLException, NoSuchEntityException {
		logger.info("select contacts by id@" + id);
		return contactRepository.selectById(id);
	}
	
	public void remove(List<Integer> ids) throws SQLException {
		logger.info("remove contacts: @" + ids);
		for (Integer id : ids) {
			contactRepository.removeById(id);
			addressRepository.removeById(id);
		}
	}
	
	public Set<Contact> select(int amount, int offset) throws SQLException {
		logger.info("select contacts: amount: " + amount + " offset: " + offset);
		return contactRepository.select(amount, offset);
	}
	
}
