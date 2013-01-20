/**
 * 
 */
package com.travel.web.servlets.actions;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.travel.dao.UserDao;
import com.travel.exceptions.DbSqlException;
import com.travel.pojo.User;
import com.travel.web.enums.RequestMethod;
import com.travel.web.utils.TravelSecurity;

/**
 * @author dima
 *
 */
public class UserAction extends AbstractAction
{
	private static final Logger LOGGER = Logger.getLogger(UserAction.class);
	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, DbSqlException 
	{
		Cookie cookie;
		switch (getPathParams().get(0)) {
		case "logout":
			if (request.getSession(false) != null)
			{
				request.getSession().invalidate();
			}
			cookie = new Cookie("login", "");
			cookie.setMaxAge(0);
			response.addCookie(cookie);

			cookie = new Cookie("password", "");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
			sendRedirect("index", response);
			break;
		case "login":
			if(request.getMethod().equalsIgnoreCase("POST"))
			{
				String login = request.getParameter("login");
				String passwordRaw = request.getParameter("password");
				String password = TravelSecurity.hashPassword(passwordRaw);
				UserDao dao = new UserDao();
				User user = dao.findUserByLoginAndPassword(login, password);
				if (user == null)
				{
					if (request.getSession(false) != null)
					{
						request.getSession().invalidate();
					}
					cookie = new Cookie("login", "");
					cookie.setMaxAge(0);
					response.addCookie(cookie);

					cookie = new Cookie("password", "");
					cookie.setMaxAge(0);
					response.addCookie(cookie);

					request.setAttribute("err", "Invalid password or login.");
					LOGGER.info("Invalid password or login [" + login + "],[" + password + "][" + passwordRaw + "]");
				}
				else
				{
					request.getSession(true).setAttribute("loggeduser", user);
					request.setAttribute("loggeduser", user);
					
					Cookie cookieLogin = new Cookie("login", user.getLogin());
					cookieLogin.setMaxAge(60*60*24*63);
					//cookieLogin.setSecure(true);
					response.addCookie(cookieLogin);
					Cookie cookiePassword = new Cookie("password", user.getPassword());
					//cookiePassword.setSecure(true);
					cookiePassword.setMaxAge(60*60*24*63);
					response.addCookie(cookiePassword);
					
					sendRedirect("index",response);
					//request.getRequestDispatcher("index.jsp").forward(request, response);
					LOGGER.info("Successfull login [" + login + "]");
				}
			}
			else if(request.getMethod().equalsIgnoreCase("GET"))
			{
				Cookie[] cookies = request.getCookies();
				if(cookies != null)
					for (Cookie cookie2 : cookies)
					{
						if ("login".equalsIgnoreCase(cookie2.getName()))
						{
							request.setAttribute("login", cookie2.getValue());
						}
					}
			}
				
			break;
		default:
			break;
		}
	}

	@Override
	public String getJspTemplate() 
	{
		return "login.jsp";
	}

	@Override
	public boolean userHasRights()
	{
		return true;
	}

	@Override
	public void initParams(HttpServletRequest request, HttpServletResponse response)
	{
		
	}

	@Override
	public boolean canProcessMethod(RequestMethod requestMethod)
	{
		return true;
	}

}
