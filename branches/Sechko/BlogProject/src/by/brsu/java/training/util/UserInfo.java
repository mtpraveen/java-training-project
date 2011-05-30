package by.brsu.java.training.util;

import java.util.Date;

import org.apache.click.control.ActionLink;

public class UserInfo implements Comparable<UserInfo>{
	private String name;
	private Date date;
		
	/**
	 * 
	 */
	public UserInfo() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param name
	 * @param date
	 */
	public UserInfo(String name, Date date) {
		super();
		this.name = name;
		this.date = date;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}


	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}


	public int compareTo(UserInfo o) {
		return name.compareToIgnoreCase(o.name);
	}
	
	
}
