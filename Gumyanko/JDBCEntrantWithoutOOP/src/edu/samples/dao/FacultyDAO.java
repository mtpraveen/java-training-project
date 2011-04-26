package edu.samples.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import edu.samples.DBConnector;
import edu.samples.dao.Faculty;

public class FacultyDAO {
	
	public Faculty createFaculty(String nameFaculty) {
		Connection connection = DBConnector.getConnector().getConnection();
		String sql = "insert into Faculty values (null,'" + nameFaculty + "')";
		ResultSet rs = null;
		Statement st = null;
		try {
			st = connection.createStatement();
			st.executeUpdate(sql);
			st = connection.createStatement();
			rs = st.executeQuery("Select Faculty_ID from Faculty");
			if (rs.next()) {
				Faculty faculty = new Faculty();
				faculty.setNameFaculty(nameFaculty);
				faculty.setFaculty_Id(rs.getLong(1));
				return faculty;
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

	
	public Faculty findFacultyById(long id) {
		Connection connection = DBConnector.getConnector().getConnection();
		String sql = "Select * from Faculty where Faculty_ID=" + id;
		ResultSet rs = null;
		Statement st = null;
		try {
			st = connection.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				Faculty faculty = new Faculty();
				faculty.setFaculty_Id(rs.getLong(1));
				faculty.setNameFaculty(rs.getString(2));
				return faculty;
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
	
	
	public Faculty DeleteFacultyWithId(long id) {
		Connection connection = DBConnector.getConnector().getConnection();
		String sql = "Delete from Faculty where Faculty_ID=" + id;
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
