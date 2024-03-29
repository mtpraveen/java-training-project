/**
 * 
 */
package com.travel.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.travel.exceptions.DbSqlException;
import com.travel.pojo.User;

/**
 * @author dima
 *
 */
public class UserDaoTest
{
	UserDao dao = new UserDao();

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
		User user  = dao.createUser("main administrator", "admin", "123", true);
		User user1 = dao.findUserByLoginAndPassword(user.getLogin(), user.getPassword());
		assertNotNull(user1);
		assertEquals(user, user1);
		user1 = dao.createUser("user", "user", "123", false);
		User user2 = dao.createUser("user2", "user2", "123", false);
		assertNotNull(user);
		assertNotNull(user1);
		assertNotNull(user2);
		
		assertNotNull(dao.findUserByLoginAndPassword("user", "123"));
		assertEquals(user1, dao.findUserByLoginAndPassword("user", "123"));
		
		user1.setAdmin(true);
		user1.setLogin("user1b");
		user1.setName("User1b");
		user1.setPassword("1234");
		
		dao.update(user1);
		
		assertEquals(user, dao.findById(user.getId()));
		assertEquals(user1, dao.findById(user1.getId()));
		assertEquals(user2, dao.findById(user2.getId()));		
	}

	/**
	 * Test method for {@link com.travel.dao.BaseDao#delete(int)}.
	 * @throws DbSqlException 
	 */
	@Test
	public void testDelete() throws DbSqlException
	{
		User user  = dao.createUser("main administrator", "admin", "123", true);
		User user1 = dao.createUser("user", "user", "123", false);
		User user2 = dao.createUser("user2", "user2", "123", false);
		assertNotNull(user);
		assertNotNull(user1);
		assertNotNull(user2);
		
		dao.delete(user1.getId());
		
		assertNotNull(dao.findById(user.getId()));
		assertNull(dao.findById(user1.getId()));
		assertNotNull(dao.findById(user2.getId()));
		
		dao.deleteAll();
		assertEquals(dao.findAll().size(), 0);
	}
	@Test
	public void testFindAll() throws DbSqlException
	{
		dao.deleteAll();
		assertEquals(dao.findAll().size(), 0);
	}

	/**
	 * Test method for {@link com.travel.dao.BaseDao#findById(long)}.
	 * @throws DbSqlException 
	 */
	@Test
	public void testFindById() throws DbSqlException 
	{
		User user = dao.createUser("main administrator", "admin", "123", true);
		assertNotNull(user);
		assertEquals("main administrator", user.getName());
		assertEquals("admin", user.getLogin());
		assertEquals("123", user.getPassword());
		assertTrue(user.isAdmin());
		
		User user2 = dao.findById(user.getId());
		assertNotNull(user2);
		assertEquals(user, user2);
		
		assertNull(dao.findById(-1));
	}

}
