package com.vb.project.dao;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.vb.project.model.Notification;

public class SimpleNotificationRepository implements INotificationRepository {

	EntityManagerFactory factory;
	EntityManager em;

	public void init() {
		// notifications = new HashMap<Integer, Notification>();
		// for (int i = 0; i < 100; i++) {
		// addNotification(i, "title " + i, "content" + i, 0);
		// }
		factory = Persistence.createEntityManagerFactory("Database");
		

		// t.begin();
		//
		// Notification notify = new Notification();
		// notify.setContent("content123123123123");
		// notify.setTitle("title123123123123123");
		//
		// em.persist(notify);
		// t.commit();

	}

	public void addNotification(int id, String title, String content,
			int forUserId) {
//		Notification notif = new Notification();
//		notif.setId(id);
//		notif.setTitle(title);
//		notif.setContent(content);
//		notif.setForUserId(forUserId);
//
//		notifications.put(notif.getId(), notif);
	}

	public Notification getNotificationById(int id) {
		// TODO Auto-generated method stub
		em = factory.createEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		Notification notify = (Notification) em
				.createQuery("select n from Notification n order by n.id desc")
				.setMaxResults(1).getSingleResult();

		t.commit();

		em.close();
		
		return notify;
	}

	public void removeNotification(int id) {
		// TODO Auto-generated method stub

	}

	

}
