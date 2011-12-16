package com.vb.project.dao;

import java.util.Map;

import com.vb.project.model.Notification;

public interface INotificationRepository {
	
	static final String PersistanceUnit ="Database"; 
	
	
	void addNotification(int id, String title, String content, int forUserId);
	Notification getNotificationById(int id);
	void removeNotification(int id);
	
}
