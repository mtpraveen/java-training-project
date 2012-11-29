package position;

import java.util.Calendar;

public class Doctor extends Person {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String specialty;
	private int doctorID;

	public Doctor(String name, String surname, int age, Sex sex,
			Calendar dateOfBirth, String specialty, int doctorID) {
		super(name, surname, age, sex, dateOfBirth);
		this.specialty = specialty;
		this.doctorID = doctorID;
	}

	/**
	 * @return the specialty
	 */
	public String getSpecialty() {
		return specialty;
	}

	/**
	 * @param specialty
	 *            the specialty to set
	 */
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + doctorID;
		result = prime * result
				+ ((specialty == null) ? 0 : specialty.hashCode());
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
		Doctor other = (Doctor) obj;
		if (doctorID != other.doctorID)
			return false;
		if (specialty == null) {
			if (other.specialty != null)
				return false;
		} else if (!specialty.equals(other.specialty))
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
		return "Doctor [" + super.toString() + ", specialty=" + specialty
				+ ", doctorID=" + doctorID + "]";
	}

}
