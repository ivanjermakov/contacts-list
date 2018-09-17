package com.gmail.ivanjermakov1.contactslist.repository;

import com.gmail.ivanjermakov1.contactslist.config.DatabaseConfigurator;
import com.gmail.ivanjermakov1.contactslist.entity.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

@Component
public class PhoneNumberRepository {
	
	private final DatabaseConfigurator databaseConfigurator;
	
	@Autowired
	public PhoneNumberRepository(DatabaseConfigurator databaseConfigurator) {
		this.databaseConfigurator = databaseConfigurator;
	}
	
	public void insert(PhoneNumber phoneNumber) throws SQLException {
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
		
		connection.close();
	}
	
	public Set<PhoneNumber> select(int id) throws SQLException {
		Connection connection = databaseConfigurator.getConnection();
		
		PreparedStatement statement = connection.prepareStatement(
				"select * from phone_number where contact_id = ?"
		);
		statement.setInt(1, id);
		
		ResultSet resultSet = statement.executeQuery();
		
		connection.close();
		
		return set(resultSet);
	}
	
	private Set<PhoneNumber> set(ResultSet resultSet) throws SQLException {
		Set<PhoneNumber> set = new LinkedHashSet<>();
		
		while (resultSet.next()) {
			set.add(new PhoneNumber(resultSet.getInt("contact_id"),
					resultSet.getInt("area_code"),
					resultSet.getInt("operator_code"),
					resultSet.getInt("number"),
					resultSet.getBoolean("type"),
					resultSet.getString("comment")
			));
		}
		
		return set;
	}
	
}
