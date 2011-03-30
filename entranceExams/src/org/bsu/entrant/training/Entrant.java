package org.bsu.entrant.training;
public class Entrant implements IEntrant {
	private int id;
	private String fioEntrant;
	private String adress;
	private String phone;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFioEntrant() {
		return fioEntrant;
	}

	public void setFioEntrant(String fioEntrant) {
		this.fioEntrant = fioEntrant;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}




	public void registrationOnFaculty() {
		Faculty.chFaculty = true;
		System.out.println("јбитуриент зарегистрирован");
	}

	public void passExaminations() {
		Examinations.isPassed=true;
		if (Faculty.chFaculty == true){
	//	Lecturer bes = new Lecturer();
	//	bes.SetMark();
	//	bes.averageMark();
	//	bes.getMark();
	//	bes.getAver();
		
		System.out.println("Ёкзамены сданы!");
		}	
	}


	
	public static void main(String[] args){
		Entrant Bart = new Entrant();
		Bart.registrationOnFaculty();
		Bart.passExaminations();
		if (Examinations.isPassed== true){
		Lecturer bes = new Lecturer();
		bes.SetMark();
		bes.averageMark();
		bes.getMark();
		bes.getAver();
		bes.setPassingGrade(5);
		bes.enrollEnrant();
		}
	}
	
	
}
