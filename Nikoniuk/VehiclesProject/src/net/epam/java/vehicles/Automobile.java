/**
 * The code is licensed under GPL v.3.
 */
package net.epam.java.vehicles;

import java.util.Arrays;

/**
 * <p>The class <code>Automobile</code> can be used for emulating automobile movement: </p>
 * @author Alexander Nikoniuk
 * @see    net.epam.java.vehicles.IMotorVehicle
 *
 */
public abstract class Automobile implements IMotorVehicle {
	
	enum EngineDamage {
		LOW, MEDIUM, HIGH	
	}

	/**
	 * <p>The class <code>Automobile</code> implements automobile engine's methods: </p>
	 * @author Alexander Nikoniuk
	 * @see    net.epam.java.vehicles.IEngine
	 *
	 */
	protected class Engine implements IEngine {
		private int [] gearTopSpeed = new int[]{200, 20, 40, 60, 90, 200}; //gearTopSpeed[0] == 200 - top speed for neutral(0) gear
		public final int MAX_SPEED = gearTopSpeed[gearTopSpeed.length - 1];
		final int SPEED_DECREACEMENT = 10;
		final int SPEED_INCREACEMENT = 5;
		
		
	    /**
	     * Perform fuel consumption
		 * @throws EmptyTankException
		 */
		@Override
		public void consumeFuel() throws EmptyTankException {
			if (fuel == 0) 
			{
				throw new EmptyTankException("Empty fuel tank: need to refuel");
			}
			
			double fuelConsumed = consumption();
			if (fuel - fuelConsumed > 0) {
				fuel -= fuelConsumed;
			}
			else {
				fuel = 0;
			}
			
			
		}

	    /**
	     * Changes engine's gear
	     * @param newGear
	     * 			new car's gear
		 * @throws EmptyTankException
		 */
		@Override
		public void changeGear(int newGear) throws EngineOutOfOrderException, GearBoxException { 
			if (!working || newGear == 0 || (gear == 0 && newGear == 1)) {
				gear = newGear;
				return ;
			}
			
			int actualGear = gearFromSpeed(speed);
			if (newGear > actualGear) {
				int allovedChangeSpeed = gearTopSpeed[newGear - 1] - 5;
				switch (newGear - actualGear) {
				case 0:
					gear = actualGear;
					break;
				case 1:
					gear = newGear;	
					if (speed < allovedChangeSpeed) {
						addDamage(EngineDamage.LOW);
						throw new GearBoxException("GearBox exception(LOW engine damage) occured when changing gear. Your speed had to be" + allovedChangeSpeed + " km/h at least.");
					}
					break;
				case 2:
					addDamage(EngineDamage.MEDIUM);
					gear = newGear;	
					stop();
					throw new GearBoxException("GearBox exception(MEDIUM engine damage) occured when changing gear. The car was stopped(not enough engine rotations)");
				case 3:
				case 4:
				case 5:
					addDamage(EngineDamage.HIGH);
					gear = newGear;	
					stop();
					throw new GearBoxException("GearBox exception(HIGH engine damage) occured when changing gear. The car was stopped(not enough engine rotations)");
				default:
					throw new IllegalArgumentException("gear not supported");
				}
							
			} else if (newGear < actualGear) {
				int allovedChangeSpeed = gearTopSpeed[newGear] + 5;
				switch (actualGear - newGear) {
				case 1:
					gear = newGear;	
					if (speed > allovedChangeSpeed) {
						addDamage(EngineDamage.LOW);
						throw new GearBoxException("GearBox exception(LOW engine damage) occured when changing gear.");
					}
					break;
				case 2:
					addDamage(EngineDamage.MEDIUM);
					gear = newGear;	
					throw new GearBoxException("GearBox exception(MEDIUM engine damage) occured when changing gear.");
				case 3:
				case 4:
				case 5:
					addDamage(EngineDamage.HIGH);
					gear = newGear;	
					throw new GearBoxException("GearBox exception(HIGH engine damage) occured when changing gear.");
				default:
					throw new IllegalArgumentException("gear not supported");
				}
			}
		}
		
	    /**
	     * Repairs car's engine
		 */
		@Override
		public void repair() {
			damage = 0;
		}

	    /**
	     * Starts car's engine
		 * @throws EngineOutOfOrderException, GearBoxException
		 */
		@Override
		public void start() throws EngineOutOfOrderException, GearBoxException {
			
			if (speed == 0 && gear > 1) {
				throw new GearBoxException("GearBox exception: can start only from the 0 and 1 gear");
			}
			
			if (damage > 1) {
				throw new EngineOutOfOrderException("Engine out of order: repair engine to start your car");	
			}
			else {
				working = true;
			}
		}

