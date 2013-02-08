package com.epam.logic.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gordeenko_XP
 * Use as java bean.
 */
public class Jobs {
	
	private List<Job> jobs = new ArrayList<>();
	
	public Jobs() {}
	
	public Jobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

}
