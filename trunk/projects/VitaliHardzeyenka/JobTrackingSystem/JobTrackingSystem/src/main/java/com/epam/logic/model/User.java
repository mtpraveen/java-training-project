package com.epam.logic.model;

public class User {
	
	public static final int MANAGER = 1;
	public static final int WORKER = 2;
	
	// Fields names for table 'users'.
	public static final String USER_ID = "user_id";
	public static final String LOGIN = "login";
	public static final String USER_NAME = "name";
	public static final String SURNAME = "surname";
	public static final String PASSWORD = "password";
	public static final String EMAIL = "email";
	public static final String POSITION_ID = "position_id";
	
	private int id;
	private String login;
	private String name;
	private String surname;
	private String password;
	private String email;
	private Position position;
	private Jobs jobs;

	public User() {}
	
	/**
	 * Constructor.
	 * @param login
	 * @param password
	 * @param email
	 * @param position
	 */
	public User(String login, String name, String surname, String password, String email, Position position) {
		super();
		this.login = login;
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.email = email;
		this.position = position;
	}
	
	public User(int id, String login, String name, String surname, String password, String email, Position position) {
		this.id = id;
		this.login = login;
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.email = email;
		this.position = position;
	}
	
	public Jobs getJobs() {
		return jobs;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setJobs(Jobs jobs) {
		this.jobs = jobs;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
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
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Position getPosition() {
		return position;
	}
	
	public void setPosition(Position position) {
		this.position = position;
	}
	
}
