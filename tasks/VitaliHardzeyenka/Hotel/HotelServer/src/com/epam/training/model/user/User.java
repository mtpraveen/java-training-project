package com.epam.training.model.user;

/**
 * This class describe user.
 * @author Gordeenko
 *
 */
public class User {
	
	private String login;
	private String password;
	private UserStatus status; // describe who user is: administrator, client or disable
	
	/**
	 * Constructor
	 * @param login user login
	 * @param password password for the login
	 * @param status status of the user
	 */
	public User(String login, String password, UserStatus status) {
		this.setLogin(login);
		this.setPassword(password);
		this.setStatus(status);
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}
	
}
