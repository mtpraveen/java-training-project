package com.vb.testproj.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public abstract class PersistenceBase {
	static final String PersistanceUnit = "DatabaseTest";

	EntityManagerFactory factory;
	EntityTransaction trans;
	
	protected EntityManager entityManager;
	
	protected void beginTransmition(){
		trans.begin();
	}
	protected void endTransmition(){
		trans.commit();
	}
	
	protected void addObject(Object obj) {
		trans.begin();
		entityManager.persist(obj);
		trans.commit();
	}
	protected void deleteObject(Object obj){
		trans.begin();
		entityManager.remove(obj);
		trans.commit();
	}
	protected void updateDatabase(){
		trans.begin();
		entityManager.flush();
		trans.commit();
	}
	
	protected PersistenceBase(){
		factory = Persistence.createEntityManagerFactory("DatabaseTest");

		entityManager = factory.createEntityManager();
		trans = entityManager.getTransaction();
	}
	
}
