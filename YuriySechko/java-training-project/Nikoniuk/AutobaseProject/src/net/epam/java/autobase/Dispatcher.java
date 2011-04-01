package net.epam.java.autobase;

import java.util.ArrayList;

public class Dispatcher extends User implements IDispatcher {

	public Dispatcher(int id, String username, String password) {
		super(id, username, password);
	}
	
	public Dispatcher() { }

	@Override
	public Request createRaceRequest(int sid, Driver driver, Car car) {
		return server.createRaceRequest(sid, driver, car);
	}

	@Override
	public void setDriverSuspended(int sid, Driver driver, boolean value) {
		server.setDriverSuspended(sid, driver, value);		
	}

	@Override
	public ArrayList<Car> getAvailableCars(int sid) {
		return server.getAvailableCars(sid);
	}

	@Override
	public ArrayList<Driver> getAvailableDrivers(int sid) {
		return server.getAvailableDrivers(sid);
	}

	@Override
	public ArrayList<Request> getRaceRequests(int sid) {
		return server.getRaceRequests(sid);
	}

	@Override
	public ArrayList<Request> getRepairRequests(int sid) {
		return server.getRepairRequests(sid);
	}

	@Override
	public void setRequestStatus(int sid, Request request, RequestStatus status) {
		server.setRequestStatus(sid, request, status);	
	}

	@Override
	public Request getRequestById(int sid, int id) {
		return server.getRequestById(sid, id);
	}

	@Override
	public Car getCarById(int sid, int id) {
		return server.getCarById(sid, id);
	}

	@Override
	public Driver getDriverById(int sid, int id) {
		return server.getDriverById(sid, id);
	}

	@Override
	public Car addCar(String number, String brand) {
		return server.addCar(number, brand);
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Dispatcher " + getUsername() + "(suspended=" + isSuspended()
		+ ", id=" + getId() + ")";
	}
	
	

}
