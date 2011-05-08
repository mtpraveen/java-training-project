package test;
import static junit.framework.Assert.assertEquals;
import org.junit.Test;
import transports.TransportSystem;

public class TransportSystemTest {
	@Test
	public void readWayTest() {    // testing reading from file
		String path = "d:\\Politech\\hobby\\__JAVA\\workspace\\TransportSystem\\data\\test\\";
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
		String path = "d:\\Politech\\hobby\\__JAVA\\workspace\\TransportSystem\\data\\test2\\";
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
		String path = "d:\\Politech\\hobby\\__JAVA\\workspace\\TransportSystem\\data\\test\\";
		TransportSystem trS = new TransportSystem();
		trS.addWays(path);
		trS.addTrans(path);
		trS.addBreakage(path);
		trS.verification();
		trS.makeAssignment(path);
		assertEquals(trS.ways.get(0).getStopTime(), 12);
	}
	
	
	
}
