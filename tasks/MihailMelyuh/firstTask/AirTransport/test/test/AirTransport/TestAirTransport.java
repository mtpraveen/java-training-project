package test.AirTransport;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import aircraft.Airplane;
import aircraft.Cargo;
import aircraft.Passenger;
import airline.Airline;
import airline.IOAirline;

public class TestAirTransport {

	@Test
	public void testAirline() {
		//Create a new company
		Airline company = new Airline();
		company.addPlane(new Cargo(100, 400000, 50, 3, 570, 350, "First"));
		company.addPlane(new Cargo(150.6, 460000, 64.3, 7, 600, 120, "Second"));
		company.addPlane(new Passenger(58, 600000, 24.6, 4, 700, 240, "Tu-154"));
		
		//Basic operations
		System.out.println("Capacity: "+company.totalCapacity());
		System.out.println("Number of passengers: "+company.totalNumberPassenger());
		company.sort();
		company.show();
		company.findPassenger(100, 400000, 50, 3, 570, 350, "First");
		company.findCargo(150.6, 460000, 64.3, 7, 600, 120, "Second");
		company.findDistance(1);
		company.findNumberPassenger(5);
		company.findCarrying(10);
		company.find(new Passenger(58, 600000, 24.6, 4, 700, 240, "Tu-154"));
		company.find(new Cargo(150.6, 460000, 64.3, 7, 600, 120, "Second"));
		company.removePlane(new Cargo(150.6, 460000, 64.3, 7, 600, 120, "Second"));	
		company.show();
		
		//Storing and extracting data
		String fileName="out.my";
		company.saveToFile(fileName);
		Airline companyNew = new Airline();	
		try {
			companyNew=IOAirline.readData(fileName);
			companyNew.show();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
