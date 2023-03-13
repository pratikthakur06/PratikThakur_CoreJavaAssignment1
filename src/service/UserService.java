package service;

import database.UserDBConnection;
import model.User;

public class UserService {

	private UserDBConnection userDBConnection;
	
	public UserService(UserDBConnection userDBConnection) {
		this.userDBConnection = userDBConnection;
	}
	
	public boolean registerUser(User user) throws Exception {
		if(user.getEmail()==null || user.getEmail().isEmpty())
			throw new Exception("Email cannot be empty or null!");
		try {
			userDBConnection.insertUser(user);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return true;
	}
	
	public boolean validateCredentials(String email, String password) throws Exception {
		if(email==null || email.isEmpty())
			throw new Exception("Email cannot be empty or null!");
		return this.userDBConnection.login(email, password);
	}
}
