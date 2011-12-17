package com.vb.testproj.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the notifications database table.
 * 
 */
@Entity
@Table(name="notifications")
public class Notification implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String content;

    @Temporal( TemporalType.TIMESTAMP)
	private Date creationDate;

	private int forUserId;

	@Column(nullable = true)
	private Byte isNotificated;

    @Temporal( TemporalType.TIMESTAMP)
	private Date notificatedDate;

	private String title;

    public Notification(int forUserId, String content, String title) {
		super();
		this.content = content;
		this.forUserId = forUserId;
		this.title = title;
		
		this.creationDate = new Date();
		this.isNotificated = 0;
	}

	public Notification() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public int getForUserId() {
		return this.forUserId;
	}

	public void setForUserId(int forUserId) {
		this.forUserId = forUserId;
	}

	public byte getIsNotificated() {
		return this.isNotificated;
	}

	public void setIsNotificated(byte isNotificated) {
		this.isNotificated = isNotificated;
	}

	public Date getNotificatedDate() {
		return this.notificatedDate;
	}

	public void setNotificatedDate(Date notificatedDate) {
		this.notificatedDate = notificatedDate;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result
				+ ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + forUserId;
		result = prime * result + id;
		result = prime * result + isNotificated;
		result = prime * result
				+ ((notificatedDate == null) ? 0 : notificatedDate.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Notification other = (Notification) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (forUserId != other.forUserId)
			return false;
		if (id != other.id)
			return false;
		if (isNotificated != other.isNotificated)
			return false;
		if (notificatedDate == null) {
			if (other.notificatedDate != null)
				return false;
		} else if (!notificatedDate.equals(other.notificatedDate))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Notification [id=" + id + ", content=" + content + ", title="
				+ title + ", isNotificated=" + isNotificated + ", forUserId=" + forUserId + "]";
	}

}