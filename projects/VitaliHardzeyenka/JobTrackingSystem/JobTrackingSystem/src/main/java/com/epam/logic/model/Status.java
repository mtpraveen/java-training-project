package com.epam.logic.model;

public class Status {
	
	// Fields names for table 'statuses'.
	public static final String STATUS_ID = "status_id";
	public static final String STATUS = "status";
	
	private int statusId;
	private String status;
	
	public Status() {}
	
	public Status(int statusId, String status) {
		super();
		this.statusId = statusId;
		this.status = status;
	}
	
	public int getStatusId() {
		return statusId;
	}
	
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
}
