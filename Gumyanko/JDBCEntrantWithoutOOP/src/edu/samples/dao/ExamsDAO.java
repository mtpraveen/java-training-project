package edu.samples.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import edu.samples.DBConnector;

public class ExamsDAO {
	public Exams createLecturer(String nameExam) {
		Connection connection = DBConnector.getConnector().getConnection();
		String sql = "insert into Exams values (null,'" + nameExam + "',1)";
		ResultSet rs = null;
		Statement st = null;
		try {
			st = connection.createStatement();
			st.executeUpdate(sql);
			st = connection.createStatement();
			rs = st.executeQuery("Select * from Exams");
			if (rs.next()) {
				Exams exams = new Exams();
				exams.setNameExam(nameExam);
				exams.setExams_ID(rs.getInt(3));
				exams.setLecturer_ID(rs.getInt(1));
				return exams;
			}

		} catch (SQLException e) {
			//TODO log error
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return null;
	}

	
	public Exams findLecturerById(long id) {
		Connection connection = DBConnector.getConnector().getConnection();
		String sql = "Select * from Exams where Exams_ID=" + id;
		ResultSet rs = null;
		Statement st = null;
		try {
			st = connection.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				Exams exams = new Exams();
				exams.setExams_ID(rs.getInt(1));
				exams.setNameExam(rs.getString(2));
				exams.setLecturer_ID(rs.getInt(1));
				return exams;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			}

		}
		return null;
	}
	
	
	public Exams DeleteLecturerWithId(long id) {
		Connection connection = DBConnector.getConnector().getConnection();
		String sql = "Delete from Exams where Exams_ID=" + id;
		ResultSet rs = null;
		Statement st = null;
		try {
			st = connection.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		 finally {
			try {
					if (st != null)
					st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			}

		}
		return null;
	}
}
