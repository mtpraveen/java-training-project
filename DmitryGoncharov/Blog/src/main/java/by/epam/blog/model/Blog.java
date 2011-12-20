package by.epam.blog.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmitry_Goncharov
 *
 */
public class Blog {
	private long id;
	private User author;
	private List<Topic> topics = new ArrayList<Topic>();
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public List<Topic> getTopics() {
		return topics;
	}
	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}
}
