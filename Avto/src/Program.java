/**
 * 
 */
import java.io.*;
import java.util.*;

/**
 * @author ANN
 * 
 */

public class Program {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		int a, b = 0, b1 = 0;
		double t = 1, dt = 10;
		int flag = 0;
		System.out.println("Type of vechicle (1-Velo, 2-Moto,3-Auto):");
		Scanner input = new Scanner(System.in);
		b1 = input.nextInt();
		Velo vel = new Velo("VELO", 40);
		Moto moto = new Moto("MOTO", 120, 1.3);
		if (b1 == 1) {

			System.out.printf("\n Type: %s	Speed: %f", vel.getType(),
					vel.getMaxSpeed());
			vel.start();
			dt = 1;
			while (flag != 1) {
				System.out.println("Go or Stop (3-stop, any key-go):");
				Scanner input2 = new Scanner(System.in);
				b = input2.nextInt();
				if (b == 3)
					vel.decreaseSpeed(5);
				else
					vel.increaseSpeed(5);

				vel.distance(t);
				flag = vel.brake();
				System.out.printf("\n Speed: %f, Distance: %f, Time: %f",
						vel.getSpeed(), vel.getDistance(), t);
				System.out.flush();
				t = t + dt;
			}
		}
		if (b1 == 2) {
			System.out.printf("\n Type: %s	Speed: %f Volume: %f",
					moto.getType(), moto.getMaxSpeed(), moto.getVolumeEngine());
			vel.start();
			dt = 1;
			while (flag != 1) {
				System.out.println("Go or Stop (3-stop, any key-go):");
				Scanner input2 = new Scanner(System.in);
				b = input2.nextInt();
				if (b == 3)
					moto.decreaseSpeed(10);
				else
					moto.increaseSpeed(10);

				moto.distance(t);
				flag = moto.brake();
				System.out.printf("\n Speed: %f, Distance: %f, Time: %f",
						moto.getSpeed(), moto.getDistance(), t);
				System.out.flush();
				t = t + dt;
			}

		} else if (b1 == 3) {
			System.out.println("Type of auto (1-Mechanics, 2-Automatic):");
			a = System.in.read();
			Vehicle veh1=null;
			Vehicle.Engine eng1=null;
			if (a==49)
			{
			veh1 = new Vehicle("AUTO", 200, 2.5, "Mechanics");
			eng1=veh1.new Engine();
			}
			else
			{
			veh1=new Vehicle("AUTO",280, 4.0,"Automatic");
			eng1=veh1.new Engine();
			}
			System.out.printf("\n Type: %s	MaxSpeed: %f Volume: %f",
					veh1.getType(), veh1.getMaxSpeed(), veh1.getVolumeEngine(),
					veh1.getTypeBox());

			veh1.start();
			while (flag != 1) {
				System.out.println("Go or Stop (3-stop, any key-go):");
				Scanner input2 = new Scanner(System.in);
				b = input2.nextInt();
				if (b == 3)
					veh1.decreaseSpeed(20);
				else
					veh1.increaseSpeed(20);

				veh1.distance(t);
				if (a == 49)
					eng1.controlSpeed();
					flag = veh1.brake();
				System.out.printf("\n Speed: %f, Distance: %f, Number: %d, Time: %f", veh1.getSpeed(),veh1.getDistance(),veh1.getNumber(),t);
				System.out.flush();	
				t = t + dt;
			}
		}
	}
}
