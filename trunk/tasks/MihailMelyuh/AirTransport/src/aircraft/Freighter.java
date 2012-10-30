/**
 * 
 */
package aircraft;

/**
 * @author Mihail
 * 
 */
public class Freighter extends Airplane {
	/**
	 * First version
	 */
	private static final long serialVersionUID = 1L;
	private double carrying;

	public Freighter(double carrying, double distance, double fuelConsumption,
			int countMotor, double mass, double maxSpeed, String name) {
		super(distance, fuelConsumption, countMotor, mass, maxSpeed, name);
		this.carrying = carrying;
	}

	/**
	 * @return the carrying
	 */
	public double getCarrying() {
		return carrying;
	}

	/**
	 * @param carrying
	 *            the carrying to set
	 */
	public void setCarrying(double carrying) {
		this.carrying = carrying;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Freighter [carrying=" + carrying + ", distance=" + distance
				+ ", fuelConsumption=" + fuelConsumption + ", countMotor="
				+ countMotor + ", mass=" + mass + ", maxSpeed=" + maxSpeed
				+ ", name=" + name + "]";
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
		long temp;
		temp = Double.doubleToLongBits(carrying);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Freighter other = (Freighter) obj;
		if (Double.doubleToLongBits(carrying) != Double
				.doubleToLongBits(other.carrying))
			return false;
		return true;
	}

}
