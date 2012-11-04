package test.AirTransport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import aircraft.Airliner;
import aircraft.Airplane;
import aircraft.Freighter;
import airline.Airline;
import airline.AirlineReader;

public class TestAirTransport {

	Airline company = new Airline();
	Freighter obj1 = new Freighter(100, 400000, 50, 3, 570, 350, "First");
	Freighter obj2 = new Freighter(150.6, 460000, 64.3, 7, 600, 120, "Second");
	Airliner obj3 = new Airliner(58, 600000, 24.6, 4, 700, 240, "Tu-154");

	@Test
	public void testCreatingCompany() {
		// Create a new company
		company.addPlane(obj1);
		Assert.assertEquals(obj1, company.getPlane(0));
		company.addPlane(obj2);
		Assert.assertEquals(obj2, company.getPlane(1));
		company.addPlane(obj3);
		Assert.assertEquals(obj3, company.getPlane(2));
		company.removePlane(obj2);
		Assert.assertEquals(2, company.getSize());
	}

	@Test
	public void testGettingPLane() {
		company.addPlane(obj1);
		company.addPlane(obj2);
		company.addPlane(obj3);
		Assert.assertEquals(company.getPlane(0), obj1);
		try {
			company.getPlane(-1);
			Assert.fail("The exception is not caught!");
		} catch (ArrayIndexOutOfBoundsException e) {
		}
	}

	@Test
	public void testUsingSearch() {
		company.addPlane(obj1);
		company.addPlane(obj2);
		company.addPlane(obj3);
		List<Airplane> planes = new ArrayList<Airplane>();
		planes = company.findPlanes(460000, 0, 0, 0.0, 0.0, null, 0, 0.0);
		Assert.assertEquals(obj2, planes.get(0));
	}

	@Test
	public void testTotalsDate() {
		company.addPlane(obj1);
		company.addPlane(obj2);
		company.addPlane(obj3);
		Assert.assertEquals(250.6, company.totalFreighterCapacity());
		Assert.assertEquals(58, company.totalNumberPassenger());
	}

	@Test
	public void testSorting() {
		company.addPlane(obj1);
		company.addPlane(obj2);
		company.addPlane(obj3);
		company.sort();
		Assert.assertEquals(company.getPlane(0).getDistance(), 400000.0);
	}

	@Test
	public void testReader() {

		// Storing and extracting data
		company.addPlane(obj1);
		company.addPlane(obj2);
		company.addPlane(obj3);
		String fileName = "out.my";
		AirlineReader reader = new AirlineReader();
		try {
			reader.saveData(company, fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Airline companyNew = new Airline();
		try {
			companyNew = reader.readData(fileName);
			Assert.assertEquals(company, companyNew);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
