package com.gmail.ivanjermakov1.contactslist.repository;

import com.gmail.ivanjermakov1.contactslist.config.DatabaseConfigurator;
import com.gmail.ivanjermakov1.contactslist.entity.Address;
import com.gmail.ivanjermakov1.contactslist.exception.NoSuchEntityException;
import com.gmail.ivanjermakov1.contactslist.util.DBUtils;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class AddressRepository {
	
	private final DatabaseConfigurator databaseConfigurator;
	
	@Autowired
	public AddressRepository(DatabaseConfigurator databaseConfigurator) {
		this.databaseConfigurator = databaseConfigurator;
	}
	
	public Integer insert(Address address) throws SQLException {
		Connection connection = databaseConfigurator.getConnection();
		
		PreparedStatement statement = connection.prepareStatement(
				"insert into " +
						"address(contact_id, country, region, locality, postcode, removed) " +
						"values (?, ?, ?, ?, ?, false)", Statement.RETURN_GENERATED_KEYS
		);
		statement.setInt(1, address.getContactId());
		statement.setString(2, address.getCountry());
		statement.setString(3, address.getRegion());
		statement.setString(4, address.getLocality());
		statement.setObject(5, address.getPostcode(), Types.INTEGER);
		
		statement.execute();
		
		connection.close();
		
		ResultSet id = statement.getGeneratedKeys();
		if (id.next()) return id.getInt(1);
		throw new SQLException("error saving entity.");
	}
	
	public void edit(Address address) throws SQLException {
		Connection connection = databaseConfigurator.getConnection();
		
		PreparedStatement statement = connection.prepareStatement(
				"update address\n" +
						"set country  = ?,\n" +
						"    region   = ?,\n" +
						"    locality = ?,\n" +
						"    postcode = ?\n" +
						"where contact_id = ?;"
		);
		statement.setString(1, address.getCountry());
		statement.setString(2, address.getRegion());
		statement.setString(3, address.getLocality());
		statement.setObject(4, address.getPostcode(), Types.INTEGER);
		statement.setInt(5, address.getContactId());
		
		statement.execute();
		
		connection.close();
	}
	
	public Address select(int id) throws SQLException, NoSuchEntityException {
		Connection connection = databaseConfigurator.getConnection();
		
		PreparedStatement statement = connection.prepareStatement(
				"select * from address where contact_id = ? and removed = false"
		);
		statement.setInt(1, id);
		
		ResultSet resultSet = statement.executeQuery();
		resultSet.next();
		
		connection.close();
		
		try {
			return new Address(resultSet.getInt("contact_id"),
					resultSet.getString("country"),
					resultSet.getString("region"),
					resultSet.getString("locality"),
					DBUtils.nullableInt(resultSet, "postcode"),
					resultSet.getBoolean("removed")
			);
		} catch (PSQLException e) {
			throw new NoSuchEntityException("nothing found.");
		}
	}
	
	public void removeById(int id) throws SQLException {
		Connection connection = databaseConfigurator.getConnection();
		
		PreparedStatement statement = connection.prepareStatement(
				"update address\n" +
						"set removed = true\n" +
						"where contact_id = ?"
		);
		statement.setInt(1, id);
		
		statement.execute();
		
		connection.close();
	}
	
}
