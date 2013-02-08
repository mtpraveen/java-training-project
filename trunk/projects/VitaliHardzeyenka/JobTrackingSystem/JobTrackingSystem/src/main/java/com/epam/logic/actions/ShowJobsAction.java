package com.epam.logic.actions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.logic.Logger;
import com.epam.logic.database.DataBaseReader;
import com.epam.logic.model.Job;
import com.epam.logic.model.Jobs;
import com.epam.logic.model.User;
import com.epam.logic.model.Users;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;

/**
 * @author EXHUMLOKI
 * Class describe how will to show jobs for manager and worker.
 */
public class ShowJobsAction extends AbstractAction {
	
	private final Logger logger = new Logger(org.apache.log4j.Logger.getLogger(ShowJobsAction.class.getClass()));

	@Override
	/**
	 * Show jobs of specified user.
	 */
	public String perform(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		ResultSet jobsResultSet = null;
		ResultSet usersResultSet = null;
		Jobs jobs = null;
		User user = null;
		Users users = new Users();
		
		
		if (!request.getParameter("userId").equals("all")) { // show all jobs for all users (use for manager)
			usersResultSet = (ResultSet) find("users", User.USER_ID, Integer.valueOf(request.getParameter("userId")));
			
			try {
				if (usersResultSet.first()) {
					int id = (int) usersResultSet.getObject(User.USER_ID);
					user = createUserById(id);
				}
			} catch (SQLException exception) {
				logger.getExceptionTextFileLogger().error(exception);
			}
			
			jobsResultSet = searchJobs(user.getId());
			jobs = new Jobs(createJobsList(jobsResultSet));
			
			user.setJobs(jobs);
			users.getUsers().add(user);
			session.setAttribute("users", users);
		} else { // show all jobs for specified user
			usersResultSet = (ResultSet) find("users", User.POSITION_ID, String.valueOf(User.WORKER));
			
			try {
				while (usersResultSet.next()) {
					int id = (int) usersResultSet.getObject(User.USER_ID);
					user = createUserById(id);
					
					if (user != null) {
						jobsResultSet = searchJobs(user.getId());
						jobs = new Jobs(createJobsList(jobsResultSet));
						user.setJobs(jobs);
						users.getUsers().add(user);
					}
				}
				session.setAttribute("users", users);
			} catch (SQLException exception) {
				logger.getExceptionTextFileLogger().error(exception);
			}
		}
		
		return request.getParameter("backJsp").toString();
	}
	
	/**
	 * Creating jobs list by ResultSet from SELECT data base query.
	 * @param resultSet - ResultSet from SELECT data base query.
	 * @return - list of jobs selected by need parameters.
	 */
	private List<Job> createJobsList(ResultSet resultSet) {
		List<Job> jobs = new ArrayList<>();
		
		try {
			if (resultSet != null) {
				while (resultSet.next()) {
					int id = (int) resultSet.getObject(Job.JOB_ID);
					jobs.add(createJobById(id));
				}
			}
			
			return jobs;
		} catch (SQLException exception) {
			logger.getExceptionTextFileLogger().error("ShowJobsAction.createJobsList(); Line: 126. " + exception);
			return null;
		}
	}
	
	/**
	 * Searching jobs by users that perform it.
	 * @param workerId - id number by which searching will be perform.
	 * @return - result set of searching.
	 */
	public ResultSet searchJobs(int workerId) {
		DataBaseReader dataBaseReader = new DataBaseReader();
		Connection connection = (Connection) dataBaseReader.createConnection("localhost:3306", "job_tracking_system", "root", "root");
		ResultSet resultSet = null;
		String query = null;

		if (connection != null) {
			try {
				query = "SELECT jobs.* FROM jobs WHERE jobs.worker_id='" + workerId + "';";
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
				logger.getExceptionTextFileLogger().error("ShowJobsAction.searchJobs(); Line: 155. " + exception);
			} finally {
				// TODO close connection
			}
		}

		return null;
	}

}