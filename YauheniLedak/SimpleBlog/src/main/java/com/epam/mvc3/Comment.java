package com.epam.mvc3;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Comment")
public class Comment
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private int ID;
	
	private int TopicID;
    
	@OneToMany
    private int AuthorID;

    private String Text;
    private Date CreateDate;
    
    
	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}
	/**
	 * @param iD the iD to set
	 */
	public void setID(int iD) {
		ID = iD;
	}
	/**
	 * @return the topicID
	 */
	public int getTopicID() {
		return TopicID;
	}
	/**
	 * @param topicID the topicID to set
	 */
	public void setTopicID(int topicID) {
		TopicID = topicID;
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
	/**
	 * @return the text
	 */
	public String getText() {
		return Text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		Text = text;
	}
	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return CreateDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		CreateDate = createDate;
	}
    
    //public virtual Topic topic { get; set; }
    
}

