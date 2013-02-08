package com.epam.logic.actions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.logic.Logger;
import com.epam.logic.database.DataBaseReader;
import com.epam.logic.model.Job;
import com.epam.logic.model.Position;
import com.epam.logic.model.Priority;
import com.epam.logic.model.Status;
import com.epam.logic.model.User;
import com.mysql.jdbc.Connection;

/**
 * @author EXHUMLOKI
 * Class describe abstract action which is parent for all 
 * other action classes. It also describe actions with data base,
 * which are general for all child classes, and this actions use in
 * some of it.
 */
public abstract class AbstractAction {
	
	private final Logger logger = new Logger(org.apache.log4j.Logger.getLogger(AbstractAction.class.getName()));
	
	/**
	 * This method overriding in all child classes.
	 * Use for perform specified actions.
	 * @param request - request from servlet.
	 * @param response - response to jsp.
	 * @return
	 */
	public abstract String perform(HttpServletRequest request, HttpServletResponse response);
	
	/**
	 * Find records in data base by table name, field name and field value.
	 * @param tableName - table name.
	 * @param fieldName - field name.
	 * @param fieldValue - field value as string.
	 * @return result set of searching.
	 */
	protected ResultSet find(String tableName, String fieldName, String fieldValue) {
		DataBaseReader dataBaseReader = new DataBaseReader();
		Connection connection = (Connection) dataBaseReader.createConnection("localhost:3306", "job_tracking_system", "root", "root");
		String query = null;
		ResultSet resultSet = null;

		if (connection != null) {
			try {
				query = String.format("SELECT * FROM %s WHERE %s = '%s';", tableName, fieldName, fieldValue);
				resultSet = (ResultSet) dataBaseReader.select(connection, query);
				if (resultSet != null) {
					if (resultSet.next()) {
						resultSet.beforeFirst();
						return resultSet;
					}
				} else {
					return null;
				}
			} catch (SQLException exception) {
				logger.getExceptionTextFileLogger().error(exception);
			}
		}
		
		return null;
	}
	
	/**
	 * Find records in data base by table and set of conditions.
	 * @param tableName - name of table in which record will be search.
	 * @param values - set of conditions for searching.
	 * @return
	 */
	protected ResultSet find(String tableName, Object ... values) {
		DataBaseReader dataBaseReader = new DataBaseReader();
		Connection connection = (Connection) dataBaseReader.createConnection("localhost:3306", "job_tracking_system", "root", "root");
		ResultSet resultSet = null;

		if (connection != null) {
			try {
				resultSet = (ResultSet) dataBaseReader.select(connection, tableName, values);
				if (resultSet != null) {
					if (resultSet.next()) {
						resultSet.beforeFirst();
						return resultSet;
					}
				} else {
					return null;
				}
			} catch (SQLException exception) {
				logger.getExceptionTextFileLogger().error(exception);
			}
		}
		
		return null;
	}
	
	/**
	 * Find field by its name in result set.
	 * @param resultSet - result set for searching (must be one record).
	 * @param fieldName - field name, which value need to search.
	 * @return - value of field.
	 */
	protected Object findField(ResultSet resultSet, String fieldName) {
		try {
			resultSet.first(); 
			return resultSet.getObject(fieldName);
		} catch (SQLException e) {
			logger.getExceptionTextFileLogger().error(e);
			return null;
		}
	}
	
