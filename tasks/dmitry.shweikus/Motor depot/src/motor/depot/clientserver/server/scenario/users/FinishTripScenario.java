package motor.depot.clientserver.server.scenario.users;

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
	public void run() {
		ListWithIds<Trip> trips = MotorDepot.getInstance().getTripsByState(TripState.STARTED);
		Iterator<Trip> iterator = trips.iterator();
		while(iterator.hasNext())
		{
			if(iterator.next().getDriver() != thread.getUser())
				iterator.remove();
		}
		if(trips.size() == 0)
		{
			str("You don't have active trips");
			return;
		}
		TableScenario tableScenario = new TableScenario(thread, trips.getTableProvider());
		tableScenario.setSelectable(true);
		tableScenario.setCaption("Select trip:");
		tableScenario.run();
		if (tableScenario.getSelectedRow() == -1)
			return;
		Trip trip = trips.get(tableScenario.getSelectedRow());
		TripState state;
		if(question("Trip was ended successfully?"))
			state = TripState.FINISHED;
		else
			state = TripState.ERROR;
		str("Enter car state after trip");
		String carstate = readString();
		trip.setState(state);
		trip.getCar().setState(carstate);
		waitForInput();
	}

}
