package zip;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import position.Doctor;
import position.Nurse;
import position.Patient;
import position.Person;
import position.Sex;

public class test {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		ArrayList<Nurse> nurse = new ArrayList<Nurse>();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2000);
		calendar.set(Calendar.MONTH, 11);
		calendar.set(Calendar.DAY_OF_MONTH, 31);
		Nurse nurse1=new Nurse("Mihail", "Melyuh", 20, Sex.MAN,calendar,1);
		Nurse nurse2=new Nurse("Kate", "Ivanova", 21, Sex.WOMAN,calendar,2);
		nurse.add(nurse1);
		nurse.add(nurse2);
		ArrayList<Doctor> doctor = new ArrayList<Doctor>();
		Doctor doctor1=new Doctor("Mihail", "Melyuh", 20, Sex.MAN,calendar,"Lager",1);
		Doctor doctor2=new Doctor("Kate", "Ivanova", 21, Sex.WOMAN,calendar,"More",2);
		doctor.add(doctor1);
		doctor.add(doctor2);
		ArrayList<Patient> patient = new ArrayList<Patient>();
		Patient patient1=new Patient("Mihail", "Melyuh", 20, Sex.MAN,calendar,calendar, "Hei", "Lager", 1,1);
		Patient patient2=new Patient("Kate", "Ivanova", 21, Sex.WOMAN,calendar,calendar, "More","Hoi", 2,2);
		patient.add(patient1);
		patient.add(patient2);
		ArrayList<ArrayList<? extends Person>> persons=new ArrayList<>();
		persons.add(patient);
		persons.add(doctor);
		persons.add(nurse);
		ZipWriter writerDoc = new ZipWriter("hospital.zip");
		writerDoc.writeData(persons);
		writerDoc.close();
		ArrayList<Person> persons1= new ArrayList<Person>();
		ZipReader reader = new ZipReader("hospital.zip");
		persons1=reader.readData("Patient.csv");
		reader.close();
		System.out.println(persons1.toString());
	}

}
