package com.vb.testproj.persistence;

import java.util.Collection;

import com.vb.testproj.model.Notification;


public interface INotification {
	
	boolean hasNotification(int notifId);
	
	int addNotification(int toUser, String title, String content);
	void updateNotification(int id);
	void deleteNotification(int id);
	
	Notification getNotification(int id);

	Collection<?> getNewNotifications(int forUserId, int skipCount, int takeCount);
}
