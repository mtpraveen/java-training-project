package com.vb.testproj.persistence;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public abstract class PersistenceBase {
	static final String PersistanceUnit = "DatabaseTest";

	EntityManagerFactory factory;
	EntityTransaction trans;

	protected EntityManager entityManager;

	protected void beginTransmition() {
		trans.begin();
	}

	protected void endTransmition() {
		trans.commit();
	}

	protected void addObject(Object obj) {
		if (obj == null) return;
		trans.begin();
		entityManager.persist(obj);
		trans.commit();
	}

	protected void deleteObject(Object obj) {
		if (obj == null) return;
		trans.begin();
		entityManager.remove(obj);
		trans.commit();
	}

	protected void updateDatabase() {
		trans.begin();
		entityManager.flush();
		trans.commit();
	}

	protected Object getSingleObject(String queryString, Object... params) {
		beginTransmition();
		Query query = entityManager.createQuery(queryString);
		for (int i = 0; i < params.length; i++)
			query.setParameter(i + 1, params[i]);
		query.setMaxResults(1);
		List temp = query.getResultList();
		endTransmition();
		return temp.size() > 0 ? temp.get(0) : null;
	}

	protected List<Object> getManyObjects(int skipCount, int takeCount,
			String queryString, Object... params) {
		beginTransmition();

		Query query = entityManager.createQuery(queryString);
		for (int i = 0; i < params.length; i++)
			query.setParameter(i + 1, params[i]);

		query.setFirstResult(skipCount);
		query.setMaxResults(takeCount);
		List<Object> res = query.getResultList();

		endTransmition();

		return res;
	}

	protected PersistenceBase() {
		factory = Persistence.createEntityManagerFactory("DatabaseTest");

		entityManager = factory.createEntityManager();
		trans = entityManager.getTransaction();
	}

}
