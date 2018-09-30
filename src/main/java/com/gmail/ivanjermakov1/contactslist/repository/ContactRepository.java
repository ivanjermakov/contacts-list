package com.gmail.ivanjermakov1.contactslist.repository;

import com.gmail.ivanjermakov1.contactslist.config.DatabaseConfigurator;
import com.gmail.ivanjermakov1.contactslist.entity.Contact;
import com.gmail.ivanjermakov1.contactslist.entity.ContactMainInfo;
import com.gmail.ivanjermakov1.contactslist.exception.NoSuchEntityException;
import com.gmail.ivanjermakov1.contactslist.util.DBUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Component
public class ContactRepository {
	
	private final AddressRepository addressRepository;
	private final DatabaseConfigurator databaseConfigurator;
	
	@Autowired
	public ContactRepository(DatabaseConfigurator databaseConfigurator, AddressRepository addressRepository) {
		this.databaseConfigurator = databaseConfigurator;
		this.addressRepository = addressRepository;
	}
	
	public Integer insert(Contact contact) throws SQLException {
		Connection connection = databaseConfigurator.getConnection();
		
		PreparedStatement statement = connection.prepareStatement(
				"insert into " +
						"contact(name, surname, patronymic, sex, birth, nationality, marital_status, website, email, workplace, removed) " +
						"values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, false)", Statement.RETURN_GENERATED_KEYS
		);
		statement.setString(1, contact.getName());
		statement.setString(2, contact.getSurname());
		statement.setString(3, contact.getPatronymic());
		if (contact.getSex() != null) {
			statement.setBoolean(4, contact.getSex());
		} else {
			statement.setNull(4, Types.BOOLEAN);
		}
		statement.setDate(5, contact.getBirth());
		statement.setString(6, contact.getNationality());
		statement.setString(7, contact.getMaritalStatus());
		statement.setString(8, contact.getWebsite());
		statement.setString(9, contact.getEmail());
		statement.setString(10, contact.getWorkplace());
		
		statement.execute();
		
		connection.close();
		
		ResultSet id = statement.getGeneratedKeys();
		if (id.next()) return id.getInt(1);
		throw new SQLException("error saving entity.");
	}
	
	public void edit(Contact contact) throws SQLException {
		Connection connection = databaseConfigurator.getConnection();
		
		PreparedStatement statement = connection.prepareStatement(
				"update contact\n" +
						"set name    = ?,\n" +
						"    surname = ?,\n" +
						"    patronymic = ?,\n" +
						"    sex = ?,\n" +
						"    birth = ?,\n" +
						"    nationality = ?,\n" +
						"    marital_status = ?,\n" +
						"    website = ?,\n" +
						"    email = ?,\n" +
						"    workplace = ?\n" +
						"where id = ?"
		);
		
		statement.setString(1, contact.getName());
		statement.setString(2, contact.getSurname());
		statement.setString(3, contact.getPatronymic());
		statement.setObject(4, contact.getSex(), Types.BOOLEAN);
		statement.setDate(5, contact.getBirth());
		statement.setString(6, contact.getNationality());
		statement.setString(7, contact.getMaritalStatus());
		statement.setString(8, contact.getWebsite());
		statement.setString(9, contact.getEmail());
		statement.setString(10, contact.getWorkplace());
		statement.setInt(11, contact.getId());
		
		statement.execute();
		
		connection.close();
	}
	
	public Set<Contact> selectAll() throws SQLException {
		Connection connection = databaseConfigurator.getConnection();
		
		PreparedStatement statement = connection.prepareStatement(
				"select * from contact where removed = false order by id asc"
		);
		
		ResultSet resultSet = statement.executeQuery();
		
		connection.close();
		
		return set(resultSet);
	}
	
	public Contact selectById(int id) throws SQLException, NoSuchEntityException {
		Connection connection = databaseConfigurator.getConnection();
		
		PreparedStatement statement = connection.prepareStatement(
				"select * from contact where id = ? and removed = false order by id asc"
		);
		statement.setInt(1, id);
		
		ResultSet resultSet = statement.executeQuery();
		
		connection.close();
		
		return set(resultSet).stream().findFirst().orElseThrow(NoSuchEntityException::new);
	}
	
