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

	@Test
	public void testAirline() {
		//Create a new company
		Airline company = new Airline();
		Freighter obj1=new Freighter(100, 400000, 50, 3, 570, 350, "First");
		Freighter obj2=new Freighter(150.6, 460000, 64.3, 7, 600, 120, "Second");
		Airliner obj3 = new Airliner(58, 600000, 24.6, 4, 700, 240, "Tu-154");
		company.addPlane(obj1);
		Assert.assertEquals(obj1, company.getPlane(0));
		company.addPlane(obj2);
		Assert.assertEquals(obj2, company.getPlane(1));
		company.addPlane(obj3);
		Assert.assertEquals(obj3, company.getPlane(2));
		
		List<Airplane> planes=new ArrayList<Airplane>();
		planes=company.findPlanes(460000, 0, 0, 0.0, 0.0, null, 0, 0.0);
		Assert.assertEquals(obj2, planes.get(0));
		Assert.assertEquals(250.6, company.totalCapacity());
		Assert.assertEquals(58, company.totalNumberPassenger());
		company.sort();
		company.removePlane(new Freighter(150.6, 460000, 64.3, 7, 600, 120, "Second"));	
		Assert.assertEquals(2, company.getSize());
		
		//Storing and extracting data
		String fileName="out.my";
		AirlineReader reader = new AirlineReader();
		try {
			reader.saveData(company, fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Airline companyNew = new Airline();	
		try {
			companyNew=reader.readData(fileName);
			System.out.println(company.toString());
			System.out.println(companyNew.toString());
			//Assert.assertEquals(company,companyNew);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
