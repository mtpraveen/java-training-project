package by.brsu.java.training.util;

import java.io.File;

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

public class SessionUtil {
	// private static SessionFactory SESSION_FACTORY = new Configuration()
	// .configure("hibernate.cfg.xml").buildSessionFactory();

	private static SessionFactory sessionFactory;

	static {
		try {
			sessionFactory = new Configuration().configure(
					new File("WebContent/WEB-INF/hibernate.cfg.xml"))
					.buildSessionFactory();

		} catch (Throwable e) {
			e.printStackTrace();
		} finally {

		}
	}

	// public static SessionFactory getSessionFactory() {
	// return SESSION_FACTORY;
	// }

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
