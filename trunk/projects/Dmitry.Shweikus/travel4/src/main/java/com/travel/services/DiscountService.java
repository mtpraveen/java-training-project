/**
 * 
 */
package com.travel.services;

import com.travel.dao.DiscountDao;
import com.travel.web.enums.CrudAction;

/**
 * @author dima
 *
 */
public class DiscountService extends MyAbstractWebService<DiscountDao>
{
	@Override
	public DiscountDao createDao()
	{
		return new DiscountDao();
	}

	@Override
	public boolean hasRights(CrudAction crudAction)
	{
		switch (crudAction) {
		case CREATE: return getServicesContainer().isUserAdmin();
		case DELETE: return getServicesContainer().isUserAdmin();
		case UPDATE: return getServicesContainer().isUserAdmin();
		case READ: return true;
		default:
			return true;
		}
	}

}