	    /**
	     * Stops car's engine
		 */
		@Override
		public void stop() {
			working = false;
		}

	    /**
	     * Increases car's engine speed
		 */
		@Override
		public void increaseSpeed()  {
			if (!working || gear == 0) {
				return;
			}
			final int MAX_SPEED = gearTopSpeed[gear];
			int newSpeed = speed + SPEED_INCREACEMENT;
			if (newSpeed < MAX_SPEED) {
				speed = newSpeed;
			} else {
				speed = MAX_SPEED; 
			}
			
		}
	
	    /**
	     * Decreases car's engine speed
		 */
		@Override
		public void decreaseSpeed() {
			if (!working || gear == 0) {
				return;
			}
			int newSpeed = speed - SPEED_DECREACEMENT;
			if (newSpeed > 0) {
				speed = newSpeed;	
			} else {
				speed = 0;
			}
		}
		
	    /**
	     * Getter for car's engine speed
		 */
		@Override
		public int getSpeed() {
			return speed;
		}
		
	    /**
	     * Checks engine's status
		 */
		public boolean isStarted() {
			return working;
		}
		
		//private declarations
		
		private double consumption()
		{
			final double NO_LOAD_CONSUMPTION = working? 0.1: 0;
			return ((double)speed / MAX_SPEED  + NO_LOAD_CONSUMPTION) * 0.05;
		}
		
		/**
		 * @param damageStrength
		 * @throws EngineOutOfOrderException
		 */
		private void addDamage(EngineDamage damageStrength) throws EngineOutOfOrderException
		{
			switch (damageStrength) {
			case LOW:
				damage += 0.1;
				break;
			case MEDIUM:
				damage += 0.2;
				break;
			case HIGH:
				damage += 0.3;
				break;
			}
			
			if (damage >= 1) {
				stop();
				throw new EngineOutOfOrderException("Engine out of order: repair it in automobile workshop");
			}
		}
		
		private int speed;
		private double damage;	
		private boolean working;
		private int gear;
	}
	
	/** starts car's engine
	 * @throws VehicleException
	 */
	@Override
	public void start() throws VehicleException {
		engine.start();	
		move();
	}
	
	/** stops car's engine
	 * @throws VehicleException
	 */
	@Override
	public void stop() throws VehicleException{
		engine.stop();
		move();
	}

	/** activates brakes and stops the car
	 * @throws VehicleException
	 */
	@Override
	public void brake() throws VehicleException {
		while (engine.getSpeed() > 0) {
			decreaseSpeed();
			move();
		}
	}

	/** increases car's speed
	 * @throws VehicleException
	 */
	@Override
	public void increaseSpeed() throws VehicleException {
		engine.increaseSpeed();
		move();
	}

	/** decreases car's speed
	 * @throws VehicleException
	 */
	@Override
	public void decreaseSpeed() throws VehicleException {
		engine.decreaseSpeed();
		move();
	}

	/** starts car's engine
	 * @returns 
	 		distance of movement
	 */
	@Override
	public double distance() {
		return distance;
	} 
	
	/** refuels the car
	 */
	@Override
	public void refuel() {
		fuel = 1;	
	}	
	
	/** try to move the car with the current parameters and increase the distance
	 * @throws EmptyTankException
	 */
	@Override
	public void move() throws EmptyTankException {
		engine.consumeFuel();
		distance += engine.getSpeed() / 10.0;
	}
	
	/** repairs the car
	 */
	@Override
	public void repair() {
		engine.repair();
		
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "speed = " + engine.speed + " of " + engine.MAX_SPEED + " km/h\n" +
		   	   "gear = " + engine.gear + "\n" +
			   "distance = " + distance + "\n" + 
			   "fuel = " + Math.round(fuel * 100) + "%\n" +
			   "current fuel consumption = " + engine.consumption() + "(started:" + engine.isStarted() + ")\n" + 
			   "engine damage = " + Math.round(engine.damage * 100) + "%\n" +
			   "gear top speeds " + Arrays.toString(engine.gearTopSpeed) + 
			   "\n----------------------------------------------------------------\n";
	}
	
	/** get car's optimal gear according to speed
	 * @param speed
	 * 		speed to find the optimal gear
	 * @returns 
	 * 		optimal gear to the speed
	 */
	protected int gearFromSpeed(int speed) {
		for (int gear = 1; gear < engine.gearTopSpeed.length; ++gear) {
			if (engine.gearTopSpeed[gear] >= speed)
				return gear;
		}
		throw new IllegalArgumentException("speed not supported");
	}
	
	//private declarations

	private double fuel = 1;
	protected Engine engine = new Engine();
	private double distance;

}
