package com.vb.project.dao;

import java.util.Map;

import com.vb.project.model.Notification;

public interface INotificationRepository {
	
	Map<Integer, Notification> getAllNotifications();
	
	void addNotification(int id, String title, String content, int forUserId);
	void getNotificationById(int id);
	void removeNotification(int id);
	
}
