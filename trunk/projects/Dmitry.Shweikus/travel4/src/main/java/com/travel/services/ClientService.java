/**
 * 
 */
package com.travel.services;

import javax.servlet.http.HttpServletRequest;

import com.travel.dao.ClientDao;
import com.travel.dao.UserDao;
import com.travel.dao.extenders.OrdersDaoExtender;
import com.travel.exceptions.DbSqlException;
import com.travel.exceptions.SaveException;
import com.travel.pojo.Client;
import com.travel.pojo.User;
import com.travel.web.enums.CrudAction;

/**
 * @author dima
 *
 */
public class ClientService extends MyAbstractWebService<Client>
{
	@Override
	public ClientDao createDao()
	{
		return new ClientDao();
	}
	@Override
	public void loadDetailItemsForSingleView(HttpServletRequest request) throws DbSqlException
	{
		Client client = (Client) request.getAttribute("client");
		OrdersDaoExtender ordersDaoExtender = new OrdersDaoExtender();
		request.setAttribute("orders", ordersDaoExtender.findClientOrders(client.getId()));
	}
	@Override
	public boolean hasRights(CrudAction crudAction)
	{
		return getServicesContainer().getUser() != null;
	}
	
	@Override
	public void validateNewItem(Client item) throws SaveException, DbSqlException
	{
		ClientDao clientDao = new ClientDao();
		if (clientDao.findByName(item.getFirstName(),item.getLastName()).size() > 0)
			throw new SaveException("Client with name " + item.getFirstName() + " " + item.getLastName() + " allready exists");
	}

	@Override
	public void validateSavedItem(Client item) throws DbSqlException, SaveException
	{
		ClientDao clientDao = new ClientDao();
		for (Client client : clientDao.findByName(item.getFirstName(),item.getLastName()))
				if (client.getId() != item.getId())
					throw new SaveException("Client with name " + item.getFirstName() + " " + item.getLastName() + " allready exists");
	} 
}
