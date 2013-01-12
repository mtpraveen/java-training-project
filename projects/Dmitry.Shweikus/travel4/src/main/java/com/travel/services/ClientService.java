/**
 * 
 */
package com.travel.services;

import javax.servlet.http.HttpServletRequest;

import com.travel.dao.ClientDao;
import com.travel.dao.extenders.OrdersDaoExtender;
import com.travel.exceptions.DbSqlException;
import com.travel.pojo.Client;

/**
 * @author dima
 *
 */
public class ClientService extends MyAbstractWebService<ClientDao>
{
	@Override
	public ClientDao createDao()
	{
		return new ClientDao();
	}
	@Override
	public void setViewDetailItems(HttpServletRequest request) throws DbSqlException
	{
		Client client = (Client) request.getAttribute("client");
		OrdersDaoExtender ordersDaoExtender = new OrdersDaoExtender();
		request.setAttribute("orders", ordersDaoExtender.findClientOrders(client.getId()));
	}
}
