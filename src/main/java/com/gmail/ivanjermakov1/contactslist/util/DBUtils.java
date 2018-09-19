package com.gmail.ivanjermakov1.contactslist.util;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtils {
	
	public static Boolean nullableBoolean(ResultSet resultSet, String columnName) throws SQLException {
		boolean result = resultSet.getBoolean(columnName);
		return resultSet.wasNull() ? null : result;
	}
	
	public static Integer nullableInt(ResultSet resultSet, String columnName) throws SQLException {
		int result = resultSet.getInt(columnName);
		return resultSet.wasNull() ? null : result;
	}
	
}
