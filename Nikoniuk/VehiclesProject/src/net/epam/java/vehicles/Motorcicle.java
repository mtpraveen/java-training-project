/**
 * The code is licensed under GPL v.3.
 */
package net.epam.java.vehicles;

public class Motorcicle  implements IMotorVehicle {
	
	protected class Engine implements IEngine {
		
		@Override
		public void consumeFuel() throws EmptyTankException {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void changeGear(int newGear) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void start() throws EngineOutOfOrderException {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void stop() {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void increaseSpeed() throws EngineOutOfOrderException {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void decreaseSpeed() {
			// TODO Auto-generated method stub
			
		}
		@Override
		public int getSpeed() {
			// TODO Auto-generated method stub
			return 0;
		}
		@Override
		public void repair() {
			// TODO Auto-generated method stub
			
		}
		
		private int speed;
		private double damage;	
		private boolean started;
		private int gear;
	}

	@Override
	public void start() throws VehicleException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void stop() throws VehicleException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void brake() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void increaseSpeed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void decreaseSpeed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double distance() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void move() throws EmptyTankException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void changeGear(int newGear) throws VehicleException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refuel() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void repair() {
		// TODO Auto-generated method stub
		
	}
	
	//private declarations
	
	double fuel;
	private Engine engine = new Engine();
	private double distance;
}
