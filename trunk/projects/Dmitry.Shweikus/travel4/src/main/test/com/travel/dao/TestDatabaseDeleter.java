/**
 * 
 */
package com.travel.dao;

/**
 * @author dima
 *
 */
public class TestDatabaseDeleter
{
	public static void clearDatabase()
	{
		new PaymentDao().deleteAll();
		new OrderDao().deleteAll();
		new TourSheduleDao().deleteAll();
		new TourDao().deleteAll();
		new ClientDao().deleteAll();
		new UserDao().deleteAll();
		new DiscountDao().deleteAll();
	}

}
