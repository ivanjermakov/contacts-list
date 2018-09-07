package com.gmail.ivanjermakov1.contactslist.repository;

import com.gmail.ivanjermakov1.contactslist.config.DatabaseConfigurator;
import com.gmail.ivanjermakov1.contactslist.entity.Avatar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class AvatarRepository {
	
	private final DatabaseConfigurator databaseConfigurator;
	
	@Autowired
	public AvatarRepository(DatabaseConfigurator databaseConfigurator) {
		this.databaseConfigurator = databaseConfigurator;
	}
	
	public void add(Avatar avatar) throws SQLException {
		Connection connection = databaseConfigurator.getConnection();
		
		PreparedStatement statement = connection.prepareStatement(
				"insert into " +
						"avatar(contact_id, path) " +
						"values (?, ?)"
		);
		statement.setInt(1, avatar.getContactId());
		statement.setString(2, avatar.getPath());
		
		statement.execute();
	}
	
}
