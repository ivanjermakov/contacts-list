package com.gmail.ivanjermakov1.contactslist.repository;

import com.gmail.ivanjermakov1.contactslist.config.DatabaseConfigurator;
import com.gmail.ivanjermakov1.contactslist.entity.Attachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class AttachmentRepository {
	
	private final DatabaseConfigurator databaseConfigurator;
	
	@Autowired
	public AttachmentRepository(DatabaseConfigurator databaseConfigurator) {
		this.databaseConfigurator = databaseConfigurator;
	}
	
	public void add(Attachment attachment) throws SQLException {
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
	}
	
}
