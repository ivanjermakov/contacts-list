package com.gmail.ivanjermakov1.contactslist.repository;

import com.gmail.ivanjermakov1.contactslist.config.DatabaseConfigurator;
import com.gmail.ivanjermakov1.contactslist.entity.PhoneNumber;
import com.gmail.ivanjermakov1.contactslist.exception.NoSuchEntityException;
import com.gmail.ivanjermakov1.contactslist.util.DBUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
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
		statement.setObject(5, phoneNumber.getType(), Types.BOOLEAN);
		statement.setString(6, phoneNumber.getComment());
		
		statement.execute();
		
		connection.close();
	}
	
	public void edit(PhoneNumber phoneNumber) throws SQLException {
		Connection connection = databaseConfigurator.getConnection();
		
		PreparedStatement statement = connection.prepareStatement(
				"update phone_number\n" +
						"set contact_id = ?,\n" +
						"area_code = ?,\n" +
						"operator_code = ?,\n" +
						"number = ?,\n" +
						"type = ?,\n" +
						"comment = ?\n" +
						"where id = ?"
		);
		
		statement.setInt(1, phoneNumber.getContactId());
		statement.setInt(2, phoneNumber.getAreaCode());
		statement.setInt(3, phoneNumber.getOperatorCode());
		statement.setInt(4, phoneNumber.getNumber());
		statement.setObject(5, phoneNumber.getType(), Types.BOOLEAN);
		statement.setString(6, phoneNumber.getComment());
		statement.setInt(7, phoneNumber.getId());
		
		statement.execute();
		
		connection.close();
	}
	
	public void removeById(int id) throws SQLException {
		Connection connection = databaseConfigurator.getConnection();
		
		PreparedStatement statement = connection.prepareStatement(
				"delete from phone_number\n" +
						"where id = ?"
		);
		statement.setInt(1, id);
		
		statement.execute();
		
		connection.close();
	}
	
	public PhoneNumber select(int id) throws SQLException, NoSuchEntityException {
		Connection connection = databaseConfigurator.getConnection();
		
		PreparedStatement statement = connection.prepareStatement(
				"select * from phone_number where id = ? order by id asc"
		);
		statement.setInt(1, id);
		
		ResultSet resultSet = statement.executeQuery();
		
		connection.close();
		
		return set(resultSet).stream().findFirst()
				.orElseThrow(() -> new NoSuchEntityException("no phone number."));
	}
	
	public Set<PhoneNumber> selectByContactId(int contactId) throws SQLException {
		Connection connection = databaseConfigurator.getConnection();
		
		PreparedStatement statement = connection.prepareStatement(
				"select * from phone_number where contact_id = ?"
		);
		statement.setInt(1, contactId);
		
		ResultSet resultSet = statement.executeQuery();
		
		connection.close();
		
		return set(resultSet);
	}
	
	private Set<PhoneNumber> set(ResultSet resultSet) throws SQLException {
		Set<PhoneNumber> set = new LinkedHashSet<>();
		
		while (resultSet.next()) {
			set.add(new PhoneNumber(resultSet.getInt("id"),
					resultSet.getInt("contact_id"),
					resultSet.getInt("area_code"),
					resultSet.getInt("operator_code"),
					resultSet.getInt("number"),
					DBUtils.nullableBoolean(resultSet, "type"),
					resultSet.getString("comment")
			));
		}
		
		return set;
	}
	
}
