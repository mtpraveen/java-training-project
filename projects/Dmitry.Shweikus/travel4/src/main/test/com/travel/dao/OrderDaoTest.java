/**
 * 
 */
package com.travel.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
import com.travel.pojo.Tour;
import com.travel.pojo.User;

/**
 * @author dima
 *
 */
public class OrderDaoTest
{
	ClientDao clientDao = new ClientDao();
	TourDao tourDao = new TourDao();
	TourSheduleDao arrivalSheduleDao = new TourSheduleDao();
	UserDao userDao = new UserDao();
	OrderDao orderDao = new OrderDao();
	
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
		Tour tour = tourDao.create("-----", TransportKind.TRAIN, TravelKind.REST, "----", "---", 1);
		@SuppressWarnings("deprecation")
		Date date = new Date(2012-1900,11, 9);
		TourShedule arrivalShedule = arrivalSheduleDao.create(tour, date, 700, 20);
		Order order = orderDao.create(client, arrivalShedule, user, date, 2, 700*2, "-", false, null);
		Order order1 = orderDao.findById(order.getId());
		
		assertNotNull(order);
		assertEquals(client.getId(), order.getClient().getId());
		assertNull(order.getFinishedDate());
		
		order.setFinishedDate(date);
		orderDao.update(order);
	}

}
