package com.gmail.ivanjermakov1.contactslist.repository;

import com.gmail.ivanjermakov1.contactslist.config.DatabaseConfigurator;
import com.gmail.ivanjermakov1.contactslist.entity.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class PhoneNumberRepository {
	
	private final DatabaseConfigurator databaseConfigurator;
	
	@Autowired
	public PhoneNumberRepository(DatabaseConfigurator databaseConfigurator) {
		this.databaseConfigurator = databaseConfigurator;
	}
	
	public void add(PhoneNumber phoneNumber) throws SQLException {
		Connection connection = databaseConfigurator.getConnection();
		
		PreparedStatement statement = connection.prepareStatement(
				"insert into " +
						"phone_number(contact_id, area_code, operator_code, number, type, comment) " +
						"values (?, ?, ?, ?, ?, ?)"
		);
		statement.setInt(1, phoneNumber.getContactId());
		statement.setInt(2, phoneNumber.getAreaCode());
		statement.setInt(3, phoneNumber.getOperatorCode());
		statement.setInt(4, phoneNumber.getNumber());
		statement.setBoolean(5, phoneNumber.getType());
		statement.setString(6, phoneNumber.getComment());
		
		statement.execute();
	}
	
}
