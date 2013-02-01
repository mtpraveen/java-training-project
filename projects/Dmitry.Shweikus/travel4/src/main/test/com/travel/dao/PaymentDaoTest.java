/**
 * 
 */
package com.travel.dao;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.travel.enums.TransportKind;
import com.travel.enums.TravelKind;
import com.travel.exceptions.DbSqlException;
import com.travel.pojo.TourShedule;
import com.travel.pojo.Client;
import com.travel.pojo.Order;
import com.travel.pojo.Payment;
import com.travel.pojo.Tour;
import com.travel.pojo.User;

/**
 * @author dima
 *
 */
public class PaymentDaoTest
{
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

	/**
	 * Test method for {@link com.travel.dao.BaseDao#update(com.travel.pojo.BaseEntity)}.
	 * @throws DbSqlException 
	 */
	@Test
	public void testUpdate() throws DbSqlException
	{
		User user = userDao.createUser("test", "manager1", "123", false);
		Client client = clientDao.create("ivan", "ivanov", "123", "", "", "", "---");
		Tour tour = tourDao.create("tour1", TransportKind.TRAIN, TravelKind.REST, "----", "---", 1);
		@SuppressWarnings("deprecation")
		Date date = new Date(2012-1900, 11, 9);
		TourShedule arrivalShedule = arrivalSheduleDao.create(tour, date, 700, 20);
		Order order = orderDao.create(client, arrivalShedule, user, date, 2, 700*2, "-", false, null);
		Payment payment = paymentDao.create(order, 700.0, date);
		assertNotNull(payment);
		assertEquals(order, payment.getOrder());
		assertEquals(700, payment.getAmount());
		assertEquals(date, payment.getDate());
		
		//assertTrue(paymentDao.delete(payment));
		//assertNull(paymentDao.findById(payment.getId()));
	}

}
