package zip;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipOutputStream;

import position.Doctor;
import position.Nurse;
import position.Patient;
import position.Person;

public class CsvWriter {
	private DataOutputStream stream = null;
	private static final String DATE = "%1$s.%2$s.%3$s";
	private static final String PREFIX = "\"%1$s\";";
	public CsvWriter(ZipOutputStream out, String filename) {
		this.stream = new DataOutputStream(out);
	}

	public void writeFile(ArrayList<? extends Person> persons)
			throws IOException {
		if (!persons.isEmpty()) {
			for (Person person : persons) {
				stream.write(String.format(PREFIX, person.getName()).getBytes());
				stream.write(String.format(PREFIX, person.getSurname())
						.getBytes());
				stream.write(String.format(PREFIX,
						String.valueOf(person.getAge())).getBytes());
				stream.write(String.format(PREFIX, person.getSex().name())
						.getBytes());
				stream.write(String.format(
						PREFIX,
						String.format(DATE, person.getDateOfBirth().get(5),
								person.getDateOfBirth().get(2), person
										.getDateOfBirth().get(1))).getBytes());
				if (person instanceof Patient) {
					Patient patient = (Patient) person;
					stream.write(String.format(
							PREFIX,
							String.format(DATE, person.getDateOfBirth().get(5),
									person.getDateOfBirth().get(2), person
											.getDateOfBirth().get(1)))
							.getBytes());
					stream.write(String.format(PREFIX, patient.getDiagnosis())
							.getBytes());
					stream.write(String.format(PREFIX, patient.getMedication())
							.getBytes());
					stream.write(String.format(PREFIX,
							String.valueOf(patient.getPatientID())).getBytes());
					stream.write(String.format(PREFIX,
							String.valueOf(patient.getDoctorID())).getBytes());
				}
				if (person instanceof Doctor) {
					Doctor doctor = (Doctor) person;
					stream.write(String.format(PREFIX, doctor.getSpecialty())
							.getBytes());
					stream.write(String.format(PREFIX,
							String.valueOf(doctor.getDoctorID())).getBytes());
				}
				if (person instanceof Nurse) {
					Nurse nurse = (Nurse) person;
					stream.write(String.format(PREFIX,
							String.valueOf(nurse.getNurseID())).getBytes());
				}
				stream.write('\n');
			}
		}
	}

}