	/**
	 * Find user in table 'users' by its id number.
	 * Get values of all fields in searching record.
	 * Create object of User class by these values.
	 * @param userId - id number of need user.
	 * @return object of User class.
	 */
	protected User createUserById(int userId) {
		ResultSet resultSet = null;
		try {
			resultSet = find("users", User.USER_ID, userId); // find user by id
			resultSet.first();
			
			// Getting values of fields and converting to need type.
			int id = (int) resultSet.getObject(User.USER_ID);
			String login = String.valueOf(resultSet.getObject(User.LOGIN));
			String name = String.valueOf(resultSet.getObject(User.USER_NAME));
			String surname = String.valueOf(resultSet.getObject(User.SURNAME));
			String password = String.valueOf(resultSet.getObject(User.PASSWORD));
			String email = String.valueOf(resultSet.getObject(User.EMAIL));
			Position position = createPositionById((int) resultSet.getObject(User.POSITION_ID));
			
			// Create user object by these values.
			return new User(id, login, name, surname, password, email, position);
		} catch (SQLException exception) {
			logger.getExceptionTextFileLogger().error(exception);
			return null;
		}
	}
	
	/**
	 * Find job in table 'jobs'.
	 * Get values of all fields in searched record.
	 * Create object of 'Job' class by these values.
	 * @param jobId - id number of need job.
	 * @return object of Job class.
	 */
	protected Job createJobById(int jobId) {
		ResultSet resultSet = null;
		try {
			resultSet = find("jobs", Job.JOB_ID, jobId); // find record by id
			resultSet.first();
			
			// Getting values of fields.
			int id = (int) resultSet.getObject(Job.JOB_ID);
			String name = String.valueOf(resultSet.getObject(Job.JOB_NAME));
			String description = String.valueOf(resultSet.getObject(Job.DESCRIPTION));
			int percents = (int) resultSet.getObject(Job.PERCENTS);
			Date beginDate = (Date) resultSet.getObject(Job.BEGIN_DATE);
			Date endDate = (Date) resultSet.getObject(Job.END_DATE);
			Status status = createStatusById((int) resultSet.getObject(Job.STATUS_ID));
			Priority priority = createPriorityById((int) resultSet.getObject(Job.PRIORITY_ID));
			User worker = createUserById((int) resultSet.getObject(Job.WORKER_ID));
			User manager = createUserById((int) resultSet.getObject(Job.MANAGER_ID));
			
			// Create object of Job class by values.
			return new Job(id, name, description, percents, beginDate, endDate, status, priority, worker, manager);
		} catch (SQLException exception) {
			logger.getExceptionTextFileLogger().error(exception);
			return null;
		}
	}
	
	/**
	 * Find record in table 'priorities' by id number.
	 * Getting values form result set.
	 * Create object of Priority class by values. 
	 * @param priorityId
	 * @return object of Priority class.
	 */
	protected Priority createPriorityById(int priorityId) {
		ResultSet resultSet = null;
		try {
			resultSet = find("priorities", Priority.PRIORITY_ID, priorityId);
			resultSet.first();
			int id = (int) resultSet.getObject(Priority.PRIORITY_ID);
			String priority = String.valueOf(resultSet.getObject(Priority.PRIORITY));
			return new Priority(id, priority);
		} catch (SQLException exception) {
			logger.getExceptionTextFileLogger().error(exception);
			return null;
		}
	}
	
	/**
	 * Find record in table 'statuses' by id number.
	 * By searching values from result set create object of Status class.
	 * @param statusId - id of need record
	 * @return object of Status class.
	 */
	protected Status createStatusById(int statusId) {
		ResultSet resultSet = null;
		try {
			resultSet = find("statuses", Status.STATUS_ID, statusId);
			resultSet.first();
			int id = (int) resultSet.getObject(Status.STATUS_ID);
			String status = String.valueOf(resultSet.getObject(Status.STATUS));
			return new Status(id, status);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Find record in table 'positions' by id number.
	 * By searching values from result set create object of Position class. 
	 * @param positionId - id of need record.
	 * @return object of Position class.
	 */
	protected Position createPositionById(int positionId) {
		ResultSet resultSet = null;
		try {
			resultSet = find("positions", Position.POSITION_ID, positionId);
			resultSet.first();
			int id = (int) resultSet.getObject(Position.POSITION_ID);
			String position = String.valueOf(resultSet.getObject(Position.POSITION));
			return new Position(id, position);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
