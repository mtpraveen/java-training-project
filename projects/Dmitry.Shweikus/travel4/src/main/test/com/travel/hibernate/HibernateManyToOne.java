/**
 * 
 */
package com.travel.hibernate;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.travel.dao.ClientDao;
import com.travel.dao.OrderDao;
import com.travel.dao.PaymentDao;
import com.travel.dao.TestDatabaseDeleter;
import com.travel.dao.TourDao;
import com.travel.dao.TourSheduleDao;
import com.travel.dao.UserDao;
import com.travel.db.ConnectionManager;
import com.travel.enums.TransportKind;
import com.travel.enums.TravelKind;
import com.travel.exceptions.DbSqlException;
import com.travel.pojo.Client;
import com.travel.pojo.Order;
import com.travel.pojo.Payment;
import com.travel.pojo.Tour;
import com.travel.pojo.TourShedule;
import com.travel.pojo.User;

/**
 * @author dima
 *
 */
public class HibernateManyToOne
{

	/**
	 * @throws java.lang.Exception
	 */
	ClientDao clientDao = new ClientDao();
	TourDao tourDao = new TourDao();
	TourSheduleDao arrivalSheduleDao = new TourSheduleDao();
	UserDao userDao = new UserDao();
	OrderDao orderDao = new OrderDao();
	PaymentDao paymentDao = new PaymentDao();
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	@After
	public void setUp() throws Exception
	{
		TestDatabaseDeleter.clearDatabase();
	}

	@Test
	public void test() throws DbSqlException
	{
		User user = userDao.createUser("test", "manager1", "123", false);
		Client client = clientDao.create("ivan", "ivanov", "123", "", "", "", "---");
		Tour tour = tourDao.create("tour1", TransportKind.TRAIN, TravelKind.REST, "----", "---", 1);
		@SuppressWarnings("deprecation")
		Date date = new Date(2012-1900, 11, 9);
		TourShedule arrivalShedule = arrivalSheduleDao.create(tour, date, 700, 20);
		Order order = orderDao.create(client, arrivalShedule, user, date, 2, 700*2, "-", false, null);
		Payment payment = paymentDao.create(order, 700.00, date);
		payment.getOrder().setCount(7);
		System.out.println("All creating done");
		Session session = ConnectionManager.getHibernateSession();
		payment = (Payment) session.byId(Payment.class).getReference(payment.getId());
		System.out.println("Find done");
		payment.getOrder().setCount(7);
		System.out.println("Test done");
	}

}
