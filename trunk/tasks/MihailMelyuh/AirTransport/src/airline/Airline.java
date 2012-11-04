/**
 * 
 */
package airline;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import aircraft.Airliner;
import aircraft.Airplane;
import aircraft.Freighter;
import aircraft.SortByDistanceAirplane;

/**
 * @author Mihail
 * 
 */
public class Airline implements Serializable, Cloneable {

	/**
	 * First version
	 */
	private static final long serialVersionUID = 1L;

	private List<Airplane> plane;

	public Airline() {
		plane = new ArrayList<Airplane>();
	}

	public void sort() {
		Collections.sort(plane, new SortByDistanceAirplane());
	}

	public void addPlane(Airplane plane1) {
		plane.add(plane1);
	}

	public void removePlane(Airplane plane1) {
		plane.remove(plane1);
	}

	public double totalFreighterCapacity() {
		double result = 0;
		for (int i = 0; i < (this.plane.size()); i++) {
			if (plane.get(i) instanceof Freighter) {
				Freighter obj = (Freighter) plane.get(i);
				result += obj.getCarrying();
			}
		}
		return result;
	}

	public int totalNumberPassenger() {
		int result = 0;
		for (int i = 0; i < (this.plane.size()); i++) {
			if (plane.get(i) instanceof Airliner) {
				Airliner obj = (Airliner) plane.get(i);
				result += obj.getNumberPassenger();
			}
		}
		return result;
	}

	/**
	 * Declare zero or null if the criterion is not important
	 * 
	 * @return the List<Airplane>
	 */
	public List<Airplane> findPlanes(double distance, double fuelConsumption,
			int countMotor, double mass, double maxSpeed, String name,
			int numberPassenger, double carrying) {
		List<Airplane> planes = new ArrayList<Airplane>();
		for (int i = 0; i < this.plane.size(); i++) {
			planes.add(this.plane.get(i));
		}
		if (distance != 0) {
			for (int i = 0; i < planes.size(); i++) {
				if (planes.get(i).getDistance() != distance) {
					planes.remove(i);
					i--;
				}
			}
		}
		if (fuelConsumption != 0) {
			for (int i = 0; i < planes.size(); i++) {
				if (planes.get(i).getFuelConsumption() != fuelConsumption) {
					planes.remove(i);
					i--;
				}
			}
		}
		if (countMotor != 0) {
			for (int i = 0; i < planes.size(); i++) {
				if (planes.get(i).getCountMotor() != countMotor) {
					planes.remove(i);
					i--;
				}
			}
		}
		if (mass != 0) {
			for (int i = 0; i < planes.size(); i++) {
				if (planes.get(i).getMass() != mass) {
					planes.remove(i);
					i--;
				}
			}
		}
		if (maxSpeed != 0) {
			for (int i = 0; i < planes.size(); i++) {
				if (planes.get(i).getMaxSpeed() != maxSpeed) {
					planes.remove(i);
					i--;
				}
			}
		}
		if (name != null) {
			for (int i = 0; i < planes.size(); i++) {
				if (planes.get(i).getName() != name) {
					planes.remove(i);
					i--;
				}
			}
		}
		if (numberPassenger != 0) {
			for (int i = 0; i < planes.size(); i++) {
				if (planes.get(i) instanceof Airliner) {
					Airliner obj = (Airliner) planes.get(i);
					if (obj.getNumberPassenger() != numberPassenger) {
						planes.remove(i);
						i--;
					}
				}
			}
		}
		if (carrying != 0) {
			for (int i = 0; i < planes.size(); i++) {
				if (planes.get(i) instanceof Airliner) {
					Freighter obj = (Freighter) planes.get(i);
					if (obj.getCarrying() != carrying) {
						planes.remove(i);
						i--;
					}
				}
			}
		}
		return planes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Airline [plane: " + plane.toString() + "]";
	}

	public Airplane getPlane(int index) {
		if ((index > this.plane.size()) && (index < 0)) {
			throw new ArrayIndexOutOfBoundsException();
		}
		return this.plane.get(index);
	}

	public int getSize() {
		return this.plane.size();
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
		result = prime * result + ((plane == null) ? 0 : plane.hashCode());
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
		Airline other = (Airline) obj;
		if (plane == null) {
			if (other.plane != null)
				return false;
		} else if (!plane.equals(other.plane))
			return false;
		return true;
	}
}
