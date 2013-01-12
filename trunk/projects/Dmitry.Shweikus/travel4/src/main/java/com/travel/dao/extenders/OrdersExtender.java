/**
 * 
 */
package com.travel.dao.extenders;

import com.travel.pojo.Client;
import com.travel.pojo.Order;
import com.travel.pojo.Tour;
import com.travel.pojo.TourShedule;
import com.travel.pojo.User;

/**
 * @author dima
 *
 */
public class OrdersExtender extends AbstractExtender
{
	private Order order;
	private TourShedule shedule ;
	private Tour tour ;
	private User user ;
	private Client client ;
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
}
