package edu.samples.dao;

public class Faculty {
	private long faculty_Id;
	private String nameFaculty; 
	
	public long getFaculty_Id() {
		return faculty_Id;
	}
	public void setFaculty_Id(long faculty_Id) {
		this.faculty_Id = faculty_Id;
	}
	public String getNameFaculty() {
		return nameFaculty;
	}
	public void setNameFaculty(String nameFaculty) {
		this.nameFaculty = nameFaculty;
	}
	@Override
	public String toString() {
		return "Faculty [faculty_Id=" + faculty_Id + ", nameFaculty="
				+ nameFaculty + "]";
	}
	

}
