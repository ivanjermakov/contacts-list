package com.gmail.ivanjermakov1.contactslist.repository;

import com.gmail.ivanjermakov1.contactslist.util.db.Insertable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class Repository<E extends Insertable> {
	
	@Value("${postgres.url}")
	private String url;
	
	@Value("${postgres.username}")
	private String userName;
	
	@Value("${postgres.password}")
	private String password;
	
	public void add(E entity) throws SQLException {
		Connection connection = getConnection();
		entity.insert(connection).execute();
	}
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, userName, password);
	}
	
}
