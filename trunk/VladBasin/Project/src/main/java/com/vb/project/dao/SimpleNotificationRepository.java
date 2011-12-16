package com.vb.project.dao;

import java.util.HashMap;
import java.util.Map;

import com.vb.project.model.Notification;

public class SimpleNotificationRepository implements INotificationRepository {

	private Map<Integer, Notification> notifications;

	public void init() {
		notifications = new HashMap<Integer, Notification>();
		for (int i = 0; i < 100; i++) {
			addNotification(i, "title " + i, "content" + i, 0);
		}

	}

	public void addNotification(int id, String title, String content, int forUserId) {
		Notification notif = new Notification();
		notif.setId(id);
		notif.setTitle(title);
		notif.setContent(content);
		notif.setForUserId(forUserId);

		notifications.put(notif.getId(), notif);
	}

	public void getNotificationById(int id) {
		// TODO Auto-generated method stub

	}

	public void removeNotification(int id) {
		// TODO Auto-generated method stub

	}

	public Map<Integer, Notification> getAllNotifications() {
		return notifications;
	}

}
