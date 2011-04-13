public class Velo {
	private String type;
	private double maxSpeed;
	private double speed;
	private double distance;

	public Velo(String type, double maxSpeed) {
		this.type = type;
		this.maxSpeed = maxSpeed;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void increaseSpeed(double a) {
		speed = getSpeed() + a;
		setSpeed(speed);
	}

	public void decreaseSpeed(double a) {
		speed = getSpeed() - a;
		setSpeed(speed);
	}

	public void distance(double t) {
		distance = getDistance() + getSpeed() * t;
	}

	public int brake() {
		if ((getSpeed() >= getMaxSpeed()) || (getSpeed() <= 0))
			return 1;
		else
			return 0;
	}

	public void start() {
		setSpeed(0);
		setDistance(0);
		// setNumber(1);
	}
}
