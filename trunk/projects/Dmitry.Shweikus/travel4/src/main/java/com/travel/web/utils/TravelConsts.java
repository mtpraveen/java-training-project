/**
 * 
 */
package com.travel.web.utils;

import java.util.ArrayList;
import java.util.List;

import com.travel.dao.BaseDao;
import com.travel.dao.ClientDao;
import com.travel.dao.DiscountDao;
import com.travel.dao.OrderDao;
import com.travel.dao.PaymentDao;
import com.travel.dao.TourDao;
import com.travel.dao.TourSheduleDao;
import com.travel.dao.UserDao;
import com.travel.pojo.User;
import com.travel.web.my.services.ClientService;
import com.travel.web.my.services.DiscountService;
import com.travel.web.my.services.OrderService;
import com.travel.web.my.services.PaymentService;
import com.travel.web.my.services.TourService;
import com.travel.web.my.services.TourSheduleService;
import com.travel.web.my.services.UserService;


/**
 * @author dima
 *
 */
public class TravelConsts
{
	public static final String USERS_TABLE = "users";	
	public static final String DISCOUNTS_TABLE = "discounts";
	public static final String TOURS_TABLE = "tours";
	public static final String CLIENTS_TABLE = "clients";
	public static final String ORDERS_TABLE = "orders";
	public static final String SHEDULES_TABLE = "tourshedules";
	public static final String PAYMENTS_TABLE = "payments";
	public static List<String> getAllTables()
	{
		ArrayList<String> all = new ArrayList<>();
		all.add(USERS_TABLE);
		all.add(DISCOUNTS_TABLE);
		all.add(TOURS_TABLE);
		all.add(CLIENTS_TABLE);
		all.add(ORDERS_TABLE);
		all.add(SHEDULES_TABLE);
		all.add(PAYMENTS_TABLE);
		return all;
	}
	public static String evalSingleItem(String table)
	{
		if(table.length() > 1)
			if (table.endsWith("s"))
				return table.substring(0, table.length()-1);
		return table;
	}
	public static String findTableName(String pattern)
	{
		List<String> all = getAllTables();
		if(all.indexOf(pattern) != -1)
			return pattern;
		pattern += "s";
		if(all.indexOf(pattern) != -1)
			return pattern;
		else
			return null; 
	}
	public static DaoDescription getDaoDescription(String tableName, User user)
	{
		tableName = findTableName(tableName);
		switch (tableName) {
		case CLIENTS_TABLE:	  return new DaoDescription(new ClientService(),tableName, user);
		case DISCOUNTS_TABLE: return new DaoDescription(new DiscountService(),tableName, user);
		case ORDERS_TABLE:	  return new DaoDescription(new OrderService(),tableName, user);
		case PAYMENTS_TABLE:  return new DaoDescription(new PaymentService(),tableName, user);
		case SHEDULES_TABLE:  return new DaoDescription(new TourSheduleService(),tableName, user);
		case TOURS_TABLE:	  return new DaoDescription(new TourService(),tableName, user);
		case USERS_TABLE:	  return new DaoDescription(new UserService(),tableName, user);
		}
		return null;
	}
	public static String makeCommand(String part1,String part2)
	{
		return part1 + "-" + part2;
	}
	public static String makeCommand(String part1,String part2,String part3)
	{
		return part1 + "-" + part2 + "-" + part3;
	}
}
