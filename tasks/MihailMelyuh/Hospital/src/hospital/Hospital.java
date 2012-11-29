package hospital;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import position.Doctor;
import position.Nurse;
import position.Patient;
import position.Person;
import position.Positions;
import zip.HospitalReader;
import zip.HospitalWriter;
import zip.ZipReader;

public class Hospital implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4911246164956671402L;
	private ArrayList<Patient> patients;
	private ArrayList<Doctor> doctors;
	private ArrayList<Nurse> nurses;
	private ArrayList<Assignment> assignment;
	private ArrayList<User> users;
	public static final String fileUsers="Users.bin";
	public static final String filePatientCsv="Patient.csv";
	public static final String fileDoctorCsv="Doctor.csv";
	public static final String fileNursetCsv="Nurse.csv";

	public Hospital() throws ClassNotFoundException, IOException {
		this.patients = new ArrayList<Patient>();
		this.doctors = new ArrayList<Doctor>();
		this.nurses = new ArrayList<Nurse>();
		this.assignment = new ArrayList<Assignment>();
		this.users = new ArrayList<User>();
		HospitalReader reader = new HospitalReader();
		this.users = reader.readUsers(fileUsers);
	}

	public synchronized String  appointDoctor(int patientID, int doctorID) {
		boolean doctoIDExist = false;
		boolean patientIDExist = false;
		for (Doctor doctor : this.doctors) {
			if (doctor.getDoctorID() == doctorID) {
				doctoIDExist = true;
				break;
			}
		}
		if (doctoIDExist) {
			for (Patient patient : this.patients) {
				if (patient.getPatientID() == patientID) {
					patientIDExist = true;
					patient.setDoctorID(doctorID);
					break;
				}
			}
			if (patientIDExist) {
				return "Doctor appointed";
			} else {
				return "Such patientID does not exist";
			}
		} else {
			return "Such doctoID does not exist";
		}
	}

	public synchronized String appointAssignment(int patientID, String text,
			Positions position, int personID) {
		boolean personIDExist = false;
		boolean patientIDExist = false;
		if (position.name().equals(Positions.Doctor.name())) {
			for (Doctor doctor : this.doctors) {
				if (doctor.getDoctorID() == personID) {
					personIDExist = true;
					break;
				}
			}
		} else {
			for (Nurse nurse : this.nurses) {
				if (nurse.getNurseID() == personID) {
					personIDExist = true;
					break;
				}
			}
		}
		if (personIDExist) {
			for (Patient patient : this.patients) {
				if (patient.getPatientID() == patientID) {
					patientIDExist = true;
					break;
				}
			}
			if (patientIDExist) {
				Assignment assignment = new Assignment(patientID, text,
						position, personID);
				this.assignment.add(assignment);
				return "Assignment appointed";
			} else {
				return "Such patientID does not exist";
			}
		} else {
			return "Such personID does not exist";
		}
	}

	public synchronized String removePatient(int patientID) {
		for (int i = 0; i < this.patients.size(); i++) {
			if (this.patients.get(i).getPatientID() == patientID) {
				this.patients.remove(i);
				return "Patient removed";
			}
		}
		for (int i = 0; i < this.assignment.size(); i++) {
			if (this.assignment.get(i).getPatientID() == patientID) {
				this.assignment.remove(i);
			}
		}
		return "Patient not found";
	}

	public String report() {
		String STRINGPREFIX = "%15.15s";
		String LONGSTRINGPREFIX = "%20.20s";
		String DATE = "%1$s.%2$s.%3$s";
		String result = String
				.format("%15.15s%15.15s%15.15s%15.15s%20.20s%20.20s%20.20s%20.20s%15.15s%15.15s%20.20s%15.15s%15.15s\n",
						"Имя", "Фамилия", "Возраст", "Пол", "Дата рождения",
						"Дата поступления", "Диагноз", "История болезни",
						"№ пациента", "№ доктора", "Назначение", "Специальность",
						"№ исплнителя");
		if (!this.patients.isEmpty()) {
			for (int i = 0; i < this.patients.size(); i++) {
				Patient patient = this.patients.get(i);
				result = result.concat(String.format(STRINGPREFIX,
						patient.getName()));
				result = result.concat(String.format(STRINGPREFIX,
						patient.getSurname()));
				result = result.concat(String.format(STRINGPREFIX,
						String.valueOf(patient.getAge())));
				result = result.concat(String.format(STRINGPREFIX, patient
						.getSex().name()));
				result = result.concat(String.format(LONGSTRINGPREFIX, String
						.format(DATE, patient.getDateOfBirth().get(5), patient
								.getDateOfBirth().get(2), patient
								.getDateOfBirth().get(1))));
				result = result.concat(String.format(LONGSTRINGPREFIX, String
						.format(DATE, patient.getDateOfBirth().get(5), patient
								.getDateOfBirth().get(2), patient
								.getDateOfBirth().get(1))));
				result = result.concat(String.format(LONGSTRINGPREFIX,
						patient.getDiagnosis()));
				result = result.concat(String.format(LONGSTRINGPREFIX,
						patient.getMedication()));
				result = result.concat(String.format(STRINGPREFIX,
						String.valueOf(patient.getPatientID())));
				result = result.concat(String.format(STRINGPREFIX,
						String.valueOf(patient.getDoctorID())));
				for (int j = 0; j < this.assignment.size(); j++) {
					Assignment assignment2 = this.assignment.get(j);
					if (patient.getPatientID() == assignment2.getPatientID()) {
						int personID = assignment2.getPositionID();
						result = result.concat(String.format(LONGSTRINGPREFIX,
								assignment2.getText()));
						if (assignment2.getPosition() == Positions.Doctor) {
							for (int k=0;k<this.doctors.size();k++){
								if (this.doctors.get(k).getDoctorID()==personID){
									personID=k;
								}
							}
							result = result.concat(String.format(STRINGPREFIX,
									this.doctors.get(personID).getSpecialty()));
							result = result.concat(String.format(STRINGPREFIX,
									String.valueOf(this.doctors.get(personID)
											.getDoctorID())));
						} else {
							for (int k=0;k<this.nurses.size();k++){
								if (this.nurses.get(k).getNurseID()==personID){
									personID=k;
								}
							}
							result = result.concat(String.format(STRINGPREFIX,
									"Nurse"));
							result = result.concat(String.format(STRINGPREFIX,
									String.valueOf(this.nurses.get(personID)
											.getNurseID())));
						}
					}
				}
				result = result.concat("\n");
			}
		} else {
			result = "Empty";
		}
		return result;
	}

	public synchronized void saveUsers(String fileName) throws IOException {
		HospitalWriter writer = new HospitalWriter();
		writer.saveUsers(users, fileName);
	}

	public synchronized void loadData(String fileName) throws IOException,
			ClassNotFoundException {
		HospitalReader reader = new HospitalReader();
		Hospital newHospital = reader.readData(fileName);
		this.patients = newHospital.getPatients();
		this.doctors = newHospital.getDoctors();
		this.nurses = newHospital.getNurses();
		this.assignment = newHospital.getAssignment();
	}

	public synchronized void addCsvData(String filename) throws IOException {
		ArrayList<Person> persons = new ArrayList<Person>();
		ZipReader reader = new ZipReader(filename);
		persons = reader.readData(Hospital.filePatientCsv);
		for (int i = 0; i < persons.size(); i++) {
			this.patients.add((Patient) persons.get(i));
		}
		reader.close();
		reader = new ZipReader(filename);
		persons = reader.readData(Hospital.fileDoctorCsv);
		for (int i = 0; i < persons.size(); i++) {
			this.doctors.add((Doctor) persons.get(i));
		}
		reader.close();
		reader = new ZipReader(filename);
		persons = reader.readData(Hospital.fileNursetCsv);
		for (int i = 0; i < persons.size(); i++) {
			this.nurses.add((Nurse) persons.get(i));
		}
		reader.close();
	}

	public synchronized boolean addUser(String login, String password) {
		User user = new User(login, password);
		for (int i=0;i<users.size();i++){
			if (users.get(i).getLogin().equals(login)){
				return false;
			}
		}
		this.users.add(user);
		return true;
	}

	public synchronized boolean removeUser(String login) throws IOException{
		boolean isRemove = false;
		for (int i = 0; i < users.size(); i++) {
			if (login.equals(users.get(i).getLogin())) {
				users.remove(i);
				isRemove = true;
				this.saveUsers(Hospital.fileUsers);
				break;
			}
		}
		return isRemove;
	}
	
	public synchronized void removeAllData() {
		this.patients = new ArrayList<Patient>();
		this.doctors = new ArrayList<Doctor>();
		this.nurses = new ArrayList<Nurse>();
		this.assignment = new ArrayList<Assignment>();
	}

	public synchronized void saveData(String filename) throws IOException {
		HospitalWriter writer = new HospitalWriter();
		writer.saveData(this, filename);
		saveUsers("Users.bin");
	}

	/**
	 * @return the patients
	 */
	public ArrayList<Patient> getPatients() {
		return patients;
	}

	/**
	 * @return the doctors
	 */
	public ArrayList<Doctor> getDoctors() {
		return doctors;
	}

	/**
	 * @return the nurses
	 */
	public ArrayList<Nurse> getNurses() {
		return nurses;
	}

	/**
	 * @param patients
	 *            the patients to set
	 */
	public void setPatients(ArrayList<Patient> patients) {
		this.patients = patients;
	}

	/**
	 * @param doctors
	 *            the doctors to set
	 */
	public void setDoctors(ArrayList<Doctor> doctors) {
		this.doctors = doctors;
	}

	/**
	 * @param nurses
	 *            the nurses to set
	 */
	public void setNurses(ArrayList<Nurse> nurses) {
		this.nurses = nurses;
	}

	/**
	 * @return the assignment
	 */
	public ArrayList<Assignment> getAssignment() {
		return assignment;
	}

	/**
	 * @param assignment
	 *            the assignment to set
	 */
	public void setAssignment(ArrayList<Assignment> assignment) {
		this.assignment = assignment;
	}

	/**
	 * @return the users
	 */
	public ArrayList<User> getUsers() {
		return users;
	}

}
