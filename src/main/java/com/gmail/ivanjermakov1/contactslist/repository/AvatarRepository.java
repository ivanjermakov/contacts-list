package com.gmail.ivanjermakov1.contactslist.repository;

import com.gmail.ivanjermakov1.contactslist.config.DatabaseConfigurator;
import com.gmail.ivanjermakov1.contactslist.entity.Avatar;
import com.gmail.ivanjermakov1.contactslist.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AvatarRepository {
	
	private final DatabaseConfigurator databaseConfigurator;
	
	@Autowired
	public AvatarRepository(DatabaseConfigurator databaseConfigurator) {
		this.databaseConfigurator = databaseConfigurator;
	}
	
	public void insert(Avatar avatar) throws SQLException {
		Connection connection = databaseConfigurator.getConnection();
		
		PreparedStatement statement = connection.prepareStatement(
				"insert into " +
						"avatar(contact_id, path) " +
						"values (?, ?)"
		);
		statement.setInt(1, avatar.getContactId());
		statement.setString(2, avatar.getPath());
		
		statement.execute();
		
		connection.close();
	}
	
	public Avatar select(int id) throws SQLException, EntityNotFoundException {
		Connection connection = databaseConfigurator.getConnection();
		
		PreparedStatement statement = connection.prepareStatement(
				"select * from avatar where contact_id = ?"
		);
		statement.setInt(1, id);
		
		ResultSet resultSet = statement.executeQuery();
		if (!resultSet.next()) throw new EntityNotFoundException("no such entity");
		
		connection.close();
		
		return new Avatar(resultSet.getInt("contact_id"),
				resultSet.getString("path"));
	}
	
	public void edit(Avatar avatar) throws SQLException {
		Connection connection = databaseConfigurator.getConnection();
		
		PreparedStatement statement = connection.prepareStatement(
				"update avatar set path = ? where contact_id = ?"
		);
		statement.setString(1, avatar.getPath());
		statement.setInt(2, avatar.getContactId());
		
		statement.execute();
		
		connection.close();
	}
	
}
