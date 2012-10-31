/**
 * 
 */
package event.recording.car;

import event.recording.car.parts.AbstractGearBox;
import event.recording.car.parts.Battery;
import event.recording.car.parts.Brake;
import event.recording.car.parts.Engine;
import event.recording.car.parts.Tank;

/**
 * @author dima
 *
 */
public class Car
{
	private AbstractGearBox gearBox;
	private Battery battery;
	private Brake brake;
	private Engine engine;
	private Tank tank;
	/**
	 * @return the gearBox
	 */
	public AbstractGearBox getGearBox()
	{
		return gearBox;
	}
	/**
	 * @return the battery
	 */
	public Battery getBattery()
	{
		return battery;
	}
	/**
	 * @return the brake
	 */
	public Brake getBrake()
	{
		return brake;
	}
	/**
	 * @return the engine
	 */
	public Engine getEngine()
	{
		return engine;
	}
	/**
	 * @return the tank
	 */
	public Tank getTank()
	{
		return tank;
	}
	/**
	 * @param engine
	 * @param gearBox
	 * @param tank
	 * @param battery
	 * @param brake
	 */
	public Car(Engine engine, AbstractGearBox gearBox, Tank tank, Battery battery, Brake brake) {
		super();
		this.engine = engine;
		this.gearBox = gearBox;
		this.tank = tank;
		this.battery = battery;
		this.brake = brake;
	}
	
}
