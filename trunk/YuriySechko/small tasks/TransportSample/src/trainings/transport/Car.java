/**
 * 
 */
package trainings.transport;

/**
 * @author epam0006
 */

/**
 * Simulates behavior of car
 */
public class Car implements ITransport {
	class Engine {
		public final int MAX_SPEED = 360;
		public final int MAX_RPM = 17000;
		public static final int INC_RPM = 200;
		
		boolean isPoweredOn = false;
		int rpm = 0;
 
		public void powerOn() {
			isPoweredOn = true;
			rpm = 100;
		}
		
		public void powerOff() {
			isPoweredOn = false;
			rpm = 0;
		}		
		
		public boolean increaseRpm(int rpm) {
			if ( (this.rpm + rpm) > MAX_RPM )
				return false;
			this.rpm += rpm;
			return true; 
		}
	}
	/**
	 * Maximum possible speed of car
	 */
	public final static int MAX_SPEED = 360;
	
	/**
	 * Speed increment
	 */
	public final static int INC_SPEED = 10;
	
	Engine engine = new Engine();
	GearBox gearBoxType;
	int speed = 0;
	double startTime = 0;
	long distance = 0;
	
	/**
	 * @param gearBoxType - specified gear box type 
	 */
	public Car(GearBox gearBoxType) {
		this.gearBoxType = gearBoxType;
	}
	
	@Override
	public boolean start() {
		engine.powerOn();
		return true;
	}

	@Override
	public boolean stop() {
		engine.powerOff();
		changeStartTime();
		distance();
		speed = 0;
		return true;
	}

	@Override
	public void brake() {
		if ( speed - INC_SPEED * 4 < 0) {
			speed = 0;
		}
		else {
			speed -= INC_SPEED * 4;
		}
		changeStartTime();
	}

	private void changeStartTime() {
		if ( speed == 0 ) {
			startTime = 0;
			distance();
		}
	}

	@Override
	public void increaseSpeed() {
		if ( this.speed + INC_SPEED * 2 > MAX_SPEED )
			return;

		if ( gearBoxType == GearBox.AUTO ) {
			this.speed += INC_SPEED;
			engine.increaseRpm(Engine.INC_RPM);
		} else {
			this.speed += INC_SPEED * 2;
			engine.increaseRpm(Engine.INC_RPM * 2);	
		}
		if ( startTime == 0 )
			startTime = System.currentTimeMillis();
	}

	@Override
	public void decreaseSpeed() {
		if ( speed - INC_SPEED < 0 ) {
			speed = 0;
		}
		else {
			speed -= INC_SPEED;
		}
		changeStartTime();
	}

	@Override
	public double distance() {
		if ( speed > 0 ) {
			double curTime = System.currentTimeMillis();
			distance += (double)speed * (curTime - startTime ) / 3600.0;
			startTime = curTime;
		}
	
		return ( distance );	
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	/**
	 * Returns current information about car
	 * @return a string in format "Car [gearBoxType=<i>gear box type</i> , speed=<i>current speed</i>, distance=<i>passed distance</i>]";
	 */
	@Override
	public String toString() {
		return "Car [gearBoxType=" + gearBoxType + ", speed=" + speed
				+ ", distance=" + String.format("%.20f", distance() )+ "]";
		//return "Car [gearBoxType=" + gearBoxType + ", speed=" + speed
		//+ ", distance=" + distance() + "]";

	}

	public static void main(String[] args) {

		try { 
			
		Car zaz = new Car( GearBox.MANUAL );
		
		zaz.start();
		System.out.println(zaz.toString());
		
		zaz.increaseSpeed();
		Thread.sleep(1000);
		System.out.println(zaz.toString());
		
		zaz.increaseSpeed();
		Thread.sleep(1000);
		System.out.println(zaz.toString());

		zaz.increaseSpeed();
		Thread.sleep(1000);
		System.out.println(zaz.toString());
		
		zaz.increaseSpeed();
		Thread.sleep(1000);
		System.out.println(zaz.toString());
		
		zaz.increaseSpeed();
		Thread.sleep(1000);
		Thread.sleep(1000);
		Thread.sleep(1000);
		
		System.out.println(zaz.toString());
		
		zaz.decreaseSpeed();
		Thread.sleep(1000);
		
		System.out.println(zaz.toString());
		
		zaz.brake();
		Thread.sleep(1000);

		zaz.stop();
		System.out.println(zaz.toString());
		
		zaz.start();
		System.out.println(zaz.toString());
		
		zaz.increaseSpeed();
		Thread.sleep(1000);
		
		zaz.stop();
		System.out.println(zaz.toString());

		
		}
		catch (Exception e){}
		finally {}
		
	}
	
	
}

