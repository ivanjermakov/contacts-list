package com.gmail.ivanjermakov1.contactslist.repository;

import com.gmail.ivanjermakov1.contactslist.config.DatabaseConfigurator;
import com.gmail.ivanjermakov1.contactslist.entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class ContactRepository {
	
	private final DatabaseConfigurator databaseConfigurator;
	
	@Autowired
	public ContactRepository(DatabaseConfigurator databaseConfigurator) {
		this.databaseConfigurator = databaseConfigurator;
	}
	
	public void add(Contact contact) throws SQLException {
		Connection connection = databaseConfigurator.getConnection();
		
		PreparedStatement statement = connection.prepareStatement(
				"insert into " +
						"contact(name, surname, patronymic, sex, birth, nationality, marital_status) " +
						"values (?, ?, ?, ?, ?, ?, ?)"
		);
		statement.setString(1, contact.getName());
		statement.setString(2, contact.getSurname());
		statement.setString(3, contact.getPatronymic());
		statement.setBoolean(4, contact.getSex());
		statement.setDate(5, contact.getBirth());
		statement.setString(6, contact.getNationality());
		statement.setString(7, contact.getMaritalStatus());
		
		statement.execute();
	}
	
}
