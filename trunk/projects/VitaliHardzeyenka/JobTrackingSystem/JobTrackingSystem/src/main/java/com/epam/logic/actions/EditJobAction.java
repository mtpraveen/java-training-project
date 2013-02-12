package com.epam.logic.actions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.logic.Logger;
import com.epam.logic.database.DataBaseWriter;
import com.epam.logic.model.Job;
import com.epam.logic.model.Priority;
import com.epam.logic.model.Status;
import com.epam.logic.model.User;
import com.mysql.jdbc.Connection;

/**
 * @author EXHUMLOKI
 * Describe how will be performed edition of record 
 * in 'jobs' table.
 */
public class EditJobAction extends AbstractAction {
	
	private final Logger logger = new Logger(org.apache.log4j.Logger.getLogger(EditJobAction.class.getClass()));

	@Override
	/**
	 * Perform edition of record in table 'jobs'.
	 * Perform two major tasks: 
	 * 	only show editJob.jsp with filled fields for edition manually they by user;
	 * 	perform edition task in data base (using data base query 'update'). 
	 */
	public String perform(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		DataBaseWriter dataBaseWriter = new DataBaseWriter();
		Connection connection = null;
		String query = null;
		Job job = null;
		ResultSet resultSet = null;
		
		// Show page with filled fields, record has been searched by id number.
		if (request.getParameter("update").equals("false")) { // show EditJob.jsp for editing job
			resultSet = find("jobs", Job.JOB_ID, request.getParameter("id"));
			try {
				if (resultSet.first()) {
					int id = (int) resultSet.getObject(Job.JOB_ID);
					job = createJobById(id);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			session.setAttribute("job", job);
			return "/editJob.jsp";
		} else { // update record in data base
			try {
				int id = Integer.valueOf(request.getParameter("jobId"));
				String name = request.getParameter("name");
				String description = request.getParameter("description");
				Date beginDate = (Date) (new SimpleDateFormat("yyyy-MM-dd")).parse(request.getParameter("beginDate"));
				Date endDate = (Date) (new SimpleDateFormat("yyyy-MM-dd")).parse(request.getParameter("endDate"));
				int percents = Integer.valueOf(request.getParameter("percents"));
				Status status = createStatusById((int) findField((ResultSet) find("statuses", Status.STATUS, request.getParameter("status")), Status.STATUS_ID));
				Priority priority = createPriorityById((int) findField(find("priorities", Priority.PRIORITY, request.getParameter("priority")), Priority.PRIORITY_ID));
				User worker = createUserById(Integer.valueOf(request.getParameter("worker")));
				User manager = createUserById(Integer.valueOf(request.getParameter("manager")));

				job = new Job(name, description, percents, beginDate, endDate, status, priority, worker, manager);
				if (job != null) {
					query = String.format("UPDATE jobs SET name='%s', description='%s', begin_date='%s', end_date='%s', percents='%s', " +
							"status_id='%s', priority_id='%s', worker_id='%s', manager_id='%s' WHERE job_id='%s';", 
							name, description, new SimpleDateFormat("yyyy-MM-dd").format(beginDate), 
							new SimpleDateFormat("yyyy-MM-dd").format(endDate), percents, status.getStatusId(), 
							priority.getPriorityId(), worker.getId(), manager.getId(), id);

					dataBaseWriter.update(query);			
				}
			} catch (ParseException exception) {
				logger.getExceptionTextFileLogger().error(exception);
			} finally {
				if (connection != null) {
					try {
						connection.close();
					} catch (SQLException exception) {
						logger.getExceptionTextFileLogger().error(exception);
					}
				}
			}
			
			return "/managerHomePage.jsp"; // TODO find need jsp 
		}
	}
}
