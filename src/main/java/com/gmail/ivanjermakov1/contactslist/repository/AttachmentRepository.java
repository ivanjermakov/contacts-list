package com.gmail.ivanjermakov1.contactslist.repository;

import com.gmail.ivanjermakov1.contactslist.config.DatabaseConfigurator;
import com.gmail.ivanjermakov1.contactslist.entity.Attachment;
import com.gmail.ivanjermakov1.contactslist.exception.NoSuchEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

@Component
public class AttachmentRepository {
	
	private final DatabaseConfigurator databaseConfigurator;
	
	@Autowired
	public AttachmentRepository(DatabaseConfigurator databaseConfigurator) {
		this.databaseConfigurator = databaseConfigurator;
	}
	
	public void insert(Attachment attachment) throws SQLException {
		Connection connection = databaseConfigurator.getConnection();
		
		PreparedStatement statement = connection.prepareStatement(
				"insert into " +
						"attachment(contact_id, name, uploaded, path, comment) " +
						"values (?, ?, ?, ?, ?)"
		);
		statement.setInt(1, attachment.getContactId());
		statement.setString(2, attachment.getName());
		statement.setDate(3, attachment.getUploaded());
		statement.setString(4, attachment.getPath());
		statement.setString(5, attachment.getComment());
		
		statement.execute();
		
		connection.close();
	}
	
	public void edit(Attachment attachment) throws SQLException {
		Connection connection = databaseConfigurator.getConnection();
		
		PreparedStatement statement = connection.prepareStatement(
				"update attachment\n" +
						"set name = ?,\n" +
						"comment = ?\n" +
						"where id = ?"
		);
		
		statement.setString(1, attachment.getName());
		statement.setString(2, attachment.getComment());
		statement.setInt(3, attachment.getId());
		
		statement.execute();
		
		connection.close();
	}
	
	public Set<Attachment> selectByContactId(int contactId) throws SQLException {
		Connection connection = databaseConfigurator.getConnection();
		
		PreparedStatement statement = connection.prepareStatement(
				"select * from attachment where contact_id = ?"
		);
		statement.setInt(1, contactId);
		
		ResultSet resultSet = statement.executeQuery();
		
		connection.close();
		
		return set(resultSet);
	}
	
	public Attachment select(int id) throws SQLException, NoSuchEntityException {
		Connection connection = databaseConfigurator.getConnection();
		
		PreparedStatement statement = connection.prepareStatement(
				"select * from attachment where id = ? order by id asc"
		);
		statement.setInt(1, id);
		
		ResultSet resultSet = statement.executeQuery();
		
		connection.close();
		
		return set(resultSet).stream().findFirst()
				.orElseThrow(() -> new NoSuchEntityException("no phone number."));
	}
	
	private Set<Attachment> set(ResultSet resultSet) throws SQLException {
		Set<Attachment> set = new LinkedHashSet<>();
		
		while (resultSet.next()) {
			set.add(new Attachment(resultSet.getInt("id"),
					resultSet.getInt("contact_id"),
					resultSet.getString("name"),
					resultSet.getDate("uploaded"),
					resultSet.getString("path"),
					resultSet.getString("comment")
			));
		}
		
		return set;
	}
	
}
