/**
 * @author Ash_Ketchum
 * 
 */
public class Transport {

	/**
	 * @param args
	 */

	public static int gear, currentgear, speed, currentspeed, distance = 0;

	public static int increaseSpeed(int speed) {
		speed++;
		currentspeed = speed;
		return currentspeed;

	}

	public static int decreaseSpeed(int speed) {
		speed--;
		currentspeed = speed;
		return currentspeed;
	}

	public static int Gear(int gear) {
		if (currentspeed <= 20) {
			gear = 1;
			currentgear = gear;
		}
		if ((currentspeed > 20) & (currentspeed <= 40)) {
			gear = 2;
			currentgear = gear;
		}
		if ((currentspeed > 40) & (currentspeed <= 60)) {
			gear = 3;
			currentgear = gear;
		}
		if ((currentspeed > 60) & (currentspeed <= 80)) {
			gear = 4;
			currentgear = gear;
		}
		if ((currentspeed > 80) & (currentspeed <= 120)) {
			gear = 5;
			currentgear = gear;

		}
		return currentgear;

	}

	public static boolean isStarted(boolean start) {
		if (start == true) {
			int c;
			for (c = 0; c < 135; c++) {
				increaseSpeed(currentspeed);
				Gear(currentgear);
				System.out.println("speed " + currentspeed + " gear "
						+ currentgear);
			}
		}
		System.out.println("Ќабрана максимальна€ скорость, начинаем торможение");
		return start;

	}

	public static boolean isStoped(boolean stop) {

		currentspeed = 135;
		currentgear = 5;
		if (stop == true) {
			int c;

			for (c = 0; c < 135; c++) {
				decreaseSpeed(currentspeed);
				Gear(currentgear);
				System.out.println("speed " + currentspeed + " gear "
						+ currentgear);
			}
		}
		System.out.println("ѕоходу остановились:)");
		return stop;
	}

}
