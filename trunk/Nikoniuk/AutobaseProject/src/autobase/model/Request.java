package autobase.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * <p>Can be used for storing data about different requests of {@link RequestType}</p>
 * @author Alexander Nikoniuk
 *
 */
public class Request {
    private int id;
    private RequestType type;
    private RequestStatus status;
    private String startDate;
    private String endDate;
    private String carState;
    private Driver driver;
    private Car car;
    
    /**
	 * @return the request type
	 */
	public RequestType getType() {
		return type;
	}

	/**
	 * @param carState the carState to set
	 */
	public void setCarState(String carState) {
		this.carState = carState;
	}


	/**
	 * constructor
	 * @param id
	 * @param type
	 * @param driver
	 * @param car
	 */
	public Request(int id, RequestType type, Driver driver, Car car) {
		Calendar cal = new GregorianCalendar();
		this.startDate = new SimpleDateFormat("dd.MM.yyyy hh:mm").format(cal.getTime());
		
		this.status = RequestStatus.ACTIVE;
		this.type = type;
		this.driver = driver;
		this.car = car;
		this.id = id;
    }
	
	/**
	 * constructor
	 * @param id
	 * @param type
	 * @param driver
	 * @param car
	 * @param car
	 */
	public Request(int id, RequestType type, Driver driver, Car car, String carState) {
		this(id, type, driver, car);
		this.carState = carState;
    }

	/**
	 * @param status the status to set
	 */
    public void setStatus(RequestStatus status) {
    	if (status == RequestStatus.COMPLETED) {
    		Calendar cal = new GregorianCalendar();
    		endDate = new SimpleDateFormat("dd.MM.yyyy hh:mm").format(cal.getTime());
    	}
  
    	this.status = status;
    }
    
    

	/**
	 * @return the driver
	 */
	public Driver getDriver() {
		return driver;
	}

	/**
	 * @param driver the driver to set
	 */
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	
	

	/**
	 * @return the car
	 */
	public Car getCar() {
		return car;
	}

	/**
	 * @param car the car to set
	 */
	public void setCar(Car car) {
		this.car = car;
	}
	
	

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		if (endDate != null)
			return endDate;
		else
			return "";
		
	}
	
	

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the status
	 */
	public RequestStatus getStatus() {
		return status;
	}

	/**
	 * @return the carState
	 */
	public String getCarState() {
		if (carState != null)
			return carState;
		else
			return "";
	}
	
	

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return "\n" + type + " report " + " ¹" + id +
			   "\nstatus: " + status.toString() +
			   "\nstart date: " + startDate + (status == RequestStatus.COMPLETED? ", end date: " + endDate: "") +
			   "\ndriver id: " + driver.getId() + 
			   "\ncar: " + car + "(state: " + carState.toString() + ")\n";
	}
    
    

}
