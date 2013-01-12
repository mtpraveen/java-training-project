/**
 * 
 */
package com.travel.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.travel.enums.TransportKind;
import com.travel.enums.TravelKind;
import com.travel.exceptions.DbSqlException;
import com.travel.pojo.Tour;
import com.travel.pojo.TourProgram;

/**
 * @author dima
 *
 */
public class TourProgramDaoTest
{
	TourProgramDao dao = new TourProgramDao();
	TourDao tourDao = new TourDao();

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	@After
	public void setUp() throws Exception
	{
		dao.deleteAll();
		tourDao.deleteAll();
	}

	/**
	 * Test method for {@link com.travel.dao.BaseDao#update(com.travel.pojo.BaseEntity)}.
	 * @throws DbSqlException 
	 */
	@Test
	public void testUpdate() throws DbSqlException
	{
		Tour tour = tourDao.create("-", TransportKind.TRAIN, TravelKind.REST, "-", "--", 1);
		TourProgram tourProgram1  = dao.create(tour, 1, "first day", 0);
		TourProgram tourProgram2  = dao.create(tour, 2, "first day", 3);
		TourProgram tourProgram1a = dao.findById(tourProgram1.getId());
		
		assertNotNull(tourProgram1);
		assertNotNull(tourProgram2);
		assertEquals(tourProgram1, tourProgram1a);
		
		assertEquals(tour.getId(), tourProgram1.getTourId());
		assertEquals(1, tourProgram1.getDayNumber());
		assertEquals("first day", tourProgram1.getDescription());
		assertEquals(0, tourProgram1.getLastDayNumber());
		
		tourProgram1.setDayNumber(5);
		tourProgram1.setDescription("day 5");
		tourProgram1.setLastDayNumber(7);
		
		assertTrue(dao.update(tourProgram1));
		
		TourProgram tourProgram1b = dao.findById(tourProgram1.getId());
		assertEquals(tourProgram1, tourProgram1b);
		assertFalse(tourProgram1a.equals(tourProgram1b));
		
		assertTrue(dao.delete(tourProgram2));
		assertNull(dao.findById(tourProgram2.getId()));
		assertNotNull(dao.findById(tourProgram1.getId()));
	}

}
