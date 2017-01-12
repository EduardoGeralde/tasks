package com.eduardoportfolio.taskList.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.eduardoportfolio.taskList.ConnectionFactory;
import com.eduardoportfolio.taskList.model.User;

public class JdbcUserDao {
	private Connection connection;

	public JdbcUserDao() {
		try {
			connection = new ConnectionFactory().getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean userExists(User user) {

		if (user == null) {
			throw new IllegalArgumentException("User can not be null");
		}

		try {
			PreparedStatement stmt = this.connection
					.prepareStatement("select * from users where login = ? and password = ?");
			stmt.setString(1, user.getLogin());
			stmt.setString(2, user.getPassword());
			ResultSet rs = stmt.executeQuery();

			boolean found = rs.next();
			rs.close();
			stmt.close();

			return found;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
