package com.travel.web.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.travel.dao.UserDao;
import com.travel.db.ApplicationException;
import com.travel.pojo.User;
import com.travel.web.exceptions.DeleteException;
import com.travel.web.exceptions.InvalidRequest;
import com.travel.web.exceptions.SaveException;
import com.travel.web.servlets.actions.AbstractAction;
import com.travel.web.servlets.dispatcher.RequestDispatcher;

/**
 * Servlet implementation class ControllerServlet
 */
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	private User LoadUser(HttpServletRequest request, HttpServletResponse response)
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
					} catch (ApplicationException e)
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

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = new RequestDispatcher();
		AbstractAction action;
		try
		{
			action = dispatcher.getAction(request, response,LoadUser(request, response));
			action.process(request, response);
			if (!action.isRedirected())
			{
				String template = action.getJspTemplate();
				if(template.equals(""))
					template = "error.jsp";
				request.getRequestDispatcher("/" + template).forward(request, response);
			}	
		} catch (InvalidRequest |ApplicationException|DeleteException|SaveException e)
		{
			throw new ServletException(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
