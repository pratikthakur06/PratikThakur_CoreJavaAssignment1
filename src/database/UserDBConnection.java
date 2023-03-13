package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class UserDBConnection {

	Connection connection = DBConnection.getConnection();

	public boolean insertUser(User user) throws Exception {
		String sql = "insert into user values(?,?,?)";
		System.out.println(sql);
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getUserName());
			statement.setString(3, user.getPassword());

			statement.execute();
		} catch (SQLException ex) {
			System.out.println("Error while inserting user data!");
			throw new Exception(ex.getMessage());
		}
		return true;
	}

	public boolean login(String email, String password) throws Exception {
		String sql = "select password from user where email=?";
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, email);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				if (password.equals(rs.getString(1)))
					return true;
			}
			throw new Exception("Invalid credentials!");
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		}
	}
}
