package test;
import static junit.framework.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.BeforeClass;
import org.junit.Test;

import transports.TransportSystem;

public class TransportSystemTest {
	private static String dir_path;

	@BeforeClass
	public static void setUp() {
		Properties props = new Properties();
		try {
			props.load(new FileInputStream("message.properties"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		dir_path = props.getProperty("path");
	}
	@Test
	public void readWayTest() {    // testing reading from file
		String path = dir_path + "test\\";
		TransportSystem trS = new TransportSystem();
		trS.addWays(path);
		assertEquals(trS.ways.get(0).getIdWays(), 1);
		assertEquals(trS.ways.get(0).getStopNumber(), 2);
		assertEquals(trS.ways.get(0).getTransNumber(), 2);
		assertEquals(trS.ways.get(0).getStopTime(), 6);
		assertEquals(trS.ways.get(0).getWayNumber(), 1);
		assertEquals(trS.ways.get(0).getTypeTrans(), 0);
		assertEquals(trS.ways.get(0).getFirstStop(), "Gershoni");
		assertEquals(trS.ways.get(0).getLastStop(), "CUM");
	}
	
	@Test
	public void verificationTest2() {  //testing makeAssignment without StopTime change necessity
		String path = dir_path + "test2\\";
		TransportSystem trS = new TransportSystem();
		trS.addWays(path);
		int time=trS.ways.get(0).getStopTime();
		trS.addTrans(path);
		trS.addBreakage(path);
		trS.verification();
		trS.makeAssignment(path);
		assertEquals(trS.ways.get(0).getStopTime(), time);
		System.out.println(time);
	}
	
	@Test
	public void verificationTest() {  //testing makeAssignment with StopTime change necessity
		String path = dir_path + "test\\";
		TransportSystem trS = new TransportSystem();
		trS.addWays(path);
		trS.addTrans(path);
		trS.addBreakage(path);
		trS.verification();
		trS.makeAssignment(path);
		assertEquals(trS.ways.get(0).getStopTime(), 12);
	}
	
	
	
}
