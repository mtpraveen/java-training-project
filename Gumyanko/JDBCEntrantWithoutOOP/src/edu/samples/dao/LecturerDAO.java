package edu.samples.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import edu.samples.DBConnector;

public class LecturerDAO {
	public Lecturer createLecturer(String nameLecturer) {
		Connection connection = DBConnector.getConnector().getConnection();
		String sql = "insert into Lecturer values (null,'" + nameLecturer + "')";
		ResultSet rs = null;
		Statement st = null;
		try {
			st = connection.createStatement();
			st.executeUpdate(sql);
			st = connection.createStatement();
			rs = st.executeQuery("Select * from Lecturer");
			if (rs.next()) {
				Lecturer lecturer = new Lecturer();
				lecturer.setNameLecturer(nameLecturer);
				lecturer.setLecturer_Id(rs.getInt(1));
				return lecturer;
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

	
	public Lecturer findLecturerById(long id) {
		Connection connection = DBConnector.getConnector().getConnection();
		String sql = "Select * from Lecturer where Lecturer_ID=" + id;
		ResultSet rs = null;
		Statement st = null;
		try {
			st = connection.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				Lecturer lecturer = new Lecturer();
				lecturer.setLecturer_Id(rs.getInt(1));
				lecturer.setNameLecturer(rs.getString(2));
				return lecturer;
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
	
	
	public Lecturer DeleteLecturerWithId(long id) {
		Connection connection = DBConnector.getConnector().getConnection();
		String sql = "Delete from Lecturer where Lecturer_ID=" + id;
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
