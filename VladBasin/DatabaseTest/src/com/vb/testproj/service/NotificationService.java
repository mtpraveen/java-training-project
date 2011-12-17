package com.vb.testproj.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

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
		beginTransmition();

		Query query = entityManager
				.createQuery("SELECT n FROM Notification n WHERE n.title = :title AND n.content = :content");
		query.setParameter("title", title);
		query.setParameter("content", content);

		int res = ((Notification) query.setMaxResults(1).getSingleResult())
				.getId();

		endTransmition();

		return res;
	}

	public Notification getNotification(int id) {
		// System.out.println(id);

		beginTransmition();

		Query query = entityManager
				.createQuery("SELECT n FROM Notification n WHERE n.id = :id");
		query.setParameter("id", id);

		List<Notification> allResults = query.getResultList();

		Notification res = null;

		if (allResults.size() != 0)
			res = allResults.get(0);

		endTransmition();

		return res;
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
	public Collection<Notification> getNewNotifications(int forUserId,
			int skipCount, int takeCount) {
		List<Notification> res = new ArrayList<Notification>();

		beginTransmition();
		Query query = entityManager
				.createQuery("SELECT n FROM Notification n WHERE n.forUserId = :forUserId");
		query.setParameter("forUserId", forUserId);
		query.setFirstResult(skipCount);
		query.setMaxResults(takeCount);
		res = query.getResultList();
		endTransmition();

		return res;
	}
}
