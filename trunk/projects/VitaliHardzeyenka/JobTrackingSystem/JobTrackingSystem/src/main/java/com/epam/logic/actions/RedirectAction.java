package com.epam.logic.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RedirectAction extends AbstractAction {
	
	@Override
	/**
	 * Perform redirection action for switching location.
	 * Add in current session attribute with locale and redirect 
	 * back to page form user has come.
	 */
	public String perform(HttpServletRequest request, HttpServletResponse response) {
		String url = request.getParameter("url");
		String locale = request.getParameter("locale");
		request.getSession().setAttribute("locale", locale);
		
		return url;
	}

}
