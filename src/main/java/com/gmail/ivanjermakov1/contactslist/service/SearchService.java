package com.gmail.ivanjermakov1.contactslist.service;

import com.gmail.ivanjermakov1.contactslist.config.DatabaseConfigurator;
import com.gmail.ivanjermakov1.contactslist.entity.Contact;
import com.gmail.ivanjermakov1.contactslist.entity.Search;
import com.gmail.ivanjermakov1.contactslist.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import static com.gmail.ivanjermakov1.contactslist.util.DBUtils.quotes;
import static com.gmail.ivanjermakov1.contactslist.util.DBUtils.where;

@Service
public class SearchService {
	
	private final DatabaseConfigurator databaseConfigurator;
	private final ContactRepository contactRepository;
	
	@Autowired
	public SearchService(ContactRepository contactRepository, DatabaseConfigurator databaseConfigurator) {
		this.contactRepository = contactRepository;
		this.databaseConfigurator = databaseConfigurator;
	}
	
	public Set<Contact> search(Search search) throws SQLException {
		String sql = "select * from contact, address where (";
		
		if (search.getName() != null && !search.getName().isEmpty())
			sql += where("name", search.getName()) + " or ";
		if (search.getSurname() != null && !search.getSurname().isEmpty())
			sql += where("surname", search.getSurname()) + " or ";
		if (search.getPatronymic() != null && !search.getPatronymic().isEmpty())
			sql += where("patronymic", search.getPatronymic()) + " or ";
		if (search.getBirth() != null && search.getBirthAfter() != null) {
			if (search.getBirthAfter()) {
				sql += "birth > " + quotes(search.getBirth().toString());
			} else {
				sql += "birth < " + quotes(search.getBirth().toString());
			}
			sql += " or ";
		}
		if (search.getSex() != null)
			sql += "sex = " + search.getSex() + " or ";
		if (search.getMaritalStatus() != null && !search.getMaritalStatus().isEmpty())
			sql += where("marital_status", search.getMaritalStatus()) + " or ";
		if (search.getNationality() != null && !search.getNationality().isEmpty())
			sql += where("nationality", search.getNationality()) + " or ";
		if (search.getCountry() != null && !search.getCountry().isEmpty())
			sql += where("country", search.getCountry()) + " or ";
		if (search.getRegion() != null && !search.getRegion().isEmpty())
			sql += where("region", search.getRegion()) + " or ";
		if (search.getLocality() != null && !search.getLocality().isEmpty())
			sql += where("locality", search.getLocality()) + " or ";
		if (search.getPostcode() != null)
			sql += "postcode::varchar like " + quotes("%" + search.getPostcode().toString() + "%") + " or ";
		
		if (sql.endsWith(" or ")) sql = sql.substring(0, sql.length() - 3);
		if (sql.endsWith("(")) {
			sql = sql.substring(0, sql.length() - 1);
			sql += "contact_id = id order by id asc";
		} else {
			sql += ") and contact_id = id order by id asc";
		}
		
		System.out.println(sql);
		
		Connection connection = databaseConfigurator.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		connection.close();
		
		return contactRepository.set(resultSet);
	}
	
}
