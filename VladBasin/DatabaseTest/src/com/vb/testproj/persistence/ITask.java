package com.vb.testproj.persistence;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.vb.testproj.model.Task;
import com.vb.testproj.model.TaskStatus;

public interface ITask {
	boolean hasTask(int taskId);

	boolean hasAnyUncompletedTaskForUser(int userId);
	boolean hasAnyFailedTaskForUser(int userId);

	int addTask(String title, String content, String description, int toUserId,
			int createdByUserId, Date mustBeCompletedBeforeDate);
	
	void updateTask(int id, int completedPercent);

	void deleteTask(int id);

	void beginTask(int id);
	
	Task getTask(int id);

	Collection<Task> getUncompletedTasks(int forUserId, int skipCount,
			int takeCount);
	
	Collection<Task> getTasks(int forUserId, int skipCount, int takeCount,
			TaskStatus status);
}
