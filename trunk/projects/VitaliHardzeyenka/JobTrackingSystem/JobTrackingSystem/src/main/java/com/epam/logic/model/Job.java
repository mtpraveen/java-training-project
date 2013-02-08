package com.epam.logic.model;

import java.util.Date;

/**
 * @author Gordeenko_XP
 * Class describe jobs (tasks) as they existing in data base. 
 */
public class Job {
	
	// Fields name for table 'jobs'.
	public static final String JOB_ID = "job_id";
	public static final String JOB_NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String PERCENTS = "percents";
	public static final String BEGIN_DATE = "begin_date";
	public static final String END_DATE = "end_date";
	public static final String STATUS_ID = "status_id";
	public static final String PRIORITY_ID = "priority_id";
	public static final String WORKER_ID = "worker_id";
	public static final String MANAGER_ID = "manager_id";
	
	private int id; // id of job
	private String name; // name of the job
	private String description; // description of the job
	private int percents; // the percentage of completion 
	private Date beginDate; // date when job must be started to work
	private Date endDate; // date when job must be finished
	private Status status; // current status of job
	private Priority priority; // priority of job
	private User worker; // worker that process this job
	private User manager; // manager that issued this job
	
	/**
	 * Constructor
	 */
	public Job() {}
	
	public Job(String name, String description, int percents, Date beginDate,
			Date endDate, Status status, Priority priority, User worker, User manager) {
		super();
		this.name = name;
		this.description = description;
		this.percents = percents;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.status = status;
		this.priority = priority;
		this.worker = worker;
		this.manager = manager;
	}
	
	public Job(int id, String name, String description, int percents, Date beginDate,
			Date endDate, Status status, Priority priority, User worker, User manager) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.percents = percents;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.status = status;
		this.priority = priority;
		this.worker = worker;
		this.manager = manager;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getPercents() {
		return percents;
	}
	
	public void setPercents(int percents) {
		this.percents = percents;
	}
	
	public Date getBeginDate() {
		return beginDate;
	}
	
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public Priority getPriority() {
		return priority;
	}
	
	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	
	public User getWorker() {
		return worker;
	}
	
	public void setWorker(User worker) {
		this.worker = worker;
	}
	
	public User getManager() {
		return manager;
	}
	
	public void setManager(User manager) {
		this.manager = manager;
	}
	
}
