package com.gmail.ivanjermakov1.contactslist.repository;

import com.gmail.ivanjermakov1.contactslist.entity.Contact;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

@Component
public class ContactsRepository extends Repository<Contact> {
	
	@Override
	public boolean add(Contact contact) {
		Connection connection = null;
		try {
			connection = super.getConnection();
			
			contact.insert(connection).execute();
			return true;
			
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
		
		return false;
	}
	
}
