package com.epam.logic.actions;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.logic.Encryption;
import com.epam.logic.Logger;
import com.epam.logic.model.User;
import com.mysql.jdbc.ResultSet;

/**
 * Class perform log on action.
 * @author Gordeenko_XP
 */
public class LogonAction extends AbstractAction {
	
	Logger logger = new Logger(org.apache.log4j.Logger.getLogger(LogonAction.class.getClass()));
	
	@Override
	/**
	 * Try to find user, if user has been find, log on system.
	 * Return .jsp. Return manager of worker home page, or 
	 * if user has`n been found, return Error.jsp.
	 */
	public String perform(HttpServletRequest request, HttpServletResponse response) {
		Encryption encryption = new Encryption();
		String login = request.getParameter("login");
		String password = encryption.getHashCode(request.getParameter("password"));
		ResultSet resultSet = (ResultSet) find("users", User.LOGIN, User.PASSWORD, login, password); // find user in data base
		User user = null;
		
		if (resultSet != null) {
			try {
				if (resultSet.first()) {
					int id = (int) resultSet.getObject(User.USER_ID);
					user = createUserById(id);
					
					request.getSession().setAttribute("user", user);
					// Choose by user position what .jsp return.
					switch ((int) resultSet.getObject(User.POSITION_ID)) {
						case User.MANAGER:
					 		return "/managerHomePage.jsp";
						case User.WORKER:
							return "/workerHomePage.jsp";
						default:
							request.getSession().setAttribute("errorMessage", "Invalid field 'Positions'");
							return "/Error.jsp";
					}
				}
			} catch (SQLException exception) {
				logger.getExceptionTextFileLogger().error(exception);
			}
		} else {
			request.getSession().setAttribute("errorMessage", "Invalid username/password.");
			return "/error.jsp";
		}
		
		request.getSession().setAttribute("errorMessage", "Data base error.");
		return "/error.jsp";
	}
	
}
