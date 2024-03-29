/**
 * 
 */
package aircraft;

/**
 * @author Mihail
 * 
 */
public class Airliner extends Airplane {
	/**
	 * First version
	 */
	private static final long serialVersionUID = 1L;
	private int numberPassenger;

	public Airliner(int numberPassenger, double distance,
			double fuelConsumption, int countMotor, double mass,
			double maxSpeed, String name) {
		super(distance, fuelConsumption, countMotor, mass, maxSpeed, name);
		this.numberPassenger = numberPassenger;
	}

	/**
	 * @return the numberPassanger
	 */
	public int getNumberPassenger() {
		return numberPassenger;
	}

	/**
	 * @param numberPassenger the numberPassanger to set
	 */
	public void setNumberPassenger(int numberPassenger) {
		this.numberPassenger = numberPassenger;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Airliner [numberPassenger=" + numberPassenger + ", distance="
				+ distance + ", fuelConsumption=" + fuelConsumption
				+ ", countMotor=" + countMotor + ", mass=" + mass
				+ ", maxSpeed=" + maxSpeed + ", name=" + name + "]";
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + numberPassenger;
		return result;
	}

	/* (non-Javadoc)
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
		Airliner other = (Airliner) obj;
		if (numberPassenger != other.numberPassenger)
			return false;
		return true;
	}

}
