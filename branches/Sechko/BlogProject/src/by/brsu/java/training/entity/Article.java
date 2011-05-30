package by.brsu.java.training.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class Article implements Comparable<Article>{
	private long id;
	private String title;
	private String author;
	private Date date;
	private String text;
	private Set<Tag> tags;
	private Set<Comment> comments;
	private boolean published;
	private Blog blog;
	public int compareTo(Article o) {
		return date.compareTo(o.date);
	}
	/**
	 * 
	 */
	public Article() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param id
	 * @param title
	 * @param author
	 * @param date
	 * @param text
	 * @param tags
	 * @param comments
	 * @param published
	 * @param blog
	 */
	public Article(long id, String title, String author, Date date,
			String text, Set<Tag> tags, Set<Comment> comments,
			boolean published, Blog blog) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.date = date;
		this.text = text;
		this.tags = tags;
		this.comments = comments;
		this.published = published;
		this.blog = blog;
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
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
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
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return the tags
	 */
	public Set<Tag> getTags() {
		return tags;
	}
	/**
	 * @param tags the tags to set
	 */
	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
	/**
	 * @return the comments
	 */
	public Set<Comment> getComments() {
		return comments;
	}
	/**
	 * @param comments the comments to set
	 */
	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	/**
	 * @return the published
	 */
	public boolean isPublished() {
		return published;
	}
	/**
	 * @param published the published to set
	 */
	public void setPublished(boolean published) {
		this.published = published;
	}
	/**
	 * @return the blog
	 */
	public Blog getBlog() {
		return blog;
	}
	/**
	 * @param blog the blog to set
	 */
	public void setBlog(Blog blog) {
		this.blog = blog;
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
		Article other = (Article) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
