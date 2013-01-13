/**
 * 
 */
package com.travel.web.servlets.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.travel.dao.BaseDao;
import com.travel.dao.OrderDao;
import com.travel.dao.PaymentDao;
import com.travel.dao.TourSheduleDao;
import com.travel.dao.utils.SqlConstrainBuilder;
import com.travel.dao.utils.SelectSqlExecutor;
import com.travel.db.ConnectionManager;
import com.travel.exceptions.DbSqlException;
import com.travel.exceptions.DeleteException;
import com.travel.exceptions.InvalidRequest;
import com.travel.web.utils.ServicesContainer;
import com.travel.web.utils.TravelConsts;

/**
 * @author dima
 *
 */
public class DeleteAction extends AbstractAction
{

    private abstract class EntityDeleter
    {
    	protected BaseDao getSingleDetailDao()
    	{
    		return null;
    	}
    	protected List<BaseDao> getMultipleDetailDao()
    	{
    		return null;
    	}
    	public List<BaseDao> getDetailDao()
    	{
    		List<BaseDao> details = new ArrayList<>();
    		BaseDao single = getSingleDetailDao();
    		if(single != null)
    			details.add(single);
    		List<BaseDao> multiple = getMultipleDetailDao();
    		if (multiple != null)
    			details.addAll(multiple);
    		return details;
    	}
    }
    private EntityDeleter getDeleter(HttpServletRequest request)
    {
    	String table = getPathParams().get(1);
    	switch (table) {
		case TravelConsts.CLIENTS_TABLE:
			return new EntityDeleter() {
				@Override
				protected BaseDao getSingleDetailDao()
				{
					return new OrderDao();
				}
			};
		case TravelConsts.DISCOUNTS_TABLE:
			return new EntityDeleter() {
			};
		case TravelConsts.ORDERS_TABLE:
			return new EntityDeleter() {
				@Override
				protected BaseDao getSingleDetailDao()
				{
					return new PaymentDao();
				}
			};
		case TravelConsts.PAYMENTS_TABLE:
			return new EntityDeleter() {
			};
		case TravelConsts.SHEDULES_TABLE:
			return new EntityDeleter() {
				@Override
				protected BaseDao getSingleDetailDao()
				{
					return new OrderDao();
				}
			};
		case TravelConsts.TOURS_TABLE:
			return new EntityDeleter() {
				@Override
				protected BaseDao getSingleDetailDao()
				{
					return new TourSheduleDao();
				}
			};
		case TravelConsts.USERS_TABLE:
			return new EntityDeleter() {
				@Override
				protected BaseDao getSingleDetailDao()
				{
					return new OrderDao();
				}
			};
		default:
			return null;
		}
    }
	@Override
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, InvalidRequest, DbSqlException, DeleteException
	{
		if (getPathParams().size() < 3)
			throw new InvalidRequest("Invalid delete params count : " + getPathParams().size());
		ServicesContainer daoDescription = TravelConsts.getServiceContainer(getPathParams().get(1),getUser());
		if (daoDescription == null)
			throw new InvalidRequest("Invalid table : " + getPathParams().get(1));
		EntityDeleter deleter = getDeleter(request);
		long id = Long.parseLong(getPathParams().get(2));
		BaseDao dao = daoDescription.getDao();
		for (BaseDao detail : deleter.getDetailDao())
		{
			SqlConstrainBuilder constrainBuilder = new SqlConstrainBuilder();
			constrainBuilder.addConstrainWithId(dao, detail, id);
			SelectSqlExecutor sql = new SelectSqlExecutor(ConnectionManager.getInstance().getConnection());
			if (sql.executeQuery(detail, constrainBuilder.build(), null).size() > 0)
				throw new DeleteException("Item has child items in table " + detail.getTableName());
		}
		dao.delete(id);
		sendRedirect("index",response);
	}

}
