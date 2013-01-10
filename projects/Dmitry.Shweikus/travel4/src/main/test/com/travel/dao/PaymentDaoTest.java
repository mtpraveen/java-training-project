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

import com.travel.db.ApplicationException;
import com.travel.enums.TransportKind;
import com.travel.enums.TravelKind;
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
		orderDao.deleteAll();
		arrivalSheduleDao.deleteAll();
		tourDao.deleteAll();
		clientDao.deleteAll();
		userDao.deleteAll();
		paymentDao.deleteAll();
	}

	/**
	 * Test method for {@link com.travel.dao.BaseDao#update(com.travel.pojo.BaseEntity)}.
	 * @throws ApplicationException 
	 */
	@Test
	public void testUpdate() throws ApplicationException
	{
		User user = userDao.createUser("test", "manager1", "123", false);
		Client client = clientDao.create("ivan", "ivanov", "", "", "", "", "--");
		Tour tour = tourDao.create("-", TransportKind.TRAIN, TravelKind.REST, "-", "--", 1);
		@SuppressWarnings("deprecation")
		Date date = new Date(2012-1900, 11, 9);
		TourShedule arrivalShedule = arrivalSheduleDao.create(tour, date, BigDecimal.valueOf(700), 20);
		Order order = orderDao.create(client, arrivalShedule, user, date, 2, BigDecimal.valueOf(700*2), "-", false, null);
		Payment payment = paymentDao.create(order, BigDecimal.valueOf(700.00), date);
		assertNotNull(payment);
		assertEquals(order.getId(), payment.getOrderId());
		assertEquals(700, payment.getAmount().intValue());
		assertEquals(date, payment.getDate());
		
		//assertTrue(paymentDao.delete(payment));
		//assertNull(paymentDao.findById(payment.getId()));
	}

}
