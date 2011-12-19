package com.vb.testproj.service;

import java.util.Date;
import java.util.List;

import com.vb.testproj.model.Notification;
import com.vb.testproj.persistence.INotification;
import com.vb.testproj.persistence.PersistenceBase;

public class NotificationService extends PersistenceBase implements
		INotification {

	public NotificationService() {
		super();
	}

	@Override
	public int addNotification(int forUserId, String title, String content) {
		Notification notify = new Notification(forUserId, title, content);

		addObject(notify);

		int resultId = getNotification(title, content);
		return resultId;
	}

	private int getNotification(String title, String content) {
		return ((Notification)getSingleObject("SELECT n FROM Notification n WHERE n.title = ? AND n.content = ?", title, content)).getId();
	}

	public Notification getNotification(int id) {
		return (Notification)getSingleObject("SELECT n FROM Notification n WHERE n.id = ?", id);
	}

	@Override
	public void updateNotification(int id) {
		Notification notif = getNotification(id);
		notif.setIsNotificated((byte) 1);
		notif.setNotificatedDate(new Date());

		updateDatabase();
	}

	@Override
	public void deleteNotification(int id) {
		Notification notif = getNotification(id);
		deleteObject(notif);
	}

	@Override
	public boolean hasNotification(int notifId) {
		return getNotification(notifId) != null;
	}

	@Override
	public List<Notification> getNewNotifications(int forUserId,
			int skipCount, int takeCount) {
		return (List)getManyObjects(skipCount, takeCount, "SELECT n FROM Notification n WHERE n.forUserId = ?", forUserId);
	}
}
