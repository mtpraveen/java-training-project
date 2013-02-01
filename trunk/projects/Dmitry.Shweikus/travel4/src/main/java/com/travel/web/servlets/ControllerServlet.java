package com.travel.web.servlets;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.travel.dao.UserDao;
import com.travel.db.ConnectionManager;
import com.travel.exceptions.DbSqlException;
import com.travel.exceptions.DeleteException;
import com.travel.exceptions.InvalidRequest;
import com.travel.exceptions.SaveException;
import com.travel.pojo.User;
import com.travel.web.enums.RequestMethod;
import com.travel.web.servlets.actions.AbstractAction;
import com.travel.web.servlets.dispatcher.RequestDispatcher;

/**
 * Servlet implementation class ControllerServlet
 */
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String bundleName="i18n.messages";
	private static final Logger LOGGER = Logger.getLogger(ControllerServlet.class);


    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerServlet() {
        super();
    }
    
    @Override
    public void init() throws ServletException
    {
    	super.init();
        LOGGER.info("Server started");
    }

	private User loadUser(HttpServletRequest request, HttpServletResponse response)
	{
		User user = null;
		if(request.getSession(false) != null)
		{
			user = (User) request.getSession().getAttribute("user");
		}
		if (user == null)
		{
			String login = null;
			String password = null;
			Cookie[] cookies = request.getCookies();
			if(cookies != null)
				for (Cookie cookie : cookies)
				{
					switch (cookie.getName()) {
					case "login":
						login = cookie.getValue();
						break;
					case "password":
						password = cookie.getValue();
						break;
					}
				}
			if(login != null)
				if (password != null)
				{
					try
					{
						user = (new UserDao()).findUserByLoginAndPassword(login, password);
						if(user == null)
						{
							Cookie cookie = new Cookie("login", "");
							cookie.setMaxAge(0);
							response.addCookie(cookie);

							cookie = new Cookie("password", "");
							cookie.setMaxAge(0);
							response.addCookie(cookie);
						}
					} catch (DbSqlException e)
					{
						//do nothing here
					}
				}
		}
		if (user != null)
		{
			request.setAttribute("loggeduser", user);
		}
		return user;
	}
	private void loadBundleName(HttpServletRequest request)
	{
		String bundle = bundleName;
		String foundedLocale = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null)
			for (Cookie cookie : cookies)
			{
				if (cookie.getName().equals("locale"))
					foundedLocale = cookie.getValue(); 
			}
		if (foundedLocale == null)
		{
			Locale locale = request.getLocale();
			if(locale != null)
			{
				if(locale.toString().indexOf("en")!=-1)
				{
					foundedLocale = "en";					
				}
				else if(locale.toString().indexOf("ru")!=-1)
				{
					foundedLocale = "ru";					
				}
			}
		}
		switch (foundedLocale.toLowerCase()) {
		case "ru":
			bundle += "_ru";
			break;
		case "en":
			bundle += "_en";
			break;
		}
		request.setAttribute("bundle", bundle);
	}
	
	private void execute(HttpServletRequest request, HttpServletResponse response, RequestMethod requestMethod) throws ServletException, IOException {
		try
		{
			loadBundleName(request);
			request.setCharacterEncoding("utf-8");
			RequestDispatcher dispatcher = new RequestDispatcher();
			AbstractAction action;
			try
			{
				User user = loadUser(request, response);
				action = dispatcher.getAction(request, response,user);
				action.initParams(request, response);
				if (!action.userHasRights())
				{
					action = dispatcher.getAutentificationRequiredAction(request, response, user);
					action.initParams(request, response);
				}	
				if (!action.canProcessMethod(requestMethod))
				{
					throw new InvalidRequest("Invalid request method " + requestMethod);
				}
				action.process(request, response);
				if (action.hasJsp())
				{
					String template = action.getJspTemplate();
					if(template.equals(""))
						template = "error.jsp";
					request.getRequestDispatcher("/WEB-INF/jsp/" + template).forward(request, response);
				}
			} catch (InvalidRequest |DbSqlException|DeleteException|SaveException e)
			{
				String template = "error.jsp";
				request.setAttribute("errorMessage", e.getMessage());
				request.getRequestDispatcher("/WEB-INF/jsp/" + template).forward(request, response);
				LOGGER.error("Standard error : ", e);
			}
			catch (Exception e) {
				LOGGER.error("Unknown error : ", e);
				throw new ServletException(e);
			}
		}
		finally
		{
	    	ConnectionManager.closeSession();
		}
	}
	
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response, RequestMethod.GET);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response, RequestMethod.POST);
	}

}
