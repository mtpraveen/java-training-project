/**
 * 
 */
package aircraft;

import java.io.Serializable;

/**
 * @author Mihail
 * 
 */
public abstract class Airplane implements Serializable {
	private static final long serialVersionUID = 1L;
	protected double distance;
	protected double fuelConsumption;
	protected int countMotor;
	protected double mass;
	protected double maxSpeed;
	protected String name;

	public Airplane(double distance, double fuelConsumption, int countMotor,
			double mass, double maxSpeed, String name) {
		super();
		this.distance = distance;
		this.fuelConsumption = fuelConsumption;
		this.countMotor = countMotor;
		this.mass = mass;
		this.maxSpeed = maxSpeed;
		this.name = name;
	}

	/**
	 * @return the distance
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * @param distance
	 *            the distance to set
	 */
	public void setDistance(double distance) {
		this.distance = distance;
	}

	/**
	 * @return the fuelConsumption
	 */
	public double getFuelConsumption() {
		return fuelConsumption;
	}

	/**
	 * @param fuelConsumption
	 *            the fuelConsumption to set
	 */
	public void setFuelConsumption(double fuelConsumption) {
		this.fuelConsumption = fuelConsumption;
	}

	/**
	 * @return the countMotor
	 */
	public int getCountMotor() {
		return countMotor;
	}

	/**
	 * @param countMotor
	 *            the countMotor to set
	 */
	public void setCountMotor(int countMotor) {
		this.countMotor = countMotor;
	}

	/**
	 * @return the mass
	 */
	public double getMass() {
		return mass;
	}

	/**
	 * @param mass
	 *            the mass to set
	 */
	public void setMass(double mass) {
		this.mass = mass;
	}

	/**
	 * @return the maxSpeed
	 */
	public double getMaxSpeed() {
		return maxSpeed;
	}

	/**
	 * @param maxSpeed
	 *            the maxSpeed to set
	 */
	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
		long temp;
		temp = Double.doubleToLongBits(countMotor);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(distance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(fuelConsumption);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(mass);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(maxSpeed);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Airplane other = (Airplane) obj;
		if (Double.doubleToLongBits(countMotor) != Double
				.doubleToLongBits(other.countMotor))
			return false;
		if (Double.doubleToLongBits(distance) != Double
				.doubleToLongBits(other.distance))
			return false;
		if (Double.doubleToLongBits(fuelConsumption) != Double
				.doubleToLongBits(other.fuelConsumption))
			return false;
		if (Double.doubleToLongBits(mass) != Double
				.doubleToLongBits(other.mass))
			return false;
		if (Double.doubleToLongBits(maxSpeed) != Double
				.doubleToLongBits(other.maxSpeed))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
