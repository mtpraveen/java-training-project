public class Vehicle extends Moto {
	private String typeBox;
	private int number;

	public Vehicle(String type, double maxSpeed, double volumeEngine,
			String typeBox) {
		super(type, maxSpeed, volumeEngine);
		this.typeBox = typeBox;

	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getTypeBox() {
		return typeBox;
	}

	public void setTypeBox(String typeBox) {
		this.typeBox = typeBox;
	}

	public class Engine {
		public void controlSpeed() {
			double speed = getSpeed();
			if (speed >= 0 && speed <= 20)
				setNumber(1);
			if (speed >= 20 && speed <= 30)
				setNumber(2);
			if (speed >= 30 && speed <= 50)
				setNumber(3);
			if (speed >= 50 && speed <= 70)
				setNumber(4);
			if (speed >= 70)
				setNumber(5);
		}
	}

}
