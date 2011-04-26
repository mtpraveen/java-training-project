package edu.samples;

import java.sql.SQLException;

import edu.samples.dao.Exams;
import edu.samples.dao.ExamsDAO;
import edu.samples.dao.Faculty;
import edu.samples.dao.FacultyDAO;
import edu.samples.dao.Lecturer;
import edu.samples.dao.LecturerDAO;



public class Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			System.out.println("Trying to connect to DB...");
			FacultyDAO facDAO = new FacultyDAO();
			LecturerDAO lectDAO = new LecturerDAO();
			ExamsDAO exDAO = new ExamsDAO();

			Faculty physics = facDAO.createFaculty("physics");
			System.out.println(physics);
			
			
			Faculty faculty = facDAO.findFacultyById(9L);
			System.out.println(faculty);
			Faculty delFaculty =facDAO.DeleteFacultyWithId(13L);
			Lecturer p = lectDAO.createLecturer("p");
			System.out.println(p);
			Lecturer lecturer = lectDAO.findLecturerById(7);
			System.out.println(lecturer);
			
			Exams exams= exDAO.createLecturer("English");
			System.out.println(exams);
			Lecturer delLecturer =lectDAO.DeleteLecturerWithId(8);
			
			
			DBConnector.getConnector().getConnection().close();
			
		} catch (ClassNotFoundException e) {
			// driver is not found
		} catch (SQLException e) {
			// TODO log error
		}
	}

}
