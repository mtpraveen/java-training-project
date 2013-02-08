package com.epam.logic.actions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.logic.Logger;
import com.epam.logic.model.User;
import com.epam.logic.model.Users;
import com.mysql.jdbc.ResultSet;

/**
 * Class use for show users from data base.
 * @author Gordeenko_XP
 */
public class ShowUsersAction extends AbstractAction {

	Logger logger = new Logger(org.apache.log4j.Logger.getLogger(ShowUsersAction.class.getClass()));
	
	@Override
	/**
	 * Get users from data base and send list of users to http session in ManagerViewWorkersList.jsp.
	 */
	public String perform(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		ResultSet resultSet = (ResultSet) find("users", User.POSITION_ID, String.valueOf(User.WORKER));
		List<User> usersList = createUsersList(resultSet);
		
		session.setAttribute("users", new Users(usersList));
		
		return "/managerViewWorkersList.jsp";
	}
	
	/**
	 * Create list of User objects.
	 * @param resultSet - result set of searching users in data base.
	 * @return - list of User objects.
	 */
	private List<User> createUsersList(ResultSet resultSet) {
		List<User> users = new ArrayList<>();
		
		// Creating users list.
		try {
			while (resultSet.next()) {
				int id = (int) resultSet.getObject(User.USER_ID);
				users.add(createUserById(id));
			}
			
			return users;
		} catch (SQLException exception) {
			logger.getExceptionTextFileLogger().error(exception);
			return null;
		}
	}
	
}
