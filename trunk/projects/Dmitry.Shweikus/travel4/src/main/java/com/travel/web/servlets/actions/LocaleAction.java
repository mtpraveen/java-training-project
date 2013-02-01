/**
 * 
 */
package com.travel.web.servlets.actions;

import java.awt.SecondaryLoop;
import java.io.IOException;

import javax.servlet.http.Cookie;
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
public class LocaleAction extends AbstractAction
{

	public LocaleAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, InvalidRequest, DbSqlException, DeleteException, SaveException
	{
		if(getPathParams().size() < 2)
			throw new InvalidRequest("Invalid params count");
		String localeCode = "en";
		switch (getPathParams().get(1).toLowerCase()) {
		case "en":
			localeCode = "en";
			break;
		case "ru":
			localeCode = "ru";
			break;
		default:
			throw new InvalidRequest("Invalid locale " + localeCode);
		}
		Cookie cookie = new Cookie("locale", localeCode);
		cookie.setMaxAge(60*60*24*365);
		//setPath because by default coocki is writed to /travel4/langauage
		String path = request.getContextPath();
		if ("".equals(path))
			path = "/";
		cookie.setPath(path);
		response.addCookie(cookie);
		redirected = true;
		sendRedirect("index", request, response);
	}

	@Override
	public boolean userHasRights()
	{
		return true;
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

	}

	@Override
	public boolean canProcessMethod(RequestMethod requestMethod)
	{
		return requestMethod == RequestMethod.GET;
	}

}
