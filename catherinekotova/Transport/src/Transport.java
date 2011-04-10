

public class Transport {

}

class Bicycle extends Transport {
	int wheelNumber;

}

class Motorcycle extends Bicycle {
	int seatNumber;
}

class Automobile extends Transport {
	public static final int maxVelocity = 160;
	private Engine engine = new Engine();
	private String name;
	boolean automatictransmission = true;
	double gear;
	int gearNumber = 7;
	double gearSpeed;
	int speed=0;

	public Automobile(String name) {
		this.name = name;
	}

	public void transmission() {

	}

	public void start() {
		System.out.println("Automobile " + name + " has started");
	}

	public void brake() {
		System.out.println("Automobile " + name + " has been shut down");
	}

	public void stop() {
		System.out.println("Automobile " + name + " has stopped");
	}

	public void increasespeed() {
		for (int speed = 0; speed <= maxVelocity; speed += 20 ) {
			
			System.out.println("Automobile " + name
					+ " increase speed, new speed " + speed);
			engine.changegear(speed);
		}
		System.out.println("Automobile " + name + " maxVelocity " + maxVelocity);
	}

	public void decreasespeed() {
		for (int speed =maxVelocity; speed >= 0; speed-=20) {

		
			System.out.println("Automobile " + name + " decrease speed, new speed " + speed);
			engine.changegear(speed);
		}
	}

	public void distance() {

	}

	public class Engine {

		double gear;
		int gearNumber = 7;
		double gearSpeed;

		public void changegear(int speed) {
			gearSpeed = maxVelocity / 7;
			
			gear = Math.round(speed/gearSpeed);
			System.out.println("Automobile " + name + " gear " + gear);
		}
	}

	public static void main(String[] args) {
		Automobile first = new Automobile("1");

		first.start();

		first.increasespeed();
	
		first.decreasespeed();
		first.stop();
		first.brake();

	}
}
