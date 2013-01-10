/**
 * 
 */
package com.travel.web.tags;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * @author dima
 *
 */
public class MainLayoutTag extends SimpleTagSupport
{
	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.SimpleTagSupport#doTag()
	 */
	@Override
	public void doTag() throws JspException, IOException
	{
		StringWriter sw = new StringWriter();
		getJspBody().invoke(sw);
		PageContext pageContext = (PageContext) getJspContext();  
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		try
		{
			request.setAttribute("travel_content", sw.toString());
			request.getRequestDispatcher("template.jsp").include(request, pageContext.getResponse());
		} catch (ServletException e)
		{
			throw new JspException(e.getMessage());
		}
		//getJspContext().getOut().println("<i>hello1</i> " + sw.toString() + " <i>hello2</i>");
	}
}
