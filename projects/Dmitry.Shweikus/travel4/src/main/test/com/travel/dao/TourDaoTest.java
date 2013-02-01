/**
 * 
 */
package com.travel.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.travel.enums.TransportKind;
import com.travel.enums.TravelKind;
import com.travel.exceptions.DbSqlException;
import com.travel.pojo.Tour;

/**
 * @author dima
 *
 */
public class TourDaoTest
{
	TourDao dao = new TourDao();
	@Before
	public void setUp() throws Exception
	{
		TestDatabaseDeleter.clearDatabase();
	}

	@Test
	@After
	public void testUpdate() throws DbSqlException
	{
		Tour tour1 = dao.create("tour1", TransportKind.BUS, TravelKind.REST, "---", "none", 1);
		Tour tour2 = dao.create("tour2", TransportKind.AVIA, TravelKind.SHOPPING, "---", "none", 2);
		Tour tour2a = dao.findById(tour2.getId());
		assertNotNull(tour1);
		assertNotNull(tour2);
		
		tour2.setTransportKind(TransportKind.TRAIN);
		tour2 = dao.update(tour2);
		Tour tour2b = dao.findById(tour2.getId());
		
		assertEquals(tour2, tour2b);
		
		dao.delete(tour1);
		assertNull(dao.findById(tour1.getId()));
	}

}
