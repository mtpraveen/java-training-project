/**
 * 
 */
package com.travel.web.servlets.actions;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


import com.travel.dao.BaseDao;
import com.travel.dao.ClientDao;
import com.travel.dao.OrderDao;
import com.travel.dao.TourDao;
import com.travel.dao.TourSheduleDao;
import com.travel.dao.UserDao;
import com.travel.enums.TransportKind;
import com.travel.enums.TravelKind;
import com.travel.exceptions.DbSqlException;
import com.travel.exceptions.InvalidRequest;
import com.travel.exceptions.SaveException;
import com.travel.pojo.BaseEntity;
import com.travel.pojo.Client;
import com.travel.pojo.Discount;
import com.travel.pojo.Order;
import com.travel.pojo.Payment;
import com.travel.pojo.Tour;
import com.travel.pojo.TourShedule;
import com.travel.pojo.User;
import com.travel.web.enums.CrudAction;
import com.travel.web.enums.RequestMethod;
import com.travel.web.utils.ServicesContainer;
import com.travel.web.utils.TravelConsts;
import com.travel.web.utils.TravelSecurity;

/**
 * @author dima
 * 
 */
public class UpdateAction extends AbstractAction
{

	private ServicesContainer servicesContainer;
	private String pathOnError;

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
			if (request.getParameter(paramName) == null)
				throw new SaveException("Param " + paramName + " not found");
			String s = request.getParameter(paramName);
			return TravelConsts.htmlEscape(s);
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
			else if (sDate == null)
				return null;
			else
				return Date.valueOf(sDate);
		}

		public long getLong(String paramName) throws SaveException
		{
			return Long.parseLong(getString(paramName));
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
			return TravelConsts.makeCommand("view", getString("table"),
					String.valueOf(entity.getId()));
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
					Client client = (Client) entity;
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
					Discount discount = (Discount) entity;
					discount.setActive(getBool("active"));
					discount.setPercent(getInt("percent"));
					discount.setThreshold(getDouble("threshold"));
					return true;
				}
			};
		case TravelConsts.ORDERS_TABLE:
			return new EntitySaver() {
				@Override
				public boolean setFields() throws SaveException
				{
					Order order = (Order) entity;
					// order.setArrivalId(arrivalId)
					// order.setClientId(clientId)
					order.setFinished(getBool("finished"));
					order.setFinishedDate(getDate("finishedDate"));
					// order.setUserId(userId)
					return true;
				}

				@Override
				public boolean setNewFields() throws SaveException
				{
					Order order = (Order) entity;
					order.setTourShedule(new TourSheduleDao().findById(getLong("sheduleId")));
					order.setClient(new ClientDao().findById(getMasterId()));
					User user = (User) request.getAttribute("loggeduser");
					order.setUser(user);

					order.setCount(getInt("count"));
					order.setDate(getDate("date"));
					order.setTotalPrice(getDouble("totalPrice"));
					order.setDescription(getString("description"));
					return true;
				}

			};
		case TravelConsts.PAYMENTS_TABLE:
			return new EntitySaver() {
				@Override
				public boolean setFields() throws SaveException
				{
					Payment payment = (Payment) entity;
					payment.setAmount(getDouble("amount"));
					payment.setDate(getDate("date"));
					// payment.setOrderId(orderId)
					return true;
				}

				@Override
				public boolean setNewFields() throws SaveException
				{
					OrderDao orderDao = new OrderDao();
					Payment payment = (Payment) entity;
					payment.setOrder(orderDao.findById(getMasterId()));
					return true;
				}

				@Override
				public String getRedirectUrl() throws SaveException
				{
					return "/" + TravelConsts.makeCommand("view", "orders",
							String.valueOf(((Payment) entity).getOrder().getId()));
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
					shedule.setPrice(getDouble("price"));
					// shedule.setTourId(tourId)
					return true;
				}

				@Override
				public boolean setNewFields() throws SaveException
				{
					TourShedule shedule = (TourShedule) entity;
					shedule.setTour(new TourDao().findById(getMasterId()));
					return true;
				}
			};
		case TravelConsts.TOURS_TABLE:
			return new EntitySaver() {
				@Override
				public boolean setFields() throws SaveException
				{
					Tour tour = (Tour) entity;
					tour.setDaysCount(getInt("daysCount"));
					tour.setDescription(getString("description"));
					tour.setName(getString("name"));
					tour.setRequiredDocuments(getString("requiredDocuments"));
					tour.setTransportKind(TransportKind.valueOf(getString("transportKind")));
					tour.setTravelKind(TravelKind.valueOf(getString("travelKind")));
					return true;
				}
			};
		case TravelConsts.USERS_TABLE:
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
					// user.setPassword(confirmpassword)
					if (!password.equals(""))
					{
						if (password.equals(confirmpassword))
							user.setPassword(TravelSecurity.hashPassword(password));
						else
							throw new SaveException("Passwords not match");
					}
					return true;
				}

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
	
	private void validate(BaseEntity baseEntity) throws SaveException
	{
		ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
	    Validator validator = vf.getValidator();
		Set<ConstraintViolation<BaseEntity>> constraintViolations = validator.validate(baseEntity);
		if(constraintViolations.size() > 0)
		{
			StringBuilder builder = new StringBuilder();
			List<String> list = new ArrayList<>();
		    for (ConstraintViolation<BaseEntity> cv : constraintViolations)
		    {
		    	builder.append(String.format(
		    			"Error in : property: [%s], value: [%s], message: [%s]",
		    			cv.getPropertyPath(), cv.getInvalidValue(), cv.getMessage()));
		    	builder.append("<br/>");
		    }
		    throw new SaveException(builder.toString());
		}
	}
	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws SaveException, IOException, DbSqlException
	{
    	EntitySaver entitySaver = getSaver(request);
    	entitySaver.dao = servicesContainer.getService().createDao();
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
			validate(entitySaver.entity);
			servicesContainer.getService().validateNewItem(entitySaver.entity);
			entitySaver.entity = dao.create(entity);
		}
		else
		{
			long id = Long.parseLong(sId);
			entity = dao.findById(id);
			entitySaver.entity = entity;
			entitySaver.setFields();
			validate(entitySaver.entity);
			servicesContainer.getService().validateSavedItem(entitySaver.entity);
			dao.update(entity);
		}
        sendRedirect(entitySaver.getRedirectUrl(),request, response);
	}

	@Override
	public boolean userHasRights()
	{
		return servicesContainer.getService().hasRights(CrudAction.UPDATE);
	}

	@Override
	public String getJspTemplate()
	{
		return "";
	}

	@Override
	public void initParams(HttpServletRequest request, HttpServletResponse response)
			throws InvalidRequest
	{
		if (getPathParams().size() < 1)
			throw new InvalidRequest("Invalid request part count : " + getPathParams().size());
		servicesContainer = TravelConsts.getServiceContainer(request.getParameter("table"),
				getUser());
		if (servicesContainer == null)
			throw new InvalidRequest("Unknow table " + request.getParameter("table"));
	}

	@Override
	public boolean canProcessMethod(RequestMethod requestMethod)
	{
		return requestMethod == RequestMethod.POST;
	}
}
