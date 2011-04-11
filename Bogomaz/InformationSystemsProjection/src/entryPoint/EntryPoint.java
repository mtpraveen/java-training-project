/**
 * 
 */
package entryPoint;

//import java.awt.List;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import education.Archive;
import education.Course;
import education.Student;
import education.Teacher;

/**
 * @author �������
 * ������� �����������. ������������� ��������� ������ �� ����. ������� ������������ �� ����, 
 * ��������� � �� ��������� ������������� ���������� ������, ������� ����������� � ������.
 * ���������, �������������� � ������ ��� �������� ����� ���� ���������.
 *	
 * ������ � ��������������, ��������� � ������ �������� � ������ � ����������� txt. 
 * ����� ��������� � zip-�����. ��������� ������ � �������.

 * ������� ����� �� ������������ �� ���������� ����� � ���� csv �����, � ������� ��������
 * ���������� � �����, ��������� � �� ������� �� ��������� �� ������� ����.
 */
public class EntryPoint {
	
	private static List<Student> students;
	private static List<Course> courses;
	private static List<Teacher> teachers;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Start.");
		
		readDataFromZipFile();//���������� ������� ���������, ������, ��������������
		Student[] sa = students.toArray(new Student[]{});
		Teacher[] ta = teachers.toArray(new Teacher[]{});
		Course[] ca = courses.toArray(new Course[]{});
		ta[0].declareCourseRegistration(ca[0], 8);
		ta[1].declareCourseRegistration(ca[1], 12);
		ta[2].declareCourseRegistration(ca[2],20);
		ta[3].addCourse(ta[2].getCourses().get(0));//���������� ��������������� ������������� �� ����
		System.out.println("���������� ������ ��������� �� ����: \n�������� �������� - ��� �������� - ���������� ������");
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 10; j++)
				System.out.println(ca[i].getSubjectName() + " - " + sa[i * 10 + j].getName() + " - " + sa[i * 10 + j].tryToRegister(ta[i], ca[i]));//������ ��������� �� ����� � ������� ���������� ������
		ta[0].study(ca[0]);//��������
		ta[1].study(ca[1]);
		ta[2].study(ca[2]);
		System.out.println("\n��������� ������ ����� ������ ��������:" + sa[29].tryToRegister(ta[1], ca[1]));//������� ������ �������� �� ���� ����� ��� �������� ��������
		ta[0].setMarks(ca[0]);//����������� ������� (��������� �������)
		ta[2].setMarks(ca[2]);
		ta[0].saveToArchive(ca[0]);//���������� ����������� � �����
		ta[2].saveToArchive(ca[2]);
		System.out.println("\n����� ����������� ������:\n");
		for (Course course : Archive.Instance().getCourses()){
			System.out.println("�������� �����:" + course.getSubjectName());
			System.out.println("������ ��������������:");
			for (Teacher teacher : course.getTeachers())
				System.out.println(teacher.getName());
			System.out.println("������: ������� - �������");
			for (Student student : course.getStudents())
				System.out.println(student.getName() + " - " + student.findMark(course));
		}
		reportToCsv();
		
		System.out.println("End.");
	}
	//���� ������ ��������� � ����� ����������
	private static void reportToCsv(){
		BufferedWriter bw = null;
		File resultFile =null;
		try{
			resultFile = new File("Report.csv");
			resultFile.createNewFile();
			bw = new BufferedWriter(new FileWriter(resultFile));
			for (Course course : Archive.Instance().getCourses()){
				bw.write("\n" + course.getSubjectName() + "\r\n");
				bw.write("������ ��������������:" + "\r\n");
				for (Teacher teacher : course.getTeachers())
					bw.write(teacher.getName() + ";\r\n");
				bw.write("���������� ��������:" + "\r\n");
				bw.write("�������;�������" + "\r\n");
				for (Student student : course.getStudents())
					bw.write(student.getName() + ";" + student.findMark(course) + "\r\n");
			}
			bw.close();
		}
		catch(IOException exc){}
		finally{}
	}
	//Zip-���� � ������� ������ ���������� � ����� ����������
	private static void readDataFromZipFile(){
		ZipFile zf = null;
		ZipEntry entry = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		BufferedReader br = null;
		File tempFile = null;
		int currentByte;
		int buffer = 1024;
		byte[] data = new byte[buffer];
		try{
			zf = new ZipFile(new File("Facultative.zip"));
			Enumeration zipFilesEntries = zf.entries();
			while(zipFilesEntries.hasMoreElements()){
				entry = (ZipEntry)zipFilesEntries.nextElement();
				tempFile = new File(entry.getName());
				bis = new BufferedInputStream(zf.getInputStream(entry));
				bos = new BufferedOutputStream(new FileOutputStream(tempFile));
				while((currentByte = bis.read(data, 0, buffer)) > 0)
					bos.write(data, 0, currentByte);
				bos.flush();
				bos.close();
				bis.close();
				br = new BufferedReader(new FileReader(tempFile));
				if (tempFile.getName().equals("Courses.txt"))
					courses = readFromCourses(br);
				if (tempFile.getName().equals("Students.txt"))
					students = readFromStudents(br);
				if (tempFile.getName().equals("Teachers.txt"))
					teachers = readFromTeachers(br);
				br.close();
				tempFile.delete();
			}
		}
		catch(FileNotFoundException exc){System.out.println(exc.getMessage());}
		catch(IOException exc){System.out.println(exc.getMessage());}
	}
	
	private static List<Course> readFromCourses(BufferedReader br) throws IOException{
		List<Course> courses = new ArrayList<Course>();
		String line = null;
		while ((line = br.readLine()) != null)
			courses.add(new Course(line, 100));
		return courses;
	}
	private static List<Teacher> readFromTeachers(BufferedReader br) throws IOException{
		String line = null;
		List<Teacher> teachers = new ArrayList<Teacher>();
		while ((line = br.readLine()) != null)
			teachers.add(new Teacher(line));
		return teachers;
	}
	private static List<Student> readFromStudents(BufferedReader br) throws IOException{
		String line = null;
		List<Student> students = new ArrayList<Student>();
		while ((line = br.readLine()) != null)
			students.add(new Student(line));
		return students;
	}
}
