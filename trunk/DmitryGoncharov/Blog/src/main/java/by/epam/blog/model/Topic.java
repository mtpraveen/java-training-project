package by.epam.blog.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Dmitry_Goncharov
 *
 */
//@Entity
//@Table(name = "TOPIC")
public class Topic implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//@Column(name = "TOPIC_CAPTION")
	private String caption;
	//@Column(name = "TOPIC_TEXT")
	private String text;
	
	private List<String> tags = new ArrayList<String>();
	
	private List<Comment> comments = new ArrayList<Comment>();
	
	public Topic(String caption) {
		super();
		this.caption = caption;
	}
	public Topic(String caption,String text) {
		super();
		this.caption = caption;
		this.text = text;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
}
