package com.vb.project.model;

import java.io.Serializable;
import java.util.Date;

public class Notification implements Serializable{

	private static final long serialVersionUID = 88761639173L;
	
	private int id;
	
	private int forUserId;
	
	private String title;
	private String content;
	
	private Date creationDate;
	private Date notificatedDate;
	
	private boolean isNotificated;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getForUserId() {
		return forUserId;
	}

	public void setForUserId(int forUserId) {
		this.forUserId = forUserId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getNotificatedDate() {
		return notificatedDate;
	}

	public void setNotificatedDate(Date notificatedDate) {
		this.notificatedDate = notificatedDate;
	}

	public boolean isNotificated() {
		return isNotificated;
	}

	public void setNotificated(boolean isNotificated) {
		this.isNotificated = isNotificated;
	}
	
}
