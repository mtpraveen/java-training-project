package by.brsu.java.training.entity;

import java.util.Date;
import java.util.Set;

public class User implements Comparable<User> {
	private long id;
	private String name;
	private String password;
	private Date date;
	private String about;
	private Set<Blog> blogs;
	private boolean moderator;
	private boolean banned;

	/**
	 * 
	 */
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param name
	 * @param password
	 * @param date
	 * @param about
	 * @param blogs
	 * @param moderator
	 * @param banned
	 */
	public User(long id, String name, String password, Date date, String about,
			Set<Blog> blogs, boolean moderator, boolean banned) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.date = date;
		this.about = about;
		this.blogs = blogs;
		this.moderator = moderator;
		this.banned = banned;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the about
	 */
	public String getAbout() {
		return about;
	}

	/**
	 * @param about
	 *            the about to set
	 */
	public void setAbout(String about) {
		this.about = about;
	}

	/**
	 * @return the blogs
	 */
	public Set<Blog> getBlogs() {
		return blogs;
	}

	/**
	 * @param blogs
	 *            the blogs to set
	 */
	public void setBlogs(Set<Blog> blogs) {
		this.blogs = blogs;
	}

	/**
	 * @return the moderator
	 */
	public boolean isModerator() {
		return moderator;
	}

	/**
	 * @param moderator
	 *            the moderator to set
	 */
	public void setModerator(boolean moderator) {
		this.moderator = moderator;
	}

	/**
	 * @return the banned
	 */
	public boolean isBanned() {
		return banned;
	}

	/**
	 * @param banned
	 *            the banned to set
	 */
	public void setBanned(boolean banned) {
		this.banned = banned;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [about=" + about + ", banned=" + banned + ", blogs="
				+ blogs + ", date=" + date + ", id=" + id + ", moderator="
				+ moderator + ", name=" + name + ", password=" + password + "]";
	}

	public int compareTo(User o) {
		return this.name.compareTo(o.getName());
	}

}
