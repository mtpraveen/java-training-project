/**
 * 
 */
package com.travel.web.my.services;

import com.travel.dao.ClientDao;

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

}
