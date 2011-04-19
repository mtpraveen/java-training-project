package autobase.model;

import java.util.ArrayList;

/**
 * <p>Represents driver operations </p>
 * 
 * @author Alexander Nikoniuk
 */
public interface IDriver {

	/**
	 * Creates repair request
	 * 
	 * @param sid
	 *            current session id
	 * @param car
	 *            car to repair
	 * @param carState
	 *            current state of the car 
	 * @return
	 * 			new repair request    
	 */
    public Request createRepairRequest(int sid, Car car, String carState);

	/**
	 * Marks race completed
	 * 
	 * @param sid
	 *            current session id
	 * @param race
	 *            race request to set the completed status
	 * @param carState
	 *            current state of the car    
	 */
    public void markRaceCompleted(int sid, Request race, String carState);

	/**
	 * Returns all race requests
	 * 
	 * @param sid
	 *            current session id  
	 * @return
	 * 			race requests list        
	 */
    public ArrayList<Request> getRaceRequests(int sid);

	/**
	 * Returns all available cars
	 * 
	 * @param sid
	 *            current session id  
	 * @return
	 * 			race cars list          
	 */
    public ArrayList<Car> getAvailableCars(int sid);
    
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
	 * Returns race request by id
	 * 
	 * @param sid 
	 * 		 current session id
	 * @param id 
	 * 		 race request id
	 * @return 
	 * 		 race request corresponding to id
	 */
	Request getRaceRequestById(int sid, int id);

}
