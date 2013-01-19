/**
 * 
 */
package com.travel.web.servlets.actions;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.travel.exceptions.DbSqlException;
import com.travel.exceptions.DeleteException;
import com.travel.exceptions.InvalidRequest;
import com.travel.exceptions.SaveException;
import com.travel.web.enums.RequestMethod;

/**
 * @author dima
 *
 */
public class AutentificationRequiredAction extends AbstractAction
{

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, InvalidRequest, DbSqlException, DeleteException, SaveException
	{
		request.setAttribute("err", "Authentication required.");
	}

	@Override
	public boolean userHasRights()
	{
		return true;
	}

	@Override
	public String getJspTemplate()
	{
		return "login.jsp";
	}

	@Override
	public void initParams(HttpServletRequest request, HttpServletResponse response)
	{
		
	}

	@Override
	public boolean canProcessMethod(RequestMethod requestMethod)
	{
		return true;
	}

}
