package com.gmail.ivanjermakov1.contactslist.controller;

import com.gmail.ivanjermakov1.contactslist.entity.Address;
import com.gmail.ivanjermakov1.contactslist.exception.InvalidAddressException;
import com.gmail.ivanjermakov1.contactslist.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

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
	public Integer insert(@RequestBody Address address) throws SQLException, InvalidAddressException {
		return addressService.insert(address);
	}
	
	@RequestMapping("address/edit")
	@PostMapping
	public void edit(@RequestBody Address address) throws InvalidAddressException, SQLException {
		addressService.edit(address);
	}
	
	@RequestMapping("address/select")
	@GetMapping
	public Address select(@RequestParam("id") int id) throws SQLException {
		return addressService.select(id);
	}
	
}
