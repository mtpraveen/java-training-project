package com.epam.logic.model;

public class Priority {
	
	// Fields names for table 'priority';
	public static final String PRIORITY_ID = "priority_id";
	public static final String PRIORITY = "priority";
	
	private int priorityId;	
	private String priority;
	
	public Priority() {}
	
	public Priority(int priorityId, String priority) {
		super();
		this.priorityId = priorityId;
		this.priority = priority;
	}
	
	public int getPriorityId() {
		return priorityId;
	}
	
	public void setPriorityId(int priorityId) {
		this.priorityId = priorityId;
	}
	
	public String getPriority() {
		return priority;
	}
	
	public void setPriority(String priority) {
		this.priority = priority;
	}

}
