package com.gmail.ivanjermakov1.contactslist.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DatabaseConfigurator {
	
	@Value("${postgres.url}")
	private String url;
	
	@Value("${postgres.username}")
	private String userName;
	
	@Value("${postgres.password}")
	private String password;
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, userName, password);
	}
	
}
