/**
 * 
 */
package com.travel.web.servlets.actions;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.travel.dao.BaseDao;
import com.travel.db.ApplicationException;
import com.travel.enums.TransportKind;
import com.travel.enums.TravelKind;
import com.travel.pojo.BaseEntity;
import com.travel.pojo.Client;
import com.travel.pojo.Discount;
import com.travel.pojo.Order;
import com.travel.pojo.Payment;
import com.travel.pojo.Tour;
import com.travel.pojo.TourShedule;
import com.travel.pojo.User;
import com.travel.web.exceptions.SaveException;
import com.travel.web.utils.DaoDescription;
import com.travel.web.utils.TravelConsts;
import com.travel.web.utils.TravelSecurity;

/**
 * @author dima
 *
 */
public class UpdateAction extends AbstractAction
{

    private abstract class EntitySaver
    {    	
    	HttpServletRequest request;
    	BaseEntity entity;
    	BaseDao dao;
    	public abstract boolean setFields() throws SaveException;
    	public boolean setNewFields() throws SaveException
    	{
    		return false;
    	}
    	public long getMasterId() throws SaveException
    	{
    		return getLong("masterId");
    	}
    	public String getString(String paramName) throws SaveException
    	{
    		if(request.getParameter(paramName) == null)
    			throw new SaveException("Param " + paramName + " not found");
    		return request.getParameter(paramName);
    	}
    	public int getInt(String paramName) throws SaveException
    	{
    		return Integer.parseInt(getString(paramName));
    	}
    	public Date getDate(String paramName) throws SaveException
    	{
    		String sDate = request.getParameter(paramName);
    		if ("".equals(sDate))
    			return null;
    		else if(sDate != null)
    			return Date.valueOf(sDate);
    		else
    		{
    			String sY = getString(paramName + "__y");
    			String sM = getString(paramName + "__m");
    			String sD = getString(paramName + "__d");
    			if(sY.equals("") || sM.equals("") || sD.equals(""))
        			return null;
    			else
    			{
    				Calendar calendar = GregorianCalendar.getInstance();
    				calendar.set(Integer.parseInt(sY), Integer.parseInt(sM)-1, Integer.parseInt(sD));
    				return new Date(calendar.getTimeInMillis());
    			}
    		}
    	}
    	public long getLong(String paramName) throws SaveException
    	{
    		return Long.parseLong(getString(paramName));
    	}
    	public BigDecimal getBigDecimal(String paramName) throws SaveException
    	{
    		return new BigDecimal(getString(paramName));
    	}
    	public boolean getBool(String paramName)
    	{
    		return request.getParameter(paramName) != null;
    	}
    	public double getDouble(String paramName) throws SaveException
    	{
    		return Double.parseDouble(getString(paramName));
    	}
    	public String getRedirectUrl() throws SaveException
    	{
    		return TravelConsts.makeCommand("view", getString("table"), String.valueOf(entity.getId()));
    	}
    }
    private EntitySaver getSaver(HttpServletRequest request)
    {
    	switch (request.getParameter("table")) {
		case TravelConsts.CLIENTS_TABLE:
			return new EntitySaver() {
				@Override
				public boolean setFields() throws SaveException
				{
					Client client = (Client)entity;
					client.setFirstName(getString("firstName"));
					client.setLastName(getString("lastName"));
					client.setDescription(getString("description"));
					client.setDocument1(getString("document1"));
					client.setDocument2(getString("document2"));
					client.setDocument3(getString("document3"));
					client.setDocument4(getString("document4"));
					return true;
				}
			};
		case TravelConsts.DISCOUNTS_TABLE:
			return new EntitySaver() {
				@Override
				public boolean setFields() throws SaveException
				{
					Discount discount = (Discount)entity;
					discount.setActive(getBool("active"));
					discount.setPercent(getInt("percent"));
					discount.setThreshold(getDouble("threshold"));
					return true;
				}
				@Override
				public String getRedirectUrl() throws SaveException
				{
		    		return TravelConsts.makeCommand("show", "discounts");
				}
			};
		case TravelConsts.ORDERS_TABLE:
			return new EntitySaver() {
				@Override
				public boolean setFields() throws SaveException
				{
					Order order = (Order)entity;
					//order.setArrivalId(arrivalId)
					//order.setClientId(clientId)
					order.setCount(getInt("count"));
					order.setDate(getDate("date"));
					order.setDescription(getString("description"));
					order.setFinished(getBool("finished"));
					order.setFinishedDate(getDate("finishedDate"));
					order.setTotalPrice(getBigDecimal("totalPrice"));
					//order.setUserId(userId)
					return true;
				}
				@Override
				public boolean setNewFields() throws SaveException
				{
					Order order = (Order)entity;
					order.setArrivalId(getLong("sheduleId"));
					order.setClientId(getMasterId());
					User user = (User) request.getAttribute("loggeduser");
					order.setUserId(user.getId());
					return true;
				}
				
			};
		case TravelConsts.PAYMENTS_TABLE:
			return new EntitySaver() {
				@Override
				public boolean setFields() throws SaveException
				{
					Payment payment = (Payment)entity;
					payment.setAmount(getBigDecimal("amount"));
					payment.setDate(getDate("date"));
					//payment.setOrderId(orderId)
					return true;
				}
				@Override
				public boolean setNewFields() throws SaveException
				{
					Payment payment = (Payment)entity;
					payment.setOrderId(getMasterId());
					return true;
				}
				@Override
				public String getRedirectUrl() throws SaveException
				{
		    		return TravelConsts.makeCommand("view", "orders", String.valueOf(((Payment)entity).getOrderId()));
				}
			};
		case TravelConsts.SHEDULES_TABLE:
			return new EntitySaver() {
				@Override
				public boolean setFields() throws SaveException
				{
					TourShedule shedule = (TourShedule) entity;
					shedule.setCount(getInt("count"));
					shedule.setDate(getDate("date"));
					shedule.setPrice(getBigDecimal("price"));
					//shedule.setTourId(tourId)
					return true;
				}
				@Override
				public boolean setNewFields() throws SaveException
				{
					TourShedule shedule = (TourShedule) entity;
					shedule.setTourId(getMasterId());
					return true;
				}
			};
		case TravelConsts.TOURS_TABLE :
			return new EntitySaver() {
				@Override
				public boolean setFields() throws SaveException
				{
					Tour tour = (Tour)entity;
					tour.setDaysCount(getInt("daysCount"));
					tour.setDescription(getString("description"));
					tour.setName(getString("name"));
					tour.setRequiredDocuments(getString("requiredDocuments"));
					tour.setTransportKind(TransportKind.valueOf(getString("transportKind")));
					tour.setTravelKind(TravelKind.valueOf(getString("travelKind")));
					return true;
				}
			};
		case TravelConsts.USERS_TABLE :
			return new EntitySaver() {
				@Override
				public boolean setFields() throws SaveException
				{
					User user = (User) entity;
					user.setAdmin(getBool("admin"));
					user.setName(getString("name"));
					user.setLogin(getString("login"));
					String password = getString("password");
					String confirmpassword = getString("confirmpassword");
					//user.setPassword(confirmpassword)
					if (!password.equals(""))
					{
						if(password.equals(confirmpassword))
							user.setPassword(TravelSecurity.hashPassword(password));
						else
							throw new SaveException("Passwords not match");
					}
					return true;
				}
				/* (non-Javadoc)
				 * @see com.travel.web.servlets.SaveServlet.EntitySaver#setNewFields()
				 */
				@Override
				public boolean setNewFields() throws SaveException
				{
					String password = getString("password");
					if (password.equals(""))
						throw new SaveException("Passwords not match");
					return true;
				}
			};
		default:
			return null;
		}
    }

    @Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws SaveException, IOException, ApplicationException
	{
		if (getPathParams().size() < 1) 
			throw new SaveException("Invalid request part count : " + getPathParams().size());
    	DaoDescription daoDescription = TravelConsts.getDaoDescription(request.getParameter("table"),getUser());
    	if (daoDescription == null)
    		throw new SaveException("Unknow table " + request.getParameter("table"));
    	EntitySaver entitySaver = getSaver(request);
    	entitySaver.dao = daoDescription.getDao();
		entitySaver.request = request;
		BaseDao dao = entitySaver.dao;
		String sId = request.getParameter("id");
		boolean isNew = sId.equals("-1") || sId.equals("");
		BaseEntity entity;
		if (isNew)
		{
			entity = dao.newEntity();
			entitySaver.entity = entity;
			entitySaver.setFields();
			entitySaver.setNewFields();
			entitySaver.entity = dao.create(entity);
		}
		else
		{
			long id = Long.parseLong(sId);
			entity = dao.findById(id);
			entitySaver.entity = entity;
			entitySaver.setFields();
			dao.update(entity);
		}
		sendRedirect(entitySaver.getRedirectUrl(),response);
	}

}