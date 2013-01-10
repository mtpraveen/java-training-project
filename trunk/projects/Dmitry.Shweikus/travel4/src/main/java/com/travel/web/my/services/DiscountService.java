/**
 * 
 */
package com.travel.web.my.services;

import com.travel.dao.DiscountDao;

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

}
