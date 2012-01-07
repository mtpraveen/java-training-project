package by.epam.blog.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Dmitry_Goncharov
 *
 */
@Entity
@Table(name = "topic")
public class Topic implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "topic_caption")
	private String caption;
	
	@Column(name = "topic_text")
	private String text;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="blog_id")
	private Blog blog;
	
	//private List<String> tags = new ArrayList<String>();
	
	@OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
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
	/*
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	*/
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Blog getBlog() {
		return blog;
	}
	public void setBlog(Blog blog) {
		this.blog = blog;
	}
	public Topic() {
		super();
	}
	
}
