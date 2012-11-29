/**
 * 
 */
package position;

import java.util.Calendar;

/**
 * @author Mihail
 * 
 */
public class Patient extends Person {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5448920976883829256L;
	private Calendar dateOfReceipt;
	private String diagnosis;
	private String medication;
	private int patientID;
	private int doctorID;

	public Patient(String name, String surname, int age, Sex sex,
			Calendar dateOfBirth, Calendar dateOfReceipt, String diagnosis,
			String medication, int patientID, int doctorID) {
		super(name, surname, age, sex, dateOfBirth);
		this.dateOfReceipt = dateOfReceipt;
		this.diagnosis = diagnosis;
		this.medication = medication;
		this.patientID = patientID;
		this.doctorID = doctorID;
	}

	/**
	 * @return the doctorID
	 */
	public int getDoctorID() {
		return doctorID;
	}

	/**
	 * @param doctorID
	 *            the doctorID to set
	 */
	public void setDoctorID(int doctorID) {
		this.doctorID = doctorID;
	}

	/**
	 * @return the diagnosis
	 */
	public String getDiagnosis() {
		return diagnosis;
	}

	/**
	 * @return the dateOfReceipt
	 */

	/**
	 * @param diagnosis
	 *            the diagnosis to set
	 */
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	/**
	 * @return the dateOfReceipt
	 */
	public Calendar getDateOfReceipt() {
		return dateOfReceipt;
	}

	/**
	 * @param dateOfReceipt
	 *            the dateOfReceipt to set
	 */
	public void setDateOfReceipt(Calendar dateOfReceipt) {
		this.dateOfReceipt = dateOfReceipt;
	}

	/**
	 * @return the medication
	 */
	public String getMedication() {
		return medication;
	}

	/**
	 * @param medication
	 *            the medication to set
	 */
	public void setMedication(String medication) {
		this.medication = medication;
	}

	/**
	 * @return the patientID
	 */
	public int getPatientID() {
		return patientID;
	}

	/**
	 * @param patientID
	 *            the patientID to set
	 */
	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((dateOfReceipt == null) ? 0 : dateOfReceipt.hashCode());
		result = prime * result
				+ ((diagnosis == null) ? 0 : diagnosis.hashCode());
		result = prime * result + doctorID;
		result = prime * result
				+ ((medication == null) ? 0 : medication.hashCode());
		result = prime * result + patientID;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patient other = (Patient) obj;
		if (dateOfReceipt == null) {
			if (other.dateOfReceipt != null)
				return false;
		} else if (!dateOfReceipt.equals(other.dateOfReceipt))
			return false;
		if (diagnosis == null) {
			if (other.diagnosis != null)
				return false;
		} else if (!diagnosis.equals(other.diagnosis))
			return false;
		if (doctorID != other.doctorID)
			return false;
		if (medication == null) {
			if (other.medication != null)
				return false;
		} else if (!medication.equals(other.medication))
			return false;
		if (patientID != other.patientID)
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Patient [" + super.toString() + ", dateOfReceipt="
				+ dateOfReceipt.get(5) + "." + dateOfReceipt.get(2) + "."
				+ dateOfReceipt.get(1) + ", diagnosis=" + diagnosis
				+ ", medication=" + medication + ", patientID=" + patientID
				+ ", doctorID=" + doctorID + "]";
	}

}
