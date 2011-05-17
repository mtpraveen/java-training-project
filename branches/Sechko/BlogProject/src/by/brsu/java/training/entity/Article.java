package by.brsu.java.training.entity;

import java.util.List;

public class Article {
	private long id;
	private String title;
	private String author;
	private String date;
	private String text;
	private List<String> tags;
	private List<Comment> comments;
	private boolean published;	
	/**
	 * @param id
	 * @param title
	 * @param author
	 * @param date
	 * @param text
	 * @param tags
	 * @param comments
	 * @param published
	 */
	public Article(long id, String title, String author, String date,
			String text, List<String> tags, List<Comment> comments,
			boolean published) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.date = date;
		this.text = text;
		this.tags = tags;
		this.comments = comments;
		this.published = published;
	}
	/**
	 * 
	 */
	public Article() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
