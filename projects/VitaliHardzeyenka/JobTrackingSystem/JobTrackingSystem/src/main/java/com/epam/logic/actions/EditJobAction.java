package com.epam.logic.actions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.logic.Logger;
import com.epam.logic.database.DataBaseWriter;
import com.epam.logic.model.Job;
import com.epam.logic.model.Priority;
import com.epam.logic.model.Status;
import com.epam.logic.model.User;

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
				// TODO 
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

				
				Map<String, Object> conditions = new HashMap<>();
				conditions.put("name", name);
				conditions.put("description", description);
				conditions.put("begin_date", new SimpleDateFormat("yyyy-MM-dd").format(beginDate));
				conditions.put("end_date", new SimpleDateFormat("yyyy-MM-dd").format(endDate));
				conditions.put("percents", percents);
				conditions.put("status_id", status.getStatusId());
				conditions.put("priority_id", priority.getPriorityId());
				conditions.put("worker_id", worker.getId());
				conditions.put("manager_id", manager.getId());
				
				Map<String, Object> whereCon = new HashMap<>();
				whereCon.put("job_id", id);
				dataBaseWriter.update("jobs", conditions, whereCon);			
			} catch (ParseException exception) {
				logger.getExceptionTextFileLogger().error(exception);
			}
			
			return "/managerHomePage.jsp"; // TODO find need jsp 
		}
	}
}
