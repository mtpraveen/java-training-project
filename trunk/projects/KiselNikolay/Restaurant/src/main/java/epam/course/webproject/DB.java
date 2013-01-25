package epam.course.webproject;

import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Date;
import java.util.Enumeration;

import org.joda.time.DateTime;

public class DB {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Enumeration<Driver> driverList=DriverManager.getDrivers();
		System.out.println("lists");
		
		while (driverList.hasMoreElements()) {
			Driver driverClass=driverList.nextElement();
			System.out.println(" "+driverClass.getClass());
		}
		
		System.out.println(new DateTime());
		System.out.println(new Date());
	}

}
