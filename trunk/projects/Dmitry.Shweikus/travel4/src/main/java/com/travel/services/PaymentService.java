/**
 * 
 */
package com.travel.services;

import com.travel.dao.PaymentDao;
import com.travel.web.enums.CrudAction;

/**
 * @author dima
 *
 */
public class PaymentService extends MyAbstractWebService<PaymentDao>
{
	@Override
	public PaymentDao createDao()
	{
		return new PaymentDao();
	}

	@Override
	public boolean hasRights(CrudAction crudAction)
	{
		//TODO : check here order owner
		return getServicesContainer().getUser() != null;
	}
}
