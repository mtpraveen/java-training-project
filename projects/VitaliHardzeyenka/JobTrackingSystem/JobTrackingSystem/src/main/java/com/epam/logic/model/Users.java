package com.epam.logic.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gordeenko_XP
 * Use as java bean.
 */
public class Users {
	
	private List<User> users = new ArrayList<>();

	public Users() {}
	
	public Users(List<User> users) {
		this.users = users;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
}
