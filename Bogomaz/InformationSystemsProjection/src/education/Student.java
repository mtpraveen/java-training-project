/**
 * 
 */
package education;

import java.util.*;
//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Facultative
//  @ File Name : Student.java
//  @ Date : 29.03.2011
//  @ Author : 
//
//

public class Student {
	private String name;
	private List<Course> courses;
	
	public Student(String name){
		this.name = name;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the courses
	 */
	public List<Course> getCourses() {
		return courses;
	}

	public boolean tryToRegister(Teacher teacher, Course course) {
		if (teacher.register(this, course)){
			if (courses == null)
				courses = new ArrayList<Course>();
			courses.add(course);
			return true;
		}
		else
			return false;
	}
	
	public int findMark(Course course) {
		return course.getMark(this);
	}
	
	public void endCourse(Course course){
		if (courses != null)
			courses.remove(course);
	}
}