	public void removeById(int id) throws SQLException {
		Connection connection = databaseConfigurator.getConnection();
		
		PreparedStatement statement = connection.prepareStatement(
				"update contact\n" +
						"set removed = true\n" +
						"where id = ?"
		);
		statement.setInt(1, id);
		
		statement.execute();
		
		connection.close();
		
		addressRepository.removeById(id);
	}
	
	public Set<Contact> select(int amount, int offset) throws SQLException {
		Connection connection = databaseConfigurator.getConnection();
		
		PreparedStatement statement = connection.prepareStatement(
				"select * from contact where removed = false order by id asc limit ? offset ?"
		);
		statement.setInt(1, amount);
		statement.setInt(2, offset);
		
		ResultSet resultSet = statement.executeQuery();
		
		connection.close();
		
		return set(resultSet);
	}
	
	public Set<ContactMainInfo> selectAllMainInfo() throws SQLException {
		Connection connection = databaseConfigurator.getConnection();
		
		PreparedStatement statement = connection.prepareStatement(
				"select id, name, surname, patronymic, birth, locality, workplace\n" +
						"from contact, address\n" +
						"where address.contact_id = id and contact.removed = false and address.removed = false\n" +
						"order by id asc;"
		);
		
		ResultSet resultSet = statement.executeQuery();
		
		connection.close();
		
		return mainInfoSet(resultSet);
	}
	
	public Set<ContactMainInfo> selectMainInfo(int amount, int offset) throws SQLException {
		Connection connection = databaseConfigurator.getConnection();
		
		PreparedStatement statement = connection.prepareStatement(
				"select id, name, surname, patronymic, birth, locality, workplace\n" +
						"from contact, address\n" +
						"where address.contact_id = id and contact.removed = false and address.removed = false\n" +
						"order by id asc " +
						"offset ? limit ?"
		
		);
		statement.setInt(1, offset);
		statement.setInt(2, amount);
		
		ResultSet resultSet = statement.executeQuery();
		
		connection.close();
		
		return mainInfoSet(resultSet);
	}
	
	public Set<Contact> selectWithBirthday(Date current) throws SQLException {
		Connection connection = databaseConfigurator.getConnection();
		
		PreparedStatement statement = connection.prepareStatement(
				"select * from contact where birth = ?"
		);
		statement.setDate(1, current);
		
		ResultSet resultSet = statement.executeQuery();
		
		connection.close();
		
		return set(resultSet);
	}
	
	public Set<Contact> set(ResultSet resultSet) throws SQLException {
		Set<Contact> set = new LinkedHashSet<>();
		
		while (resultSet.next()) {
			set.add(new Contact(
					DBUtils.nullableInt(resultSet, "id"),
					resultSet.getString("name"),
					resultSet.getString("surname"),
					resultSet.getString("patronymic"),
					DBUtils.nullableBoolean(resultSet, "sex"),
					resultSet.getDate("birth"),
					resultSet.getString("nationality"),
					resultSet.getString("marital_status"),
					resultSet.getString("website"),
					resultSet.getString("email"),
					resultSet.getString("workplace"),
					resultSet.getBoolean("removed")));
		}
		
		return set;
	}
	
	private Set<ContactMainInfo> mainInfoSet(ResultSet resultSet) throws SQLException {
		Set<ContactMainInfo> set = new LinkedHashSet<>();
		
		while (resultSet.next()) {
			set.add(new ContactMainInfo(
					DBUtils.nullableInt(resultSet, "id"),
					resultSet.getString("name"),
					resultSet.getString("surname"),
					resultSet.getString("patronymic"),
					resultSet.getDate("birth"),
					resultSet.getString("locality"),
					resultSet.getString("workplace")));
		}
		
		return set;
	}
	
}
