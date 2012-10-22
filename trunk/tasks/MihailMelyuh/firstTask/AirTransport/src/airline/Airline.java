/**
 * 
 */
package airline;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import aircraft.Airplane;
import aircraft.Cargo;
import aircraft.Passenger;
import aircraft.SortedByDistance;

/**
 * @author Mihail
 * 
 */
public class Airline implements Serializable {

	private List<Airplane> plane;

	public Airline() {
		plane = new ArrayList<Airplane>();
	}

	public void sort() {
		Collections.sort(plane, new SortedByDistance());
	}

	public void addPlane(Airplane plane1) {
		plane.add(plane1);
	}

	public void removePlane(Airplane plane1) {
		plane.remove(plane1);
	}

	public void show() {
		System.out.println("Company: \n" + plane);
	}

	public double totalCapacity() {
		double result = 0;
		for (int i = 0; i < (this.plane.size()); i++) {
			if (plane.get(i).getClass() == Cargo.class) {
				Cargo obj = (Cargo) plane.get(i);
				result += obj.getCarrying();
			}
		}
		return result;
	}

	public int totalNumberPassenger() {
		int result = 0;
		for (int i = 0; i < (this.plane.size()); i++) {
			if (plane.get(i).getClass() == Passenger.class) {
				Passenger obj = (Passenger) plane.get(i);
				result += obj.getNumberPassenger();
			}
		}
		return result;
	}

	public void findPassenger(int numberPassenger, double distance,
			double fuelConsumption, int countMotor, double mass,
			double maxSpeed, String name) {
		this.find(new Passenger(numberPassenger, distance, fuelConsumption,
				countMotor, mass, maxSpeed, name));

	}

	public void findCargo(double carrying, double distance,
			double fuelConsumption, int countMotor, double mass,
			double maxSpeed, String name) {
		this.find(new Cargo(carrying, distance, fuelConsumption, countMotor,
				mass, maxSpeed, name));

	}

	public void find(Passenger passenger) {
		boolean finded = false;
		for (int i = 0; i < (this.plane.size()); i++) {
			if (plane.get(i).getClass() == Passenger.class) {
				if (passenger.equals(plane.get(i))) {
					System.out.println(plane.get(i).toString());
					finded = true;
				}
			}
		}
		if (!finded) {
			System.out.println("Not found.");
		}

	}

	public void find(Cargo cargo) {
		boolean finded = false;
		for (int i = 0; i < (this.plane.size()); i++) {
			if (plane.get(i).getClass() == Cargo.class) {
				if (cargo.equals(plane.get(i))) {
					System.out.println(plane.get(i).toString());
					finded = true;
				}
			}
		}
		if (!finded) {
			System.out.println("Not found.");
		}

	}

	public void findDistance(double distance) {
		boolean finded = false;
		for (int i = 0; i < (this.plane.size()); i++) {
			if (plane.get(i).getDistance() == distance) {
				System.out.println(plane.get(i).toString());
				finded = true;
			}
		}
		if (!finded) {
			System.out.println("Not found.");
		}
	}

	public void findNumberPassenger(int NumberPassenger) {

		boolean finded = false;
		for (int i = 0; i < (this.plane.size()); i++) {
			if (plane.get(i).getClass() == Passenger.class) {
				Passenger obj = (Passenger) plane.get(i);
				if (obj.getNumberPassenger() == NumberPassenger) {
					System.out.println(plane.get(i).toString());
					finded = true;
				}
			}
		}
		if (!finded) {
			System.out.println("Not found.");
		}

	}

	public void findCarrying(double carrying) {
		boolean finded = false;
		for (int i = 0; i < (this.plane.size()); i++) {
			if (plane.get(i).getClass() == Cargo.class) {
				Cargo obj = (Cargo) plane.get(i);
				if (obj.getCarrying() == carrying) {
					System.out.println(plane.get(i).toString());
					finded = true;
				}
			}
		}
		if (!finded) {
			System.out.println("Not found.");
		}

	}

	public void saveToFile(String fileName) {
		try {
			IOAirline.saveData(this, fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
