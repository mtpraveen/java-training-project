package hospital;

import java.io.Serializable;

import position.Positions;

public class Assignment implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 866504692614278528L;
	private int patientID;
	private String text;
	private Positions position;
	private int positionID;

	public Assignment(int patientID, String text, Positions position,
			int positionID) {
		super();
		this.patientID = patientID;
		this.text = text;
		this.position = position;
		this.positionID = positionID;
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

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the position
	 */
	public Positions getPosition() {
		return position;
	}

	/**
	 * @param position
	 *            the position to set
	 */
	public void setPosition(Positions position) {
		this.position = position;
	}

	/**
	 * @return the positionID
	 */
	public int getPositionID() {
		return positionID;
	}

	/**
	 * @param positionID
	 *            the positionID to set
	 */
	public void setPositionID(int positionID) {
		this.positionID = positionID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + patientID;
		result = prime * result
				+ ((position == null) ? 0 : position.hashCode());
		result = prime * result + positionID;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
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
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Assignment other = (Assignment) obj;
		if (patientID != other.patientID)
			return false;
		if (position != other.position)
			return false;
		if (positionID != other.positionID)
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

}
