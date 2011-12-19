package com.vb.testproj.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.vb.testproj.model.Task;
import com.vb.testproj.model.TaskStatus;
import com.vb.testproj.persistence.ITask;
import com.vb.testproj.persistence.PersistenceBase;

public class TaskService extends PersistenceBase implements ITask {

	@Override
	public boolean hasTask(int taskId) {
		return getTask(taskId) != null;
	}

	@Override
	public boolean hasAnyUncompletedTaskForUser(int userId) {
		return getUncompletedTasks(userId, 0, 1).size() > 0;
	}

	@Override
	public int addTask(String title, String content, String description,
			int toUserId, int createdByUserId, Date mustBeCompletedBeforeDate) {
		try {
			Task t = new Task(title, content, description, toUserId,
					createdByUserId, mustBeCompletedBeforeDate);
			addObject(t);

			return t.getId();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}

	@Override
	public void updateTask(int id, int completedPercent) {
		Task t = getTask(id);
		if (t.isFailed() || t.isCompleted())
			return;

		completedPercent = Math.min(completedPercent, 100);
		t.setCompletedPercent(completedPercent);
		
		if (t.getTaskStatus() != TaskStatus.INPROGRESS)
			t.setStatus(TaskStatus.INPROGRESS);
		
		if (completedPercent == 100)
			t.setStatus(TaskStatus.COMPLETED);

		updateDatabase();
	}

	@Override
	public void deleteTask(int id) {
		Task t = getTask(id);
		deleteObject(t);
	}

	@Override
	public Task getTask(int id) {
		Task t = (Task)getSingleObject("SELECT t FROM Task t WHERE t.id = ?", id);
		
		return t;
	}

	@Override
	public List<Task> getTasks(int forUserId, int skipCount,
			int takeCount, TaskStatus status) {
		return (List)getManyObjects(skipCount, takeCount, "SELECT t FROM Task t WHERE t.status = ?", status.ordinal());
	}
	
	@Override
	public List<Task> getUncompletedTasks(int forUserId, int skipCount,
			int takeCount) {
		return (List)getManyObjects(skipCount, takeCount, "SELECT t FROM Task t WHERE t.status = ? OR t.status = ?", TaskStatus.INPROGRESS.ordinal(), TaskStatus.SCHEDULED.ordinal());
	}

	
	public Collection<Task> getAllTasks(int forUserId, int skipCount,
			int takeCount) {
		return (List)getManyObjects(skipCount, takeCount, "SELECT t FROM Task t");
	}
	
	@Override
	public boolean hasAnyFailedTaskForUser(int userId) {
		return getTasks(userId, 0, 1, TaskStatus.FAILED).size() > 0;
	}

	@Override
	public void beginTask(int id) {
		Task t = getTask(id);
		t.setStatus(TaskStatus.SCHEDULED);
		
		updateDatabase();
	}
	


}
