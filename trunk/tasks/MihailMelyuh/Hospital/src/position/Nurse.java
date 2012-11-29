/**
 * 
 */
package position;

import java.util.Calendar;

/**
 * @author Mihail
 * 
 */
public class Nurse extends Person {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int nurseID;

	public Nurse(String name, String surname, int age, Sex sex,
			Calendar dateOfBirth, int nurseID) {
		super(name, surname, age, sex, dateOfBirth);
		this.nurseID = nurseID;
	}

	/**
	 * @return the nurseID
	 */
	public int getNurseID() {
		return nurseID;
	}

	/**
	 * @param nurseID
	 *            the nurseID to set
	 */
	public void setNurseID(int nurseID) {
		this.nurseID = nurseID;
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
		result = prime * result + nurseID;
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
		Nurse other = (Nurse) obj;
		if (nurseID != other.nurseID)
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
		return "Nurse [" + super.toString() + ", nurseID=" + nurseID + "]";
	}

}
