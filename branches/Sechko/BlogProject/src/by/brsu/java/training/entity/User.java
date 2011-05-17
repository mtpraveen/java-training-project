package by.brsu.java.training.entity;

import java.util.Date;
import java.util.List;

public class User {
	/**
	 * 
	 */
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	private long id;
	private String name;
	private String password;
	private Date date;
	private String about;
	private List<Blog> blogs;

	private boolean moderator;
	private boolean banned;
}
