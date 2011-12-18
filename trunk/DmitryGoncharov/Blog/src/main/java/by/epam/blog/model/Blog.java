package by.epam.blog.model;

import java.util.List;
import javax.persistence.*;

/**
 * @author Dmitry_Goncharov
 *
 */
public class Blog {
	private User author;
	private List<Topic> topics;
	
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
