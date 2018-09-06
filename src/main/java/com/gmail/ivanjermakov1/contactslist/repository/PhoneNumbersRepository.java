package com.gmail.ivanjermakov1.contactslist.repository;

import com.gmail.ivanjermakov1.contactslist.entity.PhoneNumber;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

@Component
public class PhoneNumbersRepository extends Repository<PhoneNumber> {
	
	@Override
	public boolean add(PhoneNumber phoneNumber) {
		Connection connection = null;
		try {
			connection = super.getConnection();
			
			phoneNumber.insert(connection).execute();
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
