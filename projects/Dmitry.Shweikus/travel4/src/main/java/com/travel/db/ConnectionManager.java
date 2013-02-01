package com.travel.db;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;
import org.hibernate.Session;

public class ConnectionManager
{
	private final static Logger LOGGER = Logger.getLogger(ConnectionManager.class);
	private static EntityManagerFactory entityManagerFactory = null;
	private static ThreadLocal<EntityManager> LocalStorage = new ThreadLocal<>();
	
	private ConnectionManager(){}
	
	//public static synchronized Session getHibernateSession()
	public static  Session getHibernateSession()
	{
		return (Session) getEntityManager().getDelegate();
	}
	public static  void closeSession()
	{
		EntityManager em = LocalStorage.get();
		if (em != null)
		{
			//em.close();
			LocalStorage.set(null);
		}
	}
	public static synchronized EntityManagerFactory getEntityManagerFactory()
	{
		if (entityManagerFactory == null)
		{
			Map<String, Object> configOverrides = new HashMap<String, Object>();
			String dbUrl = System.getenv("CLEARDB_DATABASE_URL");
			if(dbUrl != null)
			{
				URI dbUri;
				try
				{
					dbUri = new URI(dbUrl);
					String username = dbUri.getUserInfo().split(":")[0];
					String password = dbUri.getUserInfo().split(":")[1];
					String jdbcUrl = "jdbc:mysql://" + dbUri.getHost() + dbUri.getPath() + "?reconnect=true&autoReconnect=true";
			        configOverrides.put("hibernate.connection.username", username);    
			        configOverrides.put("hibernate.connection.password", password);    
			        configOverrides.put("hibernate.connection.url", jdbcUrl);    
				} catch (URISyntaxException e)
				{
					LOGGER.error("Invalid CLEARDB_DATABASE_URL : " + dbUrl + " using default persistence.xml configuration", e);
				}
			}
			else
				LOGGER.info("Env value CLEARDB_DATABASE_URL not found using default persistence.xml configuration");
			entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnit",configOverrides);
		}
                return entityManagerFactory;
	}
	
	public static  EntityManager getEntityManager()
	{
		EntityManager em = LocalStorage.get();
		if (em == null)
		{
			em = getEntityManagerFactory().createEntityManager();
			LocalStorage.set(em);
		}
		return em;
	}
}
