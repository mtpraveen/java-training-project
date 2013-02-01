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
import com.travel.exceptions.DbSqlException;
import com.travel.exceptions.SaveException;
import com.travel.pojo.Client;
import com.travel.pojo.Discount;
import com.travel.pojo.Order;
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
		ClientDao clientDao = new ClientDao();
		Client client = clientDao.findById(getMasterIdParamFromRequest(request)); 
		
		Order order = new Order();
		order.setDate(calendarWrapper.getDate());
		order.setClient(client);
		order.setUser(getServicesContainer().getUser());
		
		request.setAttribute("order", order);
		request.setAttribute("shedules", new TourSheduleDao().findAll());
		
		if (client!=null)
		{
			double totalPayments = new PaymentDao().findClientTotalPayments(client.getId());
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
		Order order = (Order) request.getAttribute("order");
		PaymentDao paymentDao = new PaymentDao();
		request.setAttribute("payments", paymentDao.findOrdersPayments(order.getId()));
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
		int totalCount = 0;
		for (Order order : orderDao.findOrdersByShedule(item.getTourShedule().getId()))
		{
			totalCount += order.getCount();
		}
		if (item.getCount() > item.getTourShedule().getCount() - totalCount)
			throw new SaveException("Invalid count : " + item.getCount() + " > " + (item.getTourShedule().getCount() - totalCount));
	}
}
