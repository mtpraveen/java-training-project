/**
 * 
 */
package com.travel.services;

import javax.servlet.http.HttpServletRequest;

import com.travel.dao.ClientDao;
import com.travel.dao.OrderDao;
import com.travel.dao.PaymentDao;
import com.travel.dao.extenders.OrdersDaoExtender;
import com.travel.dao.extenders.OrdersExtender;
import com.travel.dao.extenders.TourSheduleDaoExtender;
import com.travel.exceptions.DbSqlException;
import com.travel.pojo.Order;
import com.travel.web.beans.CalendarWrapper;

/**
 * @author dima
 *
 */
public class OrderService extends MyAbstractWebService<OrderDao>
{
	@Override
	public OrderDao createDao()
	{
		return new OrderDao();
	}
	
	@Override
	public void setShowItems(HttpServletRequest request) throws DbSqlException
	{
		OrdersDaoExtender ordersDaoExtender = new OrdersDaoExtender();
		request.setAttribute("items", ordersDaoExtender.findAll());
	}

	@Override
	protected Object getViewItemById(long id) throws DbSqlException
	{
		OrdersDaoExtender ordersDaoExtender = new OrdersDaoExtender();
		return ordersDaoExtender.findById(id);
	}
	
	@Override
	public void setEditParams(HttpServletRequest request) throws DbSqlException
	{
		super.setEditParams(request);
		request.setAttribute("user", request.getAttribute("seluser"));		
	}
	@Override
	public void setNewItem(HttpServletRequest request, String sItemId) throws DbSqlException
	{
		super.setNewItem(request, sItemId);
		long masterId = Long.parseLong(sItemId);
		request.setAttribute("masterId", masterId);

		CalendarWrapper calendarWrapper = new CalendarWrapper();
		OrdersExtender orderExtender = new OrdersExtender();
		ClientDao clientDao = new ClientDao();
		
		Order order = new Order();
		order.setDate(calendarWrapper.getDate());

		orderExtender.setOrder(order);
		orderExtender.setClient(clientDao.findById(masterId));
		orderExtender.setUser(getServicesContainer().getUser());
		
		request.setAttribute("order", orderExtender);
		
		TourSheduleDaoExtender sheduleDaoExtender = new TourSheduleDaoExtender();
		request.setAttribute("shedules", sheduleDaoExtender.findAll());
	}
	
	@Override
	public void setViewDetailItems(HttpServletRequest request) throws DbSqlException
	{
		OrdersExtender order = (OrdersExtender) request.getAttribute("order");
		PaymentDao paymentDao = new PaymentDao();
		request.setAttribute("payments", paymentDao.findOrdersPayments(order.getOrder().getId()));
	}
}
