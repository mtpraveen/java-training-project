/**
 * 
 */
package com.travel.web.servlets.actions;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author dima
 *
 */
public class DefaultAction extends AbstractAction
{
	@Override
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws IOException
	{
		//do nothings
	}
	@Override
	public String getJspTemplate()
	{
		return "default.jsp";
	}
}
