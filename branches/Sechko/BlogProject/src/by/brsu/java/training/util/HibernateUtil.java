package by.brsu.java.training.util;

import java.io.File;

import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.hibernate.SessionFactory;

public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			Configuration cfg = new Configuration();
		  	return cfg.configure(
					"hibernate.cfg.xml"
					)
					.buildSessionFactory();
		} catch (Throwable ex) {
			// TODO:: Logging exception here
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
