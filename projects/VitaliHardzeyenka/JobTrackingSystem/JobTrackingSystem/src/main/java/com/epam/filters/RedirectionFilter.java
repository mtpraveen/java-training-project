package com.epam.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * @author EXHUMLOKI
 * Redirection filter use for redirect from forbidden pages.
 * For example, user cant to get error or logoff page, if he
 * type this url in urlstring.
 */
@WebFilter(urlPatterns = {"/error.jsp", "/logOff.jsp"})
public class RedirectionFilter implements Filter {
	
	
	public void init(final FilterConfig filterConfig) {	} 

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String url = "/default.html";
		// Redirect from forbidden pages to default page, that display the error.
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	public void destroy() {	}

}
