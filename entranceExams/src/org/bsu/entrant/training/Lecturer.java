package org.bsu.entrant.training;
public class Lecturer implements ILecturer {
	private String fioLecturer;
	public String getFioLecturer() {
		return fioLecturer;
	}

	public void setFioLecturer(String fioLecturer) {
		this.fioLecturer = fioLecturer;
	}

	private float passingGrade;
	
	
	Mark ma = new Mark();

	public void SetMark() {

		for (int j = 0; j < ma.getI().length; j++)
			ma.setI(j, ((int) (1 + Math.random() * 10)));
	}

	public void getMark() {
		System.out.println("Оценки:");
		for (float p : ma.getI())
			System.out.println(" " + p);
	}

	public float averageMark() {
		float aver = 0, k = 0;
		for (int j = 0; j < ma.getI().length; j++)
			k += ma.getI()[j];
		aver = k / ma.getI().length;
		return aver;
	}

	public void getAver() {
		System.out.println("Средний балл = "+averageMark());
	}
	
	
		
	
    public float getPassingGrade() {
		return passingGrade;
	}

	public void setPassingGrade(float passingGrade) {
		this.passingGrade = passingGrade;
	}

	public void enrollEnrant() {
		System.out.println("Проходной балл = "+getPassingGrade());
		if (averageMark() < getPassingGrade())
			System.out.println("Не зачислен!!");
		else
			System.out.println("Зачислен!!");
	}

}
