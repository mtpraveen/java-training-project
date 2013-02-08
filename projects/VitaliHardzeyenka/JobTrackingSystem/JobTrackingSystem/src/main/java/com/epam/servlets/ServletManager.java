package com.epam.servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.logic.actions.AbstractAction;
import com.epam.logic.actions.AbstractActionsFactory;

/**
 * Main servlet for jsp and classes management. 
 */
@WebServlet("/ServletManager")
public class ServletManager extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	protected AbstractActionsFactory actionsFactory = new AbstractActionsFactory();

	/**
	 * Get from request attribute that specify what action will be
	 * perform and what class need to handle.
	 */
	protected String getActionName(HttpServletRequest request) {
		return request.getParameter("action");
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	/**
	 * Servlet redirect to need class according to request that come from jsp.
	 * Get coming from class string with bask jsp and direct towards it.  
	 */
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AbstractAction action = actionsFactory.create(getActionName(request));
		String url = action.perform(request, response);
		if (url != null) {
			request.getRequestDispatcher(url).forward(request, response);
		}
	}

	public void destroy() {
		super.destroy();
	}

}
