package edu.samples.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Lecturer {
	private int lecturer_Id;
	private String nameLecturer;
	
	public int getLecturer_Id() {
		return lecturer_Id;
	}
	public void setLecturer_Id(int lecturer_Id) {
		this.lecturer_Id = lecturer_Id;
	}
	public String getNameLecturer() {
		return nameLecturer;
	}
	public void setNameLecturer(String nameLecturer) {
		this.nameLecturer = nameLecturer;
	}
	@Override
	public String toString() {
		return "Lecturer [lecturer_Id=" + lecturer_Id + ", nameLecturer="
				+ nameLecturer + "]";
	}
	
	
	
	
}

	
		


