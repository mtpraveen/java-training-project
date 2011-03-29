package net.epam.java.autobase;

import java.util.ArrayList;

public interface IDispatcher {
	public Request createRaceRequest(int sid, Driver driver, Car car);
	
	public void setDriverSuspended(int sid, Driver driver, boolean value);
	
	public ArrayList<Car> getAvailableCars(int sid);
	
	public ArrayList<Driver> getAvailableDrivers(int sid);
	
	public ArrayList<Request> getRaceRequests(int sid);
	
	public ArrayList<Request> getRepairRequests(int sid);
	
	public void setRequestStatus(int sid, Request request, RequestStatus status);
	
	public Request getRequestById(int sid, int id);
	
	public Car getCarById(int sid, int id);
	
	public Driver getDriverById(int sid, int id);
	
	public Car addCar(String number, String brand);
}
