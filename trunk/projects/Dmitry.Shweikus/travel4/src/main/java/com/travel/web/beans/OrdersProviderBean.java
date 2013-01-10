/**
 * 
 */
package com.travel.web.beans;

import java.util.List;

import com.travel.dao.ClientDao;
import com.travel.dao.OrderDao;
import com.travel.dao.TourDao;
import com.travel.dao.TourSheduleDao;
import com.travel.dao.UserDao;
import com.travel.dao.utils.ManyToOneConditionConstrainBuilder;
import com.travel.dao.utils.SqlQueryDaoFacade.SqlQueryDaoFacadeResultItem;
import com.travel.db.ApplicationException;

/**
 * @author dima
 *
 */
public class OrdersProviderBean extends DataProviderBean
{
	public List getAllOrders() throws ApplicationException
	{
		OrderDao orderDao = new OrderDao();
		TourSheduleDao sheduleDao = new TourSheduleDao();
		TourDao tourDao = new TourDao();
		UserDao userDao = new UserDao();
		ClientDao clientDao = new ClientDao();
		
		setDao(orderDao);
		setDao2(tourDao);
		setDao3(sheduleDao);
		setDao4(userDao);
		setDao5(clientDao);
		
		ManyToOneConditionConstrainBuilder builder = new ManyToOneConditionConstrainBuilder();
		builder.addConstrain(tourDao, sheduleDao);
		builder.addConstrain(sheduleDao, orderDao);
		builder.addConstrain(userDao, orderDao);
		builder.addConstrain(clientDao, orderDao);
		setCustomConstrains(builder.build());
		
		return getRecords();
	}
	private List getOrdersRecords() throws ApplicationException
	{
		OrderDao orderDao = new OrderDao();
		TourSheduleDao sheduleDao = new TourSheduleDao();
		TourDao tourDao = new TourDao();
		UserDao userDao = new UserDao();
		ClientDao clientDao = new ClientDao();
		
		setDao(orderDao);
		setDao2(tourDao);
		setDao3(sheduleDao);
		setDao4(userDao);
		setDao5(clientDao);
		
		ManyToOneConditionConstrainBuilder builder = new ManyToOneConditionConstrainBuilder();
		builder.addConstrain(tourDao, sheduleDao);
		builder.addConstrain(sheduleDao, orderDao);
		builder.addConstrain(userDao, orderDao);
		builder.addConstrain(clientDao, orderDao);
		setCustomConstrains(builder.build());
		
		return getDetailRecords();
	}
	public List getRecordsForClient() throws ApplicationException
	{
		setMasterDao(new ClientDao());
		return getOrdersRecords();
	}
	public List getRecordsForTourShedule() throws ApplicationException
	{
		setMasterDao(new TourSheduleDao());
		return getOrdersRecords();
	}
	public SqlQueryDaoFacadeResultItem getOrderById(long id) throws ApplicationException
	{
		OrderDao orderDao = new OrderDao();
		TourSheduleDao sheduleDao = new TourSheduleDao();
		TourDao tourDao = new TourDao();
		UserDao userDao = new UserDao();
		ClientDao clientDao = new ClientDao();
		
		setDao(orderDao);
		setDao2(tourDao);
		setDao3(sheduleDao);
		setDao4(userDao);
		setDao5(clientDao);
		
		ManyToOneConditionConstrainBuilder builder = new ManyToOneConditionConstrainBuilder();
		builder.addConstrain(tourDao, sheduleDao);
		builder.addConstrain(sheduleDao, orderDao);
		builder.addConstrain(userDao, orderDao);
		builder.addConstrain(clientDao, orderDao);
		builder.addFindByIdContrain(orderDao, id);
		setCustomConstrains(builder.build());
		List<SqlQueryDaoFacadeResultItem> list = getRecords();
		if (list.size() == 0)
			return null;
		else
			return list.get(0);
	}
}
