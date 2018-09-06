package com.gmail.ivanjermakov1.contactslist.service;

import com.gmail.ivanjermakov1.contactslist.entity.Address;
import com.gmail.ivanjermakov1.contactslist.exception.InvalidAddressException;
import com.gmail.ivanjermakov1.contactslist.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class AddressService {
	
	private final Repository<Address> addressRepository;
	
	@Autowired
	public AddressService(Repository<Address> addressRepository) {
		this.addressRepository = addressRepository;
	}
	
	public void add(Address address) throws InvalidAddressException, SQLException {
		if (!address.valid()) throw new InvalidAddressException();
		
		addressRepository.add(address);
	}
	
}
