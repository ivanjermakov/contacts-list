package com.gmail.ivanjermakov1.contactslist.util.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface Insertable {
	
	PreparedStatement insert(Connection connection) throws SQLException;
	
}
