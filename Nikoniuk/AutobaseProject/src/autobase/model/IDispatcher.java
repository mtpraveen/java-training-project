package autobase.model;

import java.util.ArrayList;

import autobase.exceptions.AddCarException;

/**
 * <p>Represents dispatcher operations </p>
 * 
 * @author Alexander Nikoniuk
 */
public interface IDispatcher {

	/**
	 * Creates race report
	 * 
	 * @param sid
	 *            current session id
	 * @param filename
	 *            report's filename
	 * @param sattus
	 *            request status       
	 */
	public void createReport(int sid, String filename, RequestStatus status);
	
	/**
	 * Creates race request
	 * 
	 * @param sid
	 *            current session id
	 * @param driver
	 *            race performer
	 * @param car
	 *            car for the race      
	 */
	public Request createRaceRequest(int sid, Driver driver, Car car);
	
	/**
	 * Sets driver suspend status
	 * 
	 * @param sid
	 *            current session id
	 * @param driver
	 * @param value
	 *            suspend status   
	 * @return
	 * 		race request
	 */
	public void setDriverSuspended(int sid, Driver driver, boolean value);
	
	/**
	 * Returns available cars
	 * 
	 * @param sid 
	 * 			 current session id
	 * @return
	 * 		available cars
	 */
	public ArrayList<Car> getAvailableCars(int sid);
	
	/**
	 * Returns available drivers
	 * 
	 * @param sid 
	 * 			 current session id
	 * @return
	 * 		available drivers
	 */
	public ArrayList<Driver> getAvailableDrivers(int sid);
	
	/**
	 * Returns race requests
	 * 
	 * @param sid 
	 * 			 current session id
	 * @return
	 * 		race requests
	 */
	public ArrayList<Request> getRaceRequests(int sid);
	
	/**
	 * Returns repair requests
	 * 
	 * @param sid 
	 * 			 current session id
	 * @return
	 * 		repair requests
	 */
	public ArrayList<Request> getRepairRequests(int sid);
	
	/**
	 * Sets request status
	 * 
	 * @param sid 
	 * 		 current session id
	 * @param request 
	 * 		 request to change the status 
	 * @param status 
	 * 		 status to set
	 */
	public void setRequestStatus(int sid, Request request, RequestStatus status);
	
	/**
	 * Returns request by id
	 * 
	 * @param sid 
	 * 		 current session id
	 * @param id 
	 * 		 request id
	 * @return 
	 * 		 request corresponding to id
	 */
	public Request getRequestById(int sid, int id);
	
	/**
	 * Returns car by id
	 * 
	 * @param sid 
	 * 		 current session id
	 * @param id 
	 * 		 car id
	 * @return 
	 * 		 car corresponding to id
	 */
	public Car getCarById(int sid, int id);
	
	/**
	 * Returns driver by id
	 * 
	 * @param sid 
	 * 		 current session id
	 * @param id 
	 * 		 driver id
	 * @return 
	 * 		 driver corresponding to id
	 */
	public Driver getDriverById(int sid, int id);
	
	/**
	 * Adds car to autobase
	 * 
	 * @param sid 
	 * 		 current session id
	 * @param number 
	 * 		 car's number
	 * @param brand 
	 * 		 car's brand
	 * @return 
	 * 		 new car object
	 * @throws AddCarException 
	 */
	public Car addCar(int sid, String number, String brand) throws AddCarException;
	
	/**
	 * Adds car to autobase
	 * 
	 * @param sid 
	 * 		 current session id
	 * @param number 
	 * 		 car's number
	 * @param brand 
	 * 		 car's brand
	 * @return 
	 * 		 new car object
	 */
	public void deleteCar(int sid, Car carForDeleting);
}
