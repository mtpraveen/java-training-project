package motor.depot.clientserver.server.scenario.admin;

import java.io.IOException;
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
	public void run() throws IOException {
		//driver
		ListWithIds<Driver> drivers = thread.getMotorDepot().drivers.getCopy();
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
				for (Trip trip : thread.getMotorDepot().getTripsByState(TripState.STARTED)) {
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
		driverTableScenario.setCaption(thread.getString("AddTripScenarion.Select_driver")); //$NON-NLS-1$
		driverTableScenario.run();
		if(driverTableScenario.getSelectedRow() == -1)
			return;
		driver = drivers.get(driverTableScenario.getSelectedRow());
		//car
		ListWithIds<Car> cars = thread.getMotorDepot().cars.getCopy();
		Iterator<Car> carIterator = cars.iterator();
		while(carIterator.hasNext())
		{
			Car car = carIterator.next();
			boolean needRemove = false;
			for (Trip trip : thread.getMotorDepot().getTripsByState(TripState.STARTED)) {
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
		carTableScenario.setCaption(thread.getString("AddTripScenarion.Select_car")); //$NON-NLS-1$
		carTableScenario.run();
		if(carTableScenario.getSelectedRow() == -1)
			return;
		Car car = cars.get(carTableScenario.getSelectedRow());
		//
		str(thread.getString("AddTripScenarion.Enter_start")); //$NON-NLS-1$
		String start = readString();
		str(thread.getString("AddTripScenarion.Enter_destination")); //$NON-NLS-1$
		String destination = readString();
		str(thread.getString("AddTripScenarion.Enter_description")); //$NON-NLS-1$
		String description = readString();
		//
		Trip trip = thread.getMotorDepot().addTrip(driver, car, start, destination, description);
		if(trip == null)
			str(thread.getString("AddTripScenarion.Trip_not_created")); //$NON-NLS-1$
		else
			str(thread.getString("AddTripScenarion.Trip_created")); //$NON-NLS-1$
		waitForInput();
	}

}
