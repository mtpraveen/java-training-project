package com.vb.testproj.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tasks database table.
 * 
 */
@Entity
@Table(name="tasks")
public class Task implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String title;
	private String content;
	private String description;

	private int status;
	private int completedPercent;
	
	private int createdByUserId;
	private int toUserId;

    @Temporal( TemporalType.TIMESTAMP)
	private Date creationDate;


    @Temporal( TemporalType.TIMESTAMP)
	private Date lastUpdateDate;

    @Temporal( TemporalType.TIMESTAMP)
	private Date mustBeCompletedBeforeDate;



    public Task() {
    }
    public Task(String title, String content, String description,
			int toUserId, int createdByUserId, Date mustBeCompletedBeforeDate) {
		super();
		this.content = content;
		this.createdByUserId = createdByUserId;
		this.description = description;
		this.title = title;
		this.toUserId = toUserId;
		this.mustBeCompletedBeforeDate = mustBeCompletedBeforeDate;
		
		this.creationDate = new Date();
		this.completedPercent = 0;
		this.status = TaskStatus.CREATED.ordinal();
	}


	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCompletedPercent() {
		return this.completedPercent;
	}

	public void setCompletedPercent(int completedPercent) {
		this.completedPercent = completedPercent;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getCreatedByUserId() {
		return this.createdByUserId;
	}

	public void setCreatedByUserId(int createdByUserId) {
		this.createdByUserId = createdByUserId;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getLastUpdateDate() {
		return this.lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public Date getMustBeCompletedBeforeDate() {
		return this.mustBeCompletedBeforeDate;
	}

	public void setMustBeCompletedBeforeDate(Date mustBeCompletedBeforeDate) {
		this.mustBeCompletedBeforeDate = mustBeCompletedBeforeDate;
	}

	public int getStatus() {
		return this.status;
	}
	public TaskStatus getTaskStatus() {
		return TaskStatus.values()[this.status];
	}
	public void setStatus(TaskStatus status){
		this.setStatus(status.ordinal());
	}
	public void setStatus(int status) {
		this.status = status;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getToUserId() {
		return this.toUserId;
	}

	public void setToUserId(int toUserId) {
		this.toUserId = toUserId;
	}
	public boolean isFailed(){
		return this.status == TaskStatus.FAILED.ordinal();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + completedPercent;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + createdByUserId;
		result = prime * result
				+ ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((lastUpdateDate == null) ? 0 : lastUpdateDate.hashCode());
		result = prime
				* result
				+ ((mustBeCompletedBeforeDate == null) ? 0
						: mustBeCompletedBeforeDate.hashCode());
		result = prime * result + status;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + toUserId;
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
		Task other = (Task) obj;
		if (completedPercent != other.completedPercent)
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (createdByUserId != other.createdByUserId)
			return false;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (lastUpdateDate == null) {
			if (other.lastUpdateDate != null)
				return false;
		} else if (!lastUpdateDate.equals(other.lastUpdateDate))
			return false;
		if (mustBeCompletedBeforeDate == null) {
			if (other.mustBeCompletedBeforeDate != null)
				return false;
		} else if (!mustBeCompletedBeforeDate
				.equals(other.mustBeCompletedBeforeDate))
			return false;
		if (status != other.status)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (toUserId != other.toUserId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Task [id=" + id + ", title=" + title + ", content=" + content
				+ ", description=" + description + ", status=" + TaskStatus.values()[status]
				+ ", completedPercent=" + completedPercent
				+ ", createdByUserId=" + createdByUserId + ", toUserId="
				+ toUserId + ", creationDate=" + creationDate
				+ ", lastUpdateDate=" + lastUpdateDate
				+ ", mustBeCompletedBeforeDate=" + mustBeCompletedBeforeDate
				+ "]";
	}
	public boolean isCompleted() {
		return status == TaskStatus.COMPLETED.ordinal();
	}

}