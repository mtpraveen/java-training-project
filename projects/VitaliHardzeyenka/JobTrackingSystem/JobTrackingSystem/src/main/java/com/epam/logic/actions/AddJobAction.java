package com.epam.logic.actions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.logic.Logger;
import com.epam.logic.database.DataBaseWriter;
import com.epam.logic.model.Priority;
import com.epam.logic.model.Status;
import com.epam.logic.model.User;

/**
 * @author Gordeenko_XP
 * Describe creating new job by user.
 * Read all need fields from form, 
 * create new object of Job,
 * write created job into data base.
 */
public class AddJobAction extends AbstractAction {
	
	private final Logger logger = new Logger(org.apache.log4j.Logger.getLogger(AddJobAction.class.getClass()));

	@Override
	/**
	 * Adding new record in data base to 'jobs' table.
	 * Object of Job class, on which will be add new record,
	 * has been created by values form form on web page.
	 */
	public String perform(HttpServletRequest request, HttpServletResponse response) {
		DataBaseWriter dataBaseWriter = new DataBaseWriter();
		
		if (request.getParameter("insert").equals("false")) {
			return "/addJob.jsp";
		} else {
			try {
				String name = request.getParameter("name");
				String description = request.getParameter("description");
				Date beginDate = (Date) (new SimpleDateFormat("yyyy-MM-dd")).parse(request.getParameter("beginDate"));
				Date endDate = (Date) (new SimpleDateFormat("yyyy-MM-dd")).parse(request.getParameter("endDate"));
				int percents = 0;
				Status status = createStatusById((int) findField((ResultSet) find("statuses", Status.STATUS, request.getParameter("status")), Status.STATUS_ID));
				Priority priority = createPriorityById((int) findField(find("priorities", Priority.PRIORITY, request.getParameter("priority")), Priority.PRIORITY_ID));
				User worker = createUserById(Integer.valueOf(request.getParameter("worker")));
				User manager = createUserById(Integer.valueOf(request.getParameter("manager")));

				dataBaseWriter.insert("Jobs", name, description, percents, beginDate, endDate, 
									status.getStatusId(), priority.getPriorityId(), worker.getId(), manager.getId());
			} catch (SQLException | ParseException | ClassNotFoundException exception) {
				logger.getExceptionTextFileLogger().error(exception);
			}

			return "/addJob.jsp";
		}
	}
	
}
