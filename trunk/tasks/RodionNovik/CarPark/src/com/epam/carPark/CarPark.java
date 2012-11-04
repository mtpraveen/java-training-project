package com.epam.carPark;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.epam.cars.AllRoad;
import com.epam.cars.Car;
import com.epam.cars.Mini;
import com.epam.cars.Van;

/**
 * @author epam0010
 * 
 */
public class CarPark {
	private int totalCost;
	private int carAmount;
	private Car carList[];

	private static final Logger LOGGER = Logger.getLogger(Runner.class);
	
	/**
	 * creates park
	 */
	public void createPark() throws IOException {
		System.out.print("Enter a number of cars ");
		Scanner scan = new Scanner(System.in);
		int n = 0;
		n = scan.nextInt();
		carList = new Car[n];
		carAmount = n;
		initialize();
	}

	/**
	 * initializes park
	 */
	protected void initialize() {
		Random generator = new Random();
		int temp;
		for (int i = 0; i < carList.length; i++) {
			temp = (1 + generator.nextInt(3));
			switch (temp) {
			case 1:
				carList[i] = new AllRoad();
				break;
			case 2:
				carList[i] = new Mini();
				break;
			case 3:
				carList[i] = new Van();
				break;
			}
			carList[i].setName(("Car " + Integer.toString(i)));
			carList[i].setCost(Math.abs(generator.nextInt() % 10000));
			carList[i].setConsumption((2 + generator.nextInt(20) + (float) Math
					.rint(generator.nextDouble() * 10) / 10));
		}
		calculateTotalCost();
	}

	/**
	 * calculates total cost of cars
	 */
	protected void calculateTotalCost() {
		for (int i = 0; i < carList.length; i++) {
			totalCost += carList[i].getCost();
		}
	}

	/**
	 * displays car list, number of cars and total cost
	 */
	public void showPark() {
		for (int i = 0; i < carList.length; i++) {
			System.out.printf("%s  %f  %d  %s\n", carList[i].getName(),
					carList[i].getConsumption(), carList[i].getCost(),
					carList[i].getType());
		}
		System.out.printf("\nNumber of cars: %d\nTotal cost: %d\n", carAmount,
				totalCost);
	}

	/**
	 * sorts cars in order of increasing fuel consumption
	 */
	public void sortByConsumption() {
		Car temp;
		for (int j = 1; j < carList.length; j++) {
			for (int i = 0; i < (carList.length - j); i++) {
				if (carList[i].compareTo(carList[i + 1])>-1) {
					temp = carList[i + 1];
					carList[i + 1] = carList[i];
					carList[i] = temp;
				}
			}
		}
	}

	/**
	 * searchings car by characteristics(type, fuel consumption, cost)
	 */
	public void searchCar() {
		String type;
		float consumption;
		int cost, numberOfCars;
		try {
			Scanner scaner = new Scanner(System.in);
			type = "";
			consumption = 0;
			cost = 0;
			numberOfCars = 0;
			System.out.print("Enter the type (Van,Mini,AllRoad): ");
			type = scaner.nextLine();
			System.out.print("Enter the fuel consumption: ");
			consumption = scaner.nextFloat();
			System.out.print("Enter the cost: ");
			cost = scaner.nextInt();
			scaner.close();
		} catch (InputMismatchException e) {
			LOGGER.error(getClass(),e);
			System.out.println("Error " + e.getMessage());
			System.out.println("Function was interrupted");
			e.printStackTrace();
			return;
		}

		for (int i = 0; i < carList.length; i++) {
			if (type.compareTo(carList[i].getType()) == 0
					&& Math.abs((consumption - carList[i].getConsumption())) <= 0.1
					&& carList[i].getCost() == cost) {
				System.out.printf("Car found: %s", carList[i].getName());
				numberOfCars++;
			}
		}
		if (numberOfCars == 0)
			System.out.print("No cars with these characteristics\n");
	}

}
