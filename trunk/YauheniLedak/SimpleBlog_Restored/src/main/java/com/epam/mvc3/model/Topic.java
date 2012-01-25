package com.epam.mvc3.model;

import java.sql.Date;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "T_TOPIC")
public class Topic {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private User author;
	
	@Enumerated(EnumType.STRING)	
	private Tag topicTag;
	
	private String name;
	private String text;
	
	@OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
	private Collection<Comment> comments = new LinkedHashSet<Comment>(); 
	
	// We shouldn't read whole collection to get this basic info
	private int commentsCount;
	private Date timeLastUpdated;

	//private virtual List<Comment> commentsList;
	
	public Topic()
    {
        //TimeLastUpdated = DateTime.Now;
        commentsCount = 0;
        topicTag = Tag.world;
       // timeLastUpdated = DateTime.Now
       Calendar calendarInstance = Calendar.getInstance();
       /*
       // deprecated
       timeLastUpdated = new Date(calendarInstance.get(Calendar.YEAR),
    		   						calendarInstance.get(Calendar.MONTH),
    		   						calendarInstance.get(Calendar.DAY_OF_MONTH) );
    	*/
       timeLastUpdated = new java.sql.Date(calendarInstance.getTimeInMillis() );
    }
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getText() {
		return text;
	}
	
	public int getCommentsCount() {
		return commentsCount;
	}
	public void setCommentsCount(int commentsCount) {
		this.commentsCount = commentsCount;
	}
	public Date getTimeLastUpdated() {
		return timeLastUpdated;
	}
	public void setTimeLastUpdated(Date timeLastUpdated) {
		this.timeLastUpdated = timeLastUpdated;
	}
	
	public int getId() {
		return id;
	}
	public void setID(int id) {
		this.id = id;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * @return the authorID
	 */
	public User getAuthor() {
		return author;
	}
	
	/**
	 * @param authorID the authorID to set
	 */
	public void setAuthor(User author) {
		this.author = author;
	}
	
	/**
	 * @return the topicTag
	 */
	public Tag getTopicTag() {
		return topicTag;
	}
	
	/**
	 * @param topicTag the topicTag to set
	 */
	public void setTopicTag(Tag topicTag) {
		this.topicTag = topicTag;
	}
	/**
	 * @return the comments
	 */
	public Collection<Comment> getComments() {
		return comments;
	}
	/**
	 * @param comments the comments to set
	 */
	public void setComments(Collection<Comment> comments) {
		this.comments = comments;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return list of tags, from which we can choose
	 */
	public List<Tag> getTagLict()
	{
		List<Tag> result = Arrays.asList(Tag.values() );
		return result;

	}
	
}
