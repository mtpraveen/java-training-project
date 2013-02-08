package com.epam.filters;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.epam.logic.model.User;

/**
 * @author EXHUMLOKI
 * Authorization filter use for filter url requests
 * and it redirect to log on page, if current session has not
 * user object (current user hasnt authorized) or this user
 * has not appropriate rights to view web page.
 */
@WebFilter(urlPatterns = {"/ServletManager", 
						  "/workerHomePage.jsp",
						  "/addJob.jsp",
						  "/editJob.jsp",
						  "/managerHomePage.jsp",
						  "/managerViewJobsList.jsp",
						  "/managerViewWorkersList.jsp",
						  "/workerViewJobsList.jsp"})
public class AuthorizationFilter implements Filter {
	
	private FilterConfig filterConfig;
	
	public void init(final FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	} 
	
	public void doFilter(final ServletRequest request, final ServletResponse response, FilterChain chain) 
																				throws java.io.IOException, 
																				javax.servlet.ServletException {
		HttpSession session = null;
		User user = null; // object of user, that has been saved in current session
		boolean authorized = false; // true - if use has authorized, false otherwise
		String url = null;
		
		// Filters works if requested pages are not as logOn, registration, switched language.
		// Check, if session exists, if true and current session has user, action is performs.
		// If session not exists or has not user, redirection on logOn.jsp performs.
		if (!("logonAction".equals(request.getParameter("action")) || 
				"regisrationAction".equals(request.getParameter("action")) ||
				"redirectAction".equals(request.getParameter("action")))) {
			session = ((HttpServletRequest)request).getSession(false); // get current session
			if (session != null) {
				user = (User) session.getAttribute("user");
				authorized = (user == null) ? false : true;
			} else {
				url = "/logOn.jsp";
			}

			// Perform action, if user has been authorized, redirect otherwise.
			if (authorized) {
				chain.doFilter(request, response);
			} else if (filterConfig != null) {
				url = "/logOn.jsp";
				request.getRequestDispatcher(url).forward(request, response);
			} else {
				throw new ServletException ("Unauthorized access, unable to forward to login page");
			}
		} else {
			chain.doFilter(request, response);
		}
	}
	
	public void destroy() { 
		this.filterConfig = null;
	}
}
