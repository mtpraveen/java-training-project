/**
 * 
 */
package com.travel.services;

import com.travel.dao.PaymentDao;

/**
 * @author dima
 *
 */
public class PaymentService extends MyAbstractWebService<PaymentDao>
{
	@Override
	public PaymentDao createDao()
	{
		// TODO Auto-generated method stub
		return new PaymentDao();
	}
}
