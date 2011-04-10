/**
 * The code is licensed under GPL v.3.
 */
package net.epam.java.vehicles;

import java.io.IOException;

/**
 * @author Alexander Nikoniuk
 *
 */
public class TestProgram {


	static void testAutomaticGearbox()
	{
		auto = new AutomaticGearboxAutomobile();
		
		try {
			auto.move();
			System.out.println(auto);
			auto.start();
			System.out.println(auto);
			System.out.println("increase speed");
			auto.increaseSpeed();
			System.out.println(auto);
			for (int i = 1; i <= 5; ++i) {
				System.out.println("continue moving");
				auto.move();
				System.out.println(auto);
			}
			for (int i = 1; i <= 20; ++i) {
				System.out.println("increase speed");
				auto.increaseSpeed();
				System.out.println(auto);
			}
			for (int i = 1; i <= 5; ++i) {
				System.out.println("continue moving");
				auto.move();
				System.out.println(auto);
			}
			System.out.println("braking");
			auto.brake();
			System.out.println(auto);
			
			for (int i = 1; i <= 100; ++i) {
				System.out.println("increase speed");
				auto.increaseSpeed();
				System.out.println(auto);
			}
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
			System.out.println(auto);
		}
	}
	
	static char manualGearBoxAction() throws IOException {
		System.out.println("Usage: s - start; p - stop; i - increase speed; d - decrease speed; b - brake;");
		System.out.println("c - continue moving; g - change gear; r - repair; f - refuel; q - quit");
		System.in.skip(100);
		System.out.println("Your choice: ");
		return (char)System.in.read();
	}
	
	static void testManualGearbox() throws IOException
	{
		// auto = new ManualGearboxAutomobile(); for testing AutomaticGearboxAutomobile in manual mode 
		auto = new ManualGearboxAutomobile();
		
		while (true) {
			try {
					
				char choice = manualGearBoxAction();
				switch (choice) {
				case 's':
					auto.start();
					break;
				case 'p':
					auto.stop();
					break;
				case 'i':
					auto.increaseSpeed();
					break;
				case 'd':
					auto.decreaseSpeed();
					break;
				case 'b':
					auto.brake();
					break;
				case 'c':
					auto.move();
					break;
				case 'g':
					int gear = 0;
					do {
						System.out.println("New gear: ");
						System.in.skip(100);
						gear = System.in.read() - '0';
					}
					while (gear < 0 && gear > 5);
					auto.changeGear(gear);
					break;
				case 'r':
					auto.repair();
					break;
				case 'f':
					auto.refuel();
					break;
				case 'q':
					return ;
				}
			} 
			catch (VehicleException e) {
				System.err.println(e.getMessage());
			}
			finally {
				System.out.print(auto);
			}
		}
	}
	
	static void showMainMenu() throws IOException 
	{
		System.out.println("1. Test automobile with automatic gearbox");
		System.out.println("2. Test automobile with manual gearbox");
		System.out.println("Your choice: ");
		char choice = (char)System.in.read();
		switch (choice) {
		case '1':
			testAutomaticGearbox();
			break;
		case '2':
			testManualGearbox();
			break;
		default:
			showMainMenu();
			break;
		}
	}
	
	public static void main(String[] args)  throws IOException {
		showMainMenu();
	}
	private static Automobile auto;

}
