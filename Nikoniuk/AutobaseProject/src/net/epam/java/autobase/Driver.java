package net.epam.java.autobase;

import java.util.ArrayList;

public class Driver extends User implements IDriver {

	public Driver(int id, String username, String password) {
		super(id, username, password);
	}
	
	public Driver() { }

	@Override
	public Request createRepairRequest(int sid, Car car, String carState) {
		return server.createRepairRequest(sid, car, carState);
	}

	@Override
	public void markRaceCompleted(int sid, Request race, String carState) {
		server.markRaceCompleted(sid, race, carState);
	}

	@Override
	public ArrayList<Request> getRaceRequests(int sid) {
		return server.getRaceRequests(sid);
	}

	@Override
	public ArrayList<Car> getAvailableCars(int sid) {
		return server.getAvailableCars(sid);
	}

	@Override
	public Car getCarById(int sid, int id) {
		return server.getCarById(sid, id);
	}

	@Override
	public Request getRaceRequestById(int sid, int id) {
		return server.getRaceRequestById(sid, id);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Driver " + getUsername() + "(suspended=" + isSuspended()
		+ ", id=" + getId() + ")";
	}
	
	

}
