package zip;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;
import java.util.zip.ZipInputStream;

import position.Doctor;
import position.Nurse;
import position.Patient;
import position.Person;
import position.Sex;

public class CsvReader {
	private StreamTokenizer stream = null;

	public CsvReader(ZipInputStream in) {
		this.stream = new StreamTokenizer(in);
		this.stream.whitespaceChars(';', ';');
	}

	public CsvReader (DataInputStream in){
		this.stream = new StreamTokenizer(in);
		this.stream.whitespaceChars(';', ';');
	}
	
	public ArrayList<Person> readFile(int fileIndex) throws IOException {
		ArrayList<Person> persons = new ArrayList<Person>();
		int nextToken = stream.nextToken();
		while (nextToken != StreamTokenizer.TT_EOF) {
			String name, surname;
			int age, birthday[] = new int[3];
			Sex sex;
			name = stream.sval;
			stream.nextToken();
			surname = stream.sval;
			stream.nextToken();
			age = Integer.valueOf(stream.sval);
			stream.nextToken();
			sex = Sex.valueOf(stream.sval);
			stream.nextToken();
			StringTokenizer tokenizer = new StringTokenizer(stream.sval,".");
			int i=0;
			while(tokenizer.hasMoreTokens()) {
				birthday[i] = Integer.valueOf(tokenizer.nextToken());
				i++;
			}
			Calendar dateOfBirth = Calendar.getInstance();
			dateOfBirth.set(Calendar.YEAR, birthday[2]);
			dateOfBirth.set(Calendar.MONTH, birthday[1]);
			dateOfBirth.set(Calendar.DAY_OF_MONTH, birthday[0]);
			switch (fileIndex) {
			case 0:
				int patientID;
				int patientDoctorID;
				String diagnosis,
				medication;
				stream.nextToken();
				tokenizer = new StringTokenizer(stream.sval,".");
				i=0;
				while(tokenizer.hasMoreTokens()) {
					birthday[i] = Integer.valueOf(tokenizer.nextToken());
					i++;
				}
				Calendar dateOfReceipt = Calendar.getInstance();
				dateOfReceipt.set(Calendar.YEAR, birthday[2]);
				dateOfReceipt.set(Calendar.MONTH, birthday[1]);
				dateOfReceipt.set(Calendar.DAY_OF_MONTH, birthday[0]);
				stream.nextToken();
				diagnosis = stream.sval;
				stream.nextToken();
				medication = stream.sval;
				stream.nextToken();
				patientID = Integer.valueOf(stream.sval);
				stream.nextToken();
				patientDoctorID = Integer.valueOf(stream.sval);
				Patient patient = new Patient(name, surname, age, sex,
						dateOfBirth, dateOfReceipt, diagnosis, medication,
						patientID,patientDoctorID);
				persons.add(patient);
				nextToken = stream.nextToken();
				break;
			case 1:
				stream.nextToken();
				String specialty = stream.sval;
				int doctorID;
				stream.nextToken();
				doctorID = Integer.valueOf(stream.sval);
				Doctor doctor = new Doctor(name, surname, age, sex,
						dateOfBirth, specialty, doctorID);
				persons.add(doctor);
				nextToken = stream.nextToken();
				break;
			case 2:
				stream.nextToken();
				int nurseID = Integer.valueOf(stream.sval);
				Nurse nurse = new Nurse(name, surname, age, sex, dateOfBirth,
						nurseID);
				persons.add(nurse);
				nextToken = stream.nextToken();
				break;
			default:
				throw new IOException("Wrong file name.");
			}
		}
		return persons;

	}


}
