package edu.samples.dao;

public class Exams {
private int Exams_ID; 
private String NameExam; 
private int Lecturer_ID; 

public int getExams_ID() {
	return Exams_ID;
}
public void setExams_ID(int exams_ID) {
	Exams_ID = exams_ID;
}
public String getNameExam() {
	return NameExam;
}
public void setNameExam(String nameExam) {
	NameExam = nameExam;
}
public int getLecturer_ID() {
	return Lecturer_ID;
}
public void setLecturer_ID(int lecturer_ID) {
	Lecturer_ID = lecturer_ID;
}
@Override
public String toString() {
	return "Exams [Exams_ID=" + Exams_ID + ", NameExam=" + NameExam
			+ ", Lecturer_ID=" + Lecturer_ID + "]";
}


}
