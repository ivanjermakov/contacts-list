package com.gmail.ivanjermakov1.contactslist.repository;

import com.gmail.ivanjermakov1.contactslist.entity.Contact;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class ContactsRepository {
	
	@Value("${postgres.url}")
	private String url;
	
	@Value("${postgres.username}")
	private String userName;
	
	@Value("${postgres.password}")
	private String password;
	
	public void add(Contact contact) {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, userName, password);
			
			PreparedStatement statement = connection.prepareStatement(
					"insert into " +
							"contacts(name, surname, patronymic, birth, sex, nationality, marital_status, web_site, email, job_place, country, city, address, postcode) " +
							"values (?, ?, ?, ?,?, ?, ?, ?,?, ?, ?, ?,?, ?)"
			);
			statement.setString(1, contact.getName());
			statement.setString(2, contact.getSurname());
			statement.setString(3, contact.getPatronymic());
			statement.setDate(4, contact.getBirth());
			statement.setBoolean(5, contact.getSex());
			statement.setString(6, contact.getNationality());
			statement.setString(7, contact.getMaritalStatus());
			statement.setString(8, contact.getWebSite());
			statement.setString(9, contact.getEmail());
			statement.setString(10, contact.getJobPlace());
			statement.setString(11, contact.getCountry());
			statement.setString(12, contact.getCity());
			statement.setString(13, contact.getAddress());
			statement.setInt(14, contact.getPostcode());
			
			statement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
