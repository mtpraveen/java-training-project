package org.bsu.entrant.training;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.crypto.NullCipher;

public class Entrant implements IEntrant {
	private int id;
	private boolean passedExams = false;
	private String nameEntrant;
	private String phone;
	private String status;
	private String chosenFaculty;
	private float aver;
	private Map<Examinations, Integer> exams = new HashMap<Examinations, Integer>();
	private List<Entrant> entrants;

	public Entrant() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Entrant(int id, String nameEntrant) {
		super();
		this.id = id;
		this.nameEntrant = nameEntrant;
		// TODO Auto-generated constructor stub
	}

	public boolean isPassedExams() {
		return passedExams;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public float getAver() {
		return aver;
	}

	public void setAver(float aver) {
		this.aver = aver;
	}

	public String getChosenFaculty() {
		return chosenFaculty;
	}

	public void setChosenFaculty(String chosenFaculty) {
		this.chosenFaculty = chosenFaculty;
	}

	public Map<Examinations, Integer> getExams() {
		return exams;
	}

	public void setExams(Examinations Examinations, int ex) {
		this.exams = exams;
		exams.put(Examinations, ex);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNameEntrant() {
		return nameEntrant;
	}

	public void setNameEntrant(String fioEntrant) {
		this.nameEntrant = fioEntrant;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void registrationOnFaculty(Faculty Faculty) {
		switch (Faculty) {
		case SF:
			System.out
					.println("Абитуриент зарегистрирован на строительном факультете");
			setChosenFaculty(Faculty.toString());

			break;
		case MSF:
			System.out
					.println("Абитуриент зарегистрирован на машиностроительном факультете");
			setChosenFaculty(Faculty.toString());

			break;
		case FEIS:
			System.out
					.println("Абитуриент зарегистрирован на электронно-информационном факультете");
			setChosenFaculty(Faculty.toString());

		case EF:
			System.out
					.println("Абитуриент зарегистрирован на экономическом факультете");
			setChosenFaculty(Faculty.toString());

			break;
		// default:
		// System.out.println("Такого факультета нет!!!");
		}
	}

	public void passExaminations(Entrant q) {
		if (chosenFaculty != null) {
			passedExams = true;
			System.out.println("Экзамены сданы!");
			// System.out.println(q.getChosenFaculty());
		}
	}

	public void export() {
		String s1 = "";
		try {
			// FileReader is = new FileReader("L:\\R.txt");
			BufferedReader is = new BufferedReader(new FileReader("L:\\R.txt"));
			int b, count = 0;

			String tmp = "";
			while ((tmp = is.readLine()) != null) {
				// \n использовать как разделитель
				String[] s = tmp.split("\\n");
				// вывод полученных строк
				for (String res : s)
					for (int i = 0; i < s.length; i++)

						s1 += s[i];
				// System.out.println(s1);
			}
			//System.out.println(s1);
			
			Entrant en1 = new Entrant();
			Entrant en2 = new Entrant();
			Lecturer l1 = new Lecturer();
			StringTokenizer st = new StringTokenizer(s1, ";");
			//System.out.println(st.countTokens());

			l1.setId(Integer.valueOf(st.nextToken()));
			l1.setNameLecturer(st.nextToken());

			en1.setId(Integer.valueOf(st.nextToken()));
			en1.setNameEntrant(st.nextToken());
			en1.setChosenFaculty(st.nextToken());
			en1.setStatus(st.nextToken());
			en2.setId(Integer.valueOf(st.nextToken()));
			en2.setNameEntrant(st.nextToken());
			en2.setChosenFaculty(st.nextToken());
			en2.setStatus(st.nextToken());
			
			//System.out.println(l1.getId());
			//System.out.println(l1.getNameLecturer());
			//System.out.println(en2.getChosenFaculty());
		
			

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Ошибка записи: " + e.toString());
		}

	}

	public void saveEntrant(Entrant q, FileWriter dataFile) {

		try {

			// FileWriter dataFile = new FileWriter("L:\\R.txt");
			int b, count = 0;
			// dataFile.write(q.getClass(). + ";");
			dataFile.write(String.valueOf(q.getId()) + ";");
			dataFile.write(q.getNameEntrant() + ";");
			dataFile.write(q.getChosenFaculty() + ";");
			dataFile.write(q.getStatus() + ";");
			// dataFile.close();
		} catch (IOException e) {
			System.out.println("Ошибка записи: " + e.toString());
		}

	}

	public static void main(String[] args) throws IOException {
		FileWriter dataFile = new FileWriter(".\\R.txt"); // File

		Entrant bart = new Entrant(1, "Bart");
		Entrant den = new Entrant(2, "Den");
		Lecturer bes = new Lecturer();
		bes.setNameLecturer("byob");
		bes.setId(5);
		Lecturer.passingGrade = 5; // Set passing score

		bart.registrationOnFaculty(Faculty.EF); // 1 entrant
		bart.passExaminations(bart);
		bes.SetMark(bart);
		bes.enrollEnrant(bart);

		den.registrationOnFaculty(Faculty.SF); // 2 entrant
		den.passExaminations(den);
		bes.SetMark(den);
		bes.enrollEnrant(den);

		bes.saveLecturer(bes, dataFile); // SavetoFile Lecturer
		bart.saveEntrant(bart, dataFile); // SavetoFile Entrant
		bart.saveEntrant(den, dataFile);
		den.export();   // export from File
		dataFile.close();



	}

}
