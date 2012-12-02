package motor.depot.clientserver.server.scenario.users;

import java.io.IOException;
import java.util.Iterator;

import motor.depot.clientserver.server.ClientThread;
import motor.depot.clientserver.server.scenario.AbstractScenario;
import motor.depot.clientserver.server.scenario.tables.TableScenario;
import motor.depot.listclasses.ListWithIds;
import motor.depot.model.MotorDepot;
import motor.depot.model.Trip;
import motor.depot.model.enums.TripState;

public class FinishTripScenario extends AbstractScenario {

	public FinishTripScenario(ClientThread thread) {
		super(thread);
	}

	@Override
	public void run() throws IOException {
		ListWithIds<Trip> trips = thread.getMotorDepot().getTripsByState(TripState.STARTED);
		Iterator<Trip> iterator = trips.iterator();
		while(iterator.hasNext())
		{
			if(iterator.next().getDriver() != thread.getUser())
				iterator.remove();
		}
		if(trips.size() == 0)
		{
			str(thread.getString("You_dont_have_active_trips")); //$NON-NLS-1$
			return;
		}
		TableScenario tableScenario = new TableScenario(thread, trips.getTableProvider());
		tableScenario.setSelectable(true);
		tableScenario.setCaption(thread.getString("Select_trip")); //$NON-NLS-1$
		tableScenario.run();
		if (tableScenario.getSelectedRow() == -1)
			return;
		Trip trip = trips.get(tableScenario.getSelectedRow());
		TripState state;
		if(question(thread.getString("Trip_was_ended_successfully"))) //$NON-NLS-1$
			state = TripState.FINISHED;
		else
			state = TripState.ERROR;
		str(thread.getString("Enter_car_state_after_trip")); //$NON-NLS-1$
		String carstate = readString();
		trip.setState(state);
		trip.getCar().setState(carstate);
		waitForInput();
	}

}
