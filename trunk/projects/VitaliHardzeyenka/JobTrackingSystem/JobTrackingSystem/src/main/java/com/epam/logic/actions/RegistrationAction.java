package com.epam.logic.actions;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.logic.Encryption;
import com.epam.logic.Logger;
import com.epam.logic.database.DataBaseWriter;
import com.epam.logic.model.Position;
import com.epam.logic.model.User;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;

/**
 * @author Gordeenko_XP
 * Class use for registration action.
 */
public class RegistrationAction extends AbstractAction {

	Logger logger = new Logger(org.apache.log4j.Logger.getLogger(RegistrationAction.class.getClass()));

	@Override
	/**
	 * Perform write new user into data base and return home page for user (worker of manager).
	 */
	public String perform(HttpServletRequest request, HttpServletResponse response) {
		Encryption encryption = new Encryption();

		String login = request.getParameter("login");
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String password = encryption.getHashCode(request.getParameter("password"));
		String email = request.getParameter("email");
		String position = request.getParameter("position");		
		ResultSet resultSet = (ResultSet) find("users", User.LOGIN, login);

		if (resultSet == null) {
			if (saveNewUser(login, name, surname, password, email, position)) {
				return "logOn.jsp";
			} else {
				request.getSession().setAttribute("errorMessage", "Information hasnt been writen into data base.");
				return "/error.jsp";
			}
		} else {
			request.getSession().setAttribute("errorMessage", "User with same login exist yet.");
			return "/error.jsp";
		}
	}

	/**
	 * Register user.
	 * Save new user in data base.
	 * Parameters are values for creating new object of User
	 * and add new record into data base. 
	 * @param login - user login
	 * @param name - user name
	 * @param surname - user surname
	 * @param password - user password
	 * @param email - user email
	 * @param position - position id of user (1 - manager, 2 - worker).
	 * @return true if new user has been writing into data base, false otherwise.
	 */
	private boolean saveNewUser(String login, String name, String surname, String password, String email, String position) {
		DataBaseWriter dataBaseWriter = new DataBaseWriter();
		ResultSet resultSet = (ResultSet) find("positions", "position", position);
		int positionId = 0;
		User user = null;

		try {
			if (resultSet != null) {
				while (resultSet.next()) {
					positionId = (int) resultSet.getObject(Position.POSITION_ID);

					user = new User(login, name, surname, password, email, createPositionById(positionId));

					// Add new record into data base.
					if (user != null) {
						return dataBaseWriter.insert("users", login, name, surname, password, email, positionId);
					}
				}
			}
		} catch (SQLException | ClassNotFoundException exception) {
			logger.getExceptionTextFileLogger().error(exception);
		}

		return false;
	}

}
