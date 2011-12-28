package com.epam.mvc3;

import java.lang.String;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "User")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int Id;
	
	@ManyToOne
	private UserRole Role;
	private String Login;
	private String Password;
	/**
	 * @return the id
	 */
	public int getId() {
		return Id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		Id = id;
	}
	/**
	 * @return the role
	 */
	public UserRole getRole() {
		return Role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(UserRole role) {
		Role = role;
	}
	/**
	 * @return the login
	 */
	public String getLogin() {
		return Login;
	}
	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		Login = login;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return Password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		Password = password;
	}
	
}
