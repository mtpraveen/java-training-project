package org.bsu.entrant.training;

import java.io.FileWriter;
import java.io.IOException;

public class Lecturer implements ILecturer {
	private int id;
	private String nameLecturer;
	static float passingGrade = 6;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNameLecturer() {
		return nameLecturer;
	}

	public void setNameLecturer(String nameLecturer) {
		this.nameLecturer = nameLecturer;
	}

	public void SetMark(Entrant q) {
		Mark ma = new Mark();
		float aver = 0;
		if (q.isPassedExams() == true) {
			if ((q.getChosenFaculty() == "MSF")
					|| (q.getChosenFaculty() == "SF")
					|| q.getChosenFaculty() == "FEIS") {
				// for (int j = 0; j < 3; j++)
				q.setExams(Examinations.Mathematics, ma.setValueMark());
				q.setAver(q.getExams().get(Examinations.Mathematics));
				aver += q.getAver();
				q.setExams(Examinations.Physics, ma.setValueMark());
				q.setAver(q.getExams().get(Examinations.Physics));
				aver += q.getAver();
				q.setExams(Examinations.Russian, ma.setValueMark());
				q.setAver(q.getExams().get(Examinations.Russian));
				aver += q.getAver();
				aver = aver / 3;
				q.setAver(aver);
				// System.out.println(q.getAver());
				System.out.println(q.getExams().toString());
				// System.out.println(q.getChosenFaculty());
			} else {
				q.setExams(Examinations.Mathematics, ma.setValueMark());
				q.setAver(q.getExams().get(Examinations.Mathematics));
				aver += q.getAver();
				q.setExams(Examinations.English, ma.setValueMark());
				q.setAver(q.getExams().get(Examinations.English));
				aver += q.getAver();
				q.setExams(Examinations.Russian, ma.setValueMark());
				q.setAver(q.getExams().get(Examinations.Russian));
				aver += q.getAver();
				aver = aver / 3;
				q.setAver(aver);
				// System.out.println(q.getAver());
				System.out.println(q.getExams().toString());
				// System.out.println(q.getChosenFaculty());
			}
		}
	}

	public void saveLecturer(Lecturer q, FileWriter dataFile) {

		try {

			// FileWriter dataFile = new FileWriter("L:\\R.txt");
			// dataFile.write(q.getClass().getSimpleName()+ ";");
			dataFile.write(String.valueOf(q.getId()) + ";");
			dataFile.write(q.getNameLecturer() + ";");

			// dataFile.close();
		} catch (IOException e) {
			System.out.println("Ошибка записи: " + e.toString());
		}

	}

	public void enrollEnrant(Entrant q) {
		System.out.println("Средний балл =" + q.getAver());
		System.out.println("Проходной балл = " + passingGrade);
		if (q.getAver() < passingGrade) {
			q.setStatus("Не зачислен");
			System.out.println(q.getStatus());
		} else {
			q.setStatus("Зачислен");
			System.out.println(q.getStatus());
		}

	}

}
