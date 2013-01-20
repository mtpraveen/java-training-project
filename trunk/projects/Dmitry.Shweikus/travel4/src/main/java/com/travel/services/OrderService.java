/**
 * 
 */
package com.travel.services;

import javax.servlet.http.HttpServletRequest;

import com.travel.dao.ClientDao;
import com.travel.dao.DiscountDao;
import com.travel.dao.OrderDao;
import com.travel.dao.PaymentDao;
import com.travel.dao.TourSheduleDao;
import com.travel.dao.extenders.OrdersDaoExtender;
import com.travel.dao.extenders.OrdersExtender;
import com.travel.dao.extenders.TourSheduleDaoExtender;
import com.travel.exceptions.DbSqlException;
import com.travel.exceptions.SaveException;
import com.travel.pojo.Client;
import com.travel.pojo.Discount;
import com.travel.pojo.Order;
import com.travel.pojo.TourShedule;
import com.travel.web.beans.CalendarWrapper;
import com.travel.web.enums.CrudAction;

/**
 * @author dima
 *
 */
public class OrderService extends MyAbstractWebService<Order>
{
	@Override
	public OrderDao createDao()
	{
		return new OrderDao();
	}
	
	@Override
	public void setParamsForTableView(HttpServletRequest request) throws DbSqlException
	{
		OrdersDaoExtender ordersDaoExtender = new OrdersDaoExtender();
		request.setAttribute("items", ordersDaoExtender.findAll());
	}

	@Override
	protected Object findItemById(long id) throws DbSqlException
	{
		OrdersDaoExtender ordersDaoExtender = new OrdersDaoExtender();
		return ordersDaoExtender.findById(id);
	}
	
	@Override
	public void setParamsForEditItem(HttpServletRequest request) throws DbSqlException
	{
		super.setParamsForEditItem(request);
		request.setAttribute("user", request.getAttribute("seluser"));		
	}
	@Override
	public void setParamsForNewItem(HttpServletRequest request) throws DbSqlException
	{
		super.setParamsForNewItem(request);

		CalendarWrapper calendarWrapper = new CalendarWrapper();
		OrdersExtender orderExtender = new OrdersExtender();
		ClientDao clientDao = new ClientDao();
		
		Order order = new Order();
		order.setDate(calendarWrapper.getDate());

		Client client = clientDao.findById(getMasterIdParamFromRequest(request)); 
		orderExtender.setOrder(order);
		orderExtender.setClient(client);
		orderExtender.setUser(getServicesContainer().getUser());
		
		request.setAttribute("order", orderExtender);
		
		TourSheduleDaoExtender sheduleDaoExtender = new TourSheduleDaoExtender();
		request.setAttribute("shedules", sheduleDaoExtender.findAll());
		
		if (client!=null)
		{
			PaymentDao paymentDao = new PaymentDao();
			double totalPayments = paymentDao.findClientTotalPayments(client.getId());
			int discountPercent = 0;
			if (totalPayments > 0)
			{
				DiscountDao discountDao = new DiscountDao();
				for (Discount discount : discountDao.findAll())
				{
					if (discount.isActive())
						if(discount.getThreshold() <= totalPayments)
							if(discount.getPercent() > discountPercent)
								discountPercent = discount.getPercent();
				}
			}
			request.setAttribute("totalPayments", totalPayments);
			request.setAttribute("discount", discountPercent);
		}
	}
	
	@Override
	public void loadDetailItemsForSingleView(HttpServletRequest request) throws DbSqlException
	{
		OrdersExtender order = (OrdersExtender) request.getAttribute("order");
		PaymentDao paymentDao = new PaymentDao();
		request.setAttribute("payments", paymentDao.findOrdersPayments(order.getOrder().getId()));
	}

	@Override
	public boolean hasRights(CrudAction crudAction)
	{
		return getServicesContainer().getUser() != null;
	}
	
	@Override
	public void validateNewItem(Order item) throws SaveException, DbSqlException
	{
		OrderDao orderDao = new OrderDao();
		TourSheduleDao sheduleDao = new TourSheduleDao();
		TourShedule shedule = sheduleDao.findById(item.getArrivalId());
		int totalCount = 0;
		for (Order order : orderDao.findOrdersByShedule(shedule.getId()))
		{
			totalCount += order.getCount();
		}
		if (item.getCount() > shedule.getCount() - totalCount)
			throw new SaveException("Invalid count : " + item.getCount() + " > " + (shedule.getCount() - totalCount));
	}
}
