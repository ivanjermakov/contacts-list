package com.gmail.ivanjermakov1.contactslist.controller;

import com.gmail.ivanjermakov1.contactslist.entity.Address;
import com.gmail.ivanjermakov1.contactslist.exception.InvalidAddressException;
import com.gmail.ivanjermakov1.contactslist.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Set;

@RestController
public class AddressController {
	
	private final AddressService addressService;
	
	@Autowired
	public AddressController(AddressService addressService) {
		this.addressService = addressService;
	}
	
	@RequestMapping("address/init")
	@GetMapping
	public Address init() {
		return new Address();
	}
	
	@RequestMapping("address/insert")
	@PostMapping
	public void insert(@RequestBody Address address) throws SQLException, InvalidAddressException {
		addressService.insert(address);
	}
	
	@RequestMapping("address/select")
	@GetMapping
	public Set<Address> select(@RequestParam("id") int id) throws SQLException {
		return addressService.select(id);
	}
	
}
