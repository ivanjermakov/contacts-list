package com.gmail.ivanjermakov1.contactslist.repository;

import com.gmail.ivanjermakov1.contactslist.config.DatabaseConfigurator;
import com.gmail.ivanjermakov1.contactslist.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class AddressRepository {
	
	private final DatabaseConfigurator databaseConfigurator;
	
	@Autowired
	public AddressRepository(DatabaseConfigurator databaseConfigurator) {
		this.databaseConfigurator = databaseConfigurator;
	}
	
	public void insert(Address address) throws SQLException {
		Connection connection = databaseConfigurator.getConnection();
		
		PreparedStatement statement = connection.prepareStatement(
				"insert into " +
						"address(contact_id, country, region, locality, postcode) " +
						"values (?, ?, ?, ?, ?)"
		);
		statement.setInt(1, address.getContactId());
		statement.setString(2, address.getCountry());
		statement.setString(3, address.getRegion());
		statement.setString(4, address.getLocality());
		statement.setInt(5, address.getPostcode());
		
		statement.execute();
	}
	
}
