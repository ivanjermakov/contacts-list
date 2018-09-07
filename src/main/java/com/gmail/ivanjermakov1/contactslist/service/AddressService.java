package com.gmail.ivanjermakov1.contactslist.service;

import com.gmail.ivanjermakov1.contactslist.entity.Address;
import com.gmail.ivanjermakov1.contactslist.exception.InvalidAddressException;
import com.gmail.ivanjermakov1.contactslist.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Set;

@Service
public class AddressService {
	
	private final AddressRepository addressRepository;
	
	@Autowired
	public AddressService(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}
	
	public void insert(Address address) throws InvalidAddressException, SQLException {
		if (!address.valid()) throw new InvalidAddressException();
		
		addressRepository.insert(address);
	}
	
	public Set<Address> select(int id) throws SQLException {
		return addressRepository.select(id);
	}
	
}
