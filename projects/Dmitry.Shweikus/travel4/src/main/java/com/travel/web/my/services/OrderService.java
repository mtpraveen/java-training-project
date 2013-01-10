/**
 * 
 */
package com.travel.web.my.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.travel.dao.ClientDao;
import com.travel.dao.OrderDao;
import com.travel.dao.utils.SqlQueryDaoFacade.SqlQueryDaoFacadeResultItem;
import com.travel.db.ApplicationException;
import com.travel.pojo.BaseEntity;
import com.travel.pojo.Client;
import com.travel.pojo.Order;
import com.travel.pojo.Tour;
import com.travel.pojo.TourShedule;
import com.travel.pojo.User;
import com.travel.web.beans.CalendarWrapper;
import com.travel.web.beans.OrdersProviderBean;

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
	
	public static class OrderExtender 
	{
		private TourShedule shedule;
		private Tour tour;
		private Client client;
		private User user;
		private Order order;
		/**
		 * @return the shedule
		 */
		public TourShedule getShedule()
		{
			return shedule;
		}
		/**
		 * @param shedule the shedule to set
		 */
		public void setShedule(TourShedule shedule)
		{
			this.shedule = shedule;
		}
		/**
		 * @return the tour
		 */
		public Tour getTour()
		{
			return tour;
		}
		/**
		 * @param tour the tour to set
		 */
		public void setTour(Tour tour)
		{
			this.tour = tour;
		}
		/**
		 * @return the client
		 */
		public Client getClient()
		{
			return client;
		}
		/**
		 * @param client the client to set
		 */
		public void setClient(Client client)
		{
			this.client = client;
		}
		/**
		 * @return the user
		 */
		public User getUser()
		{
			return user;
		}
		/**
		 * @param user the user to set
		 */
		public void setUser(User user)
		{
			this.user = user;
		}
		/**
		 * @return the order
		 */
		public Order getOrder()
		{
			return order;
		}
		/**
		 * @param order the order to set
		 */
		public void setOrder(Order order)
		{
			this.order = order;
		}
	}
	private List<OrderExtender> toOrdersExtenderList(List<SqlQueryDaoFacadeResultItem> items)
	{
		List<OrderExtender> list = new ArrayList<>();
		for (SqlQueryDaoFacadeResultItem item : items)
		{
			OrderExtender extender = null;
			if(item != null)
			{
				extender = new OrderExtender();
				extender.order = (Order) item.getEntitys().get(0);
				extender.tour = (Tour) item.getEntitys().get(1);
				extender.shedule = (TourShedule) item.getEntitys().get(2);
				extender.user = (User) item.getEntitys().get(3);
				extender.client = (Client)item.getEntitys().get(4);
			}
			list.add(extender);
		}
		return list;
	}
	@Override
	public void setShowItems(HttpServletRequest request) throws ApplicationException
	{
		OrdersProviderBean ordersProviderBean = new OrdersProviderBean();
		List results = ordersProviderBean.getAllOrders();
		request.setAttribute("items", toOrdersExtenderList(results));
	}

	@Override
	protected Object getViewItemById(long id) throws ApplicationException
	{
		OrdersProviderBean ordersProviderBean = new OrdersProviderBean();
		List<SqlQueryDaoFacadeResultItem> items = new ArrayList<>();
		items.add(ordersProviderBean.getOrderById(id));
		return toOrdersExtenderList(items).get(0);
	}
	
	@Override
	public void setEditParams(HttpServletRequest request)
	{
		super.setEditParams(request);
		request.setAttribute("user", request.getAttribute("seluser"));
	}
	@Override
	public void setNewItem(HttpServletRequest request, String sItemId) throws ApplicationException
	{
		super.setNewItem(request, sItemId);
		long masterId = Long.parseLong(sItemId);
		request.setAttribute("masterId", masterId);

		CalendarWrapper calendarWrapper = new CalendarWrapper();
		OrderExtender orderExtender = new OrderExtender();
		ClientDao clientDao = new ClientDao();
		
		Order order = new Order();
		order.setDate(calendarWrapper.getDate());

		orderExtender.setOrder(order);
		orderExtender.setClient(clientDao.findById(masterId));
		orderExtender.setUser(getDaoDescription().getUser());
		
		request.setAttribute("order", orderExtender);
	}
}
