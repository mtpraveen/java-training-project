package by.brsu.java.training.entity;

import java.util.Date;
import java.util.Set;

public class Blog implements Comparable<Blog>{
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	private long id;
	private String title;
	private String author;
	private Date date;
	private Set<Article> articles;
	private User user;
	/**
	 * 
	 */
	public Blog() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param id
	 * @param title
	 * @param date
	 * @param articles
	 * @param user
	 */
	public Blog(long id, String title, String author, Date date, Set<Article> articles,
			User user) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.date = date;
		this.articles = articles;
		this.user = user;
	}
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
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
	/**
	 * @return the articles
	 */
	public Set<Article> getArticles() {
		return articles;
	}
	/**
	 * @param articles the articles to set
	 */
	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
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
		Blog other = (Blog) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Blog [articles=" + articles + ", author=" + author + ", date="
				+ date + ", id=" + id + ", title=" + title + ", user=" + user
				+ "]";
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getAuthor() {
		return author;
	}
	public int compareTo(Blog o) {
		return title.compareToIgnoreCase(o.title);
	}
	
}
