/**
 * 
 */
package com.travel.dao.extenders;

import java.util.List;
import java.util.ArrayList;

import com.travel.dao.BaseDao;
import com.travel.dao.ClientDao;
import com.travel.dao.OrderDao;
import com.travel.dao.TourDao;
import com.travel.dao.TourSheduleDao;
import com.travel.dao.UserDao;
import com.travel.dao.utils.SqlConstrainBuilder;
import com.travel.dao.utils.SelectSqlExecutor.SqlQueryDaoFacadeResultItem;
import com.travel.exceptions.DbSqlException;
import com.travel.pojo.Client;
import com.travel.pojo.Order;
import com.travel.pojo.Tour;
import com.travel.pojo.TourShedule;
import com.travel.pojo.User;

/**
 * @author dima
 *
 */
public class OrdersDaoExtender extends AbstractDaoExtender<OrdersExtender>
{

	@Override
	protected void fillConstrainBuilder(SqlConstrainBuilder conditionConstrainBuilder)
	{
		OrderDao orderDao = new OrderDao();
		TourSheduleDao sheduleDao = new TourSheduleDao();
		TourDao tourDao = new TourDao();
		UserDao userDao = new UserDao();
		ClientDao clientDao = new ClientDao();

		conditionConstrainBuilder.addConstrain(tourDao, sheduleDao);
		conditionConstrainBuilder.addConstrain(sheduleDao, orderDao);
		conditionConstrainBuilder.addConstrain(userDao, orderDao);
		conditionConstrainBuilder.addConstrain(clientDao, orderDao);
	}

	@Override
	protected OrdersExtender newExtender()
	{
		return new OrdersExtender();
	}

	@Override
	protected BaseDao getMainDao()
	{
		return new OrderDao();
	}

	@Override
	protected List<BaseDao> getOthersDaos()
	{
		List<BaseDao> daos = new ArrayList<>();
		daos.add(new TourSheduleDao());
		daos.add(new TourDao());
		daos.add(new UserDao());
		daos.add(new ClientDao());
		return daos;
	}

	@Override
	protected void fillExtenderFields(SqlQueryDaoFacadeResultItem item, OrdersExtender extender)
	{
		extender.setOrder((Order) item.getEntitys().get(0));
		extender.setShedule((TourShedule) item.getEntitys().get(1));
		extender.setTour((Tour) item.getEntitys().get(2));
		extender.setUser((User) item.getEntitys().get(3));
		extender.setClient((Client) item.getEntitys().get(4));
	}
	
	public List<OrdersExtender> findClientOrders(long clientId) throws DbSqlException
	{
		SqlConstrainBuilder constrainBuilder = new SqlConstrainBuilder();
		constrainBuilder.addFindByIdContrain(new ClientDao(), clientId);
		return executeQuery(constrainBuilder.build(), null);
	}

	public List<OrdersExtender> findSheduleOrders(long sheduleId) throws DbSqlException
	{
		SqlConstrainBuilder constrainBuilder = new SqlConstrainBuilder();
		constrainBuilder.addFindByIdContrain(new TourSheduleDao(), sheduleId);
		return executeQuery(constrainBuilder.build(), null);
	}

}
