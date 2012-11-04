package com.epam.carPark;

import java.io.IOException;

import org.apache.log4j.PropertyConfigurator;

public class Runner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PropertyConfigurator.configure("log4j.properties");
		CarPark one = new CarPark();
		try {
			one.createPark();
		} catch (IOException e) {
			e.printStackTrace();
		}
		one.showPark();
		one.sortByConsumption();
		one.showPark();
		one.searchCar();
	}

}
