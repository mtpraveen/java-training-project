/**
 * 
 */
package com.travel.hibernate;

import static org.junit.Assert.*;

import java.util.Dictionary;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;

import com.travel.dao.DiscountDao;
import com.travel.dao.TourDao;
import com.travel.db.ConnectionManager;
import com.travel.enums.TransportKind;
import com.travel.enums.TravelKind;
import com.travel.exceptions.DbSqlException;
import com.travel.pojo.Client;
import com.travel.pojo.Discount;
import com.travel.pojo.Tour;

/**
 * @author dima
 *
 */
public class HibernateTest
{

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
	}

	@Test
	public void testSimpleQuery() throws DbSqlException
	{
		TourDao dao = new TourDao();
		dao.create("Test111", TransportKind.BUS, TravelKind.REST, "----", "------", 1);
		
		Query query = ConnectionManager.getEntityManager().createQuery("SELECT x FROM " + Tour.class.getSimpleName() + " x");
		List<Tour> list = query.getResultList();
		for (Tour tour : list)
		{
			assertEquals(tour.getClass().getSimpleName(), "Tour");
		}
		assertTrue(list.size() > 0);
	}
}
