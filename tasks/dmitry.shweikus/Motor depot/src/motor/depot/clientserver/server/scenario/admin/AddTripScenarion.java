package motor.depot.clientserver.server.scenario.admin;

import java.util.Iterator;

import motor.depot.clientserver.server.ClientThread;
import motor.depot.clientserver.server.scenario.AbstractScenario;
import motor.depot.clientserver.server.scenario.tables.TableScenario;
import motor.depot.listclasses.ListWithIds;
import motor.depot.model.Car;
import motor.depot.model.Driver;
import motor.depot.model.MotorDepot;
import motor.depot.model.Trip;
import motor.depot.model.enums.TripState;

public class AddTripScenarion extends AbstractScenario {

	public AddTripScenarion(ClientThread thread) {
		super(thread);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		//driver
		ListWithIds<Driver> drivers = MotorDepot.getInstance().drivers.getCopy();
		Iterator<Driver> driverIterator = drivers.iterator();
		Driver driver;
		while(driverIterator.hasNext())
		{
			driver = driverIterator.next();
			if(!driver.isActive())
			{	
				driverIterator.remove();
			}
			else
			{
				boolean needRemove = false;
				for (Trip trip : MotorDepot.getInstance().getTripsByState(TripState.STARTED)) {
					if(trip.getDriver() == driver)
					{
						needRemove = true;
						break;
					}
				}
				if (needRemove)
					driverIterator.remove();
			}
		}
		TableScenario driverTableScenario = new TableScenario(thread, drivers.getTableProvider());
		driverTableScenario.setSelectable(true);
		driverTableScenario.setCaption("Select driver:");
		driverTableScenario.run();
		if(driverTableScenario.getSelectedRow() == -1)
			return;
		driver = drivers.get(driverTableScenario.getSelectedRow());
		//car
		ListWithIds<Car> cars = MotorDepot.getInstance().cars.getCopy();
		Iterator<Car> carIterator = cars.iterator();
		while(carIterator.hasNext())
		{
			Car car = carIterator.next();
			boolean needRemove = false;
			for (Trip trip : MotorDepot.getInstance().getTripsByState(TripState.STARTED)) {
				if(trip.getCar() == car)
				{
					needRemove = true;
					break;
				}
			}
			if (needRemove)
				driverIterator.remove();
		}
		TableScenario carTableScenario = new TableScenario(thread, cars.getTableProvider());
		carTableScenario.setSelectable(true);
		carTableScenario.setCaption("Select car:");
		carTableScenario.run();
		if(carTableScenario.getSelectedRow() == -1)
			return;
		Car car = cars.get(carTableScenario.getSelectedRow());
		//
		str("Enter start:");
		String start = readString();
		str("Enter destination:");
		String destination = readString();
		str("Enter description:");
		String description = readString();
		//
		Trip trip = MotorDepot.getInstance().addTrip(driver, car, start, destination, description);
		if(trip == null)
			str("Trip not created");
		else
			str("Trip created");
		waitForInput();
	}

}
