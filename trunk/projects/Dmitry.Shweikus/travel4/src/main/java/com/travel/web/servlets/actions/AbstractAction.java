/**
 * 
 */
package com.travel.web.servlets.actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.travel.exceptions.DbSqlException;
import com.travel.exceptions.DeleteException;
import com.travel.exceptions.InvalidRequest;
import com.travel.exceptions.SaveException;
import com.travel.pojo.User;

/**
 * @author dima
 *
 */
public abstract class AbstractAction
{
	private boolean redirected = false;	
	private List<String> pathParams = null;
	private User user;
	public abstract void process(HttpServletRequest request,HttpServletResponse response) throws IOException, InvalidRequest,DbSqlException, DeleteException, SaveException;
	public String getJspTemplate()
	{
		return "";
	}
	protected void sendRedirect(String path,HttpServletResponse response) throws IOException
	{
		redirected = true;
		response.sendRedirect(path);
	}
	/**
	 * @return the pathParams
	 */
	public List<String> getPathParams()
	{
		return pathParams;
	}
	/**
	 * @param pathParams the pathParams to set
	 */
	public void setPathParams(List<String> pathParams)
	{
		this.pathParams = pathParams;
	}
	/**
	 * @return the redirected
	 */
	public boolean isRedirected()
	{
		return redirected;
	}
	/**
	 * @return the user
	 */
	public User getUser()
	{
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user)
	{
		this.user = user;
	}
}
