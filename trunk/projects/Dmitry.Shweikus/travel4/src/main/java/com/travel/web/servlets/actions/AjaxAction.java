/**
 * 
 */
package com.travel.web.servlets.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.google.gson.Gson;
import com.travel.dao.ClientDao;
import com.travel.dao.TourDao;
import com.travel.dao.UserDao;
import com.travel.exceptions.DbSqlException;
import com.travel.exceptions.DeleteException;
import com.travel.exceptions.InvalidRequest;
import com.travel.exceptions.SaveException;
import com.travel.pojo.Client;
import com.travel.pojo.Tour;
import com.travel.pojo.User;
import com.travel.web.enums.RequestMethod;

/**
 * @author dima
 *
 */
public class AjaxAction extends AbstractAction
{
	private static enum AjaxActionKind {
		user,//find user with presented name
		client,//find client with presented first and last name
		tour//checks that tour with this name and id not exists
	}
	AjaxActionKind kind = null;

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, InvalidRequest, DbSqlException, DeleteException, SaveException
	{
		switch (kind) {
		case client:
			executeClient(request, response);
			break;
		case user:
			executeUser(request, response);
			break;
		case tour:
			executeTour(request, response);
		}
	}
	/*
	 * search user by name and returns XML with id's
	 */
	private void executeUser(HttpServletRequest request, HttpServletResponse response)
			throws IOException, InvalidRequest, DbSqlException, DeleteException, SaveException
	{
		UserDao dao = new UserDao();
		Document document = DocumentHelper.createDocument();
        Element root = document.addElement( "root" );
		String name = request.getParameter("name");
		if(name != null)
		{
	        for (User user : dao.findUsersByLogin(name))
			{
		        Element userXml = root.addElement( "user" );
		        userXml.addAttribute("id", String.valueOf(user.getId()));
			}
		}
        OutputFormat format = OutputFormat.createPrettyPrint();
        XMLWriter writer = new XMLWriter( response.getWriter(), format );
        writer.write( document );
	}

	/*
	 * search by firstname and lastname and
	 * returns JSON array with id's
	 */
	private void executeClient(HttpServletRequest request, HttpServletResponse response)
			throws IOException, InvalidRequest, DbSqlException, DeleteException, SaveException
	{
		ClientDao dao = new ClientDao();
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		List<Long> ids = new ArrayList<>();
		for(Client client : dao.findByName(firstName, lastName))
		{
			ids.add(client.getId());
		}
		Gson gson = new Gson();
		String json = gson.toJson(ids);
		response.getWriter().print(json);
	}
	/*
	 * checks that tour with this name and id not exists 
	 * used for jquery remote() rule.
	 * 
	 * return true if name not found or this is old name for this id 
	 * return false if presented name allready exists 
	 */
	private void executeTour(HttpServletRequest request, HttpServletResponse response)
			throws IOException, InvalidRequest, DbSqlException, DeleteException, SaveException
	{
		TourDao dao = new TourDao();
		String name = request.getParameter("name");
		String sId = request.getParameter("id");
		long id;
		try
		{
			id = Long.parseLong(sId);
		}
		catch(NumberFormatException e)
		{
			id = 0;
		}
		for (Tour tour : dao.findByName(name))
		{
			if (tour.getId() != id)
			{
				response.getWriter().print("false");
				return;
			}
		}
		response.getWriter().print("true");
	}

	@Override
	public boolean userHasRights()
	{
		switch (kind) {
		case client:
			return getUser() != null;
		case tour:
			return getUser() != null;
		case user:
			return (getUser() != null) && getUser().isAdmin();
		default:
			return false;
		}
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
		if (getPathParams().size() > 1)
		{
			String sKind = getPathParams().get(1);
			try
			{
				kind = AjaxActionKind.valueOf(sKind.toLowerCase());
			}
			catch(Exception e)
			{
				kind =  null;
			}
		}
	}

	@Override
	public boolean canProcessMethod(RequestMethod requestMethod)
	{
		return kind != null;
	}
	
	@Override
	public boolean hasJsp()
	{
		return false;
	}

}
