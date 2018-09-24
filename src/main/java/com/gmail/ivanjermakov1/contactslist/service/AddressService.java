package com.gmail.ivanjermakov1.contactslist.service;

import com.gmail.ivanjermakov1.contactslist.entity.Address;
import com.gmail.ivanjermakov1.contactslist.exception.InvalidAddressException;
import com.gmail.ivanjermakov1.contactslist.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class AddressService {
	
	private final AddressRepository addressRepository;
	
	@Autowired
	public AddressService(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}
	
	public Integer insert(Address address) throws InvalidAddressException, SQLException {
		if (!address.valid()) throw new InvalidAddressException("Invalid address.");
		
		return addressRepository.insert(address);
	}
	
	public void edit(Address address) throws InvalidAddressException, SQLException {
		if (!address.valid()) throw new InvalidAddressException("invalid address.");
		
		addressRepository.edit(address);
	}
	
	public Address select(int id) throws SQLException {
		return addressRepository.select(id);
	}
	
}
