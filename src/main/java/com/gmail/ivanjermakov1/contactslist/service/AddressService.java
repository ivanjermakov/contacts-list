package com.gmail.ivanjermakov1.contactslist.service;

import com.gmail.ivanjermakov1.contactslist.entity.Address;
import com.gmail.ivanjermakov1.contactslist.exception.InvalidAddressException;
import com.gmail.ivanjermakov1.contactslist.exception.NoSuchEntityException;
import com.gmail.ivanjermakov1.contactslist.repository.AddressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class AddressService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private final AddressRepository addressRepository;
	
	@Autowired
	public AddressService(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}
	
	public Integer insert(Address address) throws InvalidAddressException, SQLException {
		logger.info("insert address: of@" + address.getContactId());
		if (!address.valid()) throw new InvalidAddressException("Invalid address.");
		
		return addressRepository.insert(address);
	}
	
	public void edit(Address address) throws InvalidAddressException, SQLException {
		logger.info("edit address: of@" + address.getContactId());
		if (!address.valid()) throw new InvalidAddressException("invalid address.");
		
		addressRepository.edit(address);
	}
	
	public Address select(int id) throws SQLException, NoSuchEntityException {
		logger.info("select address: of@" + id);
		return addressRepository.select(id);
	}
	
}
