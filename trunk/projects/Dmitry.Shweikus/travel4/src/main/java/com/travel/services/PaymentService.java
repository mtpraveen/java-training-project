/**
 * 
 */
package com.travel.services;

import java.math.BigDecimal;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import com.travel.dao.OrderDao;
import com.travel.dao.PaymentDao;
import com.travel.exceptions.DbSqlException;
import com.travel.exceptions.SaveException;
import com.travel.pojo.Order;
import com.travel.pojo.Payment;
import com.travel.web.beans.CalendarWrapper;
import com.travel.web.enums.CrudAction;

/**
 * @author dima
 *
 */
public class PaymentService extends MyAbstractWebService<Payment>
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
	
	/* (non-Javadoc)
	 * @see com.travel.services.MyAbstractWebService#setParamsForNewItem(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public void setParamsForNewItem(HttpServletRequest request) throws DbSqlException
	{
		super.setParamsForNewItem(request);
		long id = getMasterIdParamFromRequest(request);
		PaymentDao paymentDao = new PaymentDao();
		OrderDao orderDao = new OrderDao();
		Order order = orderDao.findById(id);
		BigDecimal totalPayd = new BigDecimal(0);
		for (Payment payment : paymentDao.findOrdersPayments(order.getId()))
		{
			totalPayd = totalPayd.add(payment.getAmount());
		}
		BigDecimal pay = order.getTotalPrice().subtract(totalPayd);
		CalendarWrapper wrapper = new CalendarWrapper();
		Payment payment = (Payment) request.getAttribute(getParamNameForSingleItemJsp());
		payment.setAmount(pay);
		payment.setDate(wrapper.getDate());
	}
}
