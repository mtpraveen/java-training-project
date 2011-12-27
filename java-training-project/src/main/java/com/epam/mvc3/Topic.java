package com.epam.mvc3;

import java.sql.Date;
import java.lang.String;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Topic")
public class Topic {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int ID;
	
	@OneToOne
	private int AuthorID;
	
	private String Title;
	private String Text;
	private String PathToPicture;
	private int CommentsCount;
	private Date TimeLastUpdated;

	//private virtual List<Comment> commentsList;
	
	public Topic()
    {
        //TimeLastUpdated = DateTime.Now;
        CommentsCount = 0;

    }
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getText() {
		return Text;
	}
	public void setBody(String text) {
		Text = text;
	}
	public int getCommentsCount() {
		return CommentsCount;
	}
	public void setCommentsCount(int commentsCount) {
		CommentsCount = commentsCount;
	}
	public Date getTimeLastUpdated() {
		return TimeLastUpdated;
	}
	public void setTimeLastUpdated(Date timeLastUpdated) {
		TimeLastUpdated = timeLastUpdated;
	}

	public String getPathToPicture() {
		return PathToPicture;
	}
	
	public void setPathToPicture(String pathToPicture) {
		PathToPicture = pathToPicture;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public void setText(String text) {
		Text = text;
	}
	/**
	 * @return the authorID
	 */
	public int getAuthorID() {
		return AuthorID;
	}
	/**
	 * @param authorID the authorID to set
	 */
	public void setAuthorID(int authorID) {
		AuthorID = authorID;
	}
	
	

}
