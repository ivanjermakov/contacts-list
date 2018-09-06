package com.gmail.ivanjermakov1.contactslist.repository;

import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class Repository<E> {
	
	@Value("${postgres.url}")
	private String url;
	
	@Value("${postgres.username}")
	private String userName;
	
	@Value("${postgres.password}")
	private String password;
	
	public abstract boolean add(E entity);
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, userName, password);
	}
	
}
