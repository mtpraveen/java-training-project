/**
 * 
 */
package by.bstu.OneLitleRV;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author epam0003
 * @param nameStation Name of Station
 * @param distOfBegin Distance of first stopping
 * @param timeInput Train departure time
 * @param timeDeparture Train arrival time
 * @param quantityOccupiedPlaces Quantity of the occupied places
 */
public class Stoping {
	private String nameStation;
	private int distOfBegin;
	private Calendar timeArrival;
	private Calendar timeDeparture;
	private int quantityOccupiedPlaces;
	/**
	 * 
	 */
	
	public Stoping() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return the quantityOccupiedPlaces
	 */
	public int getQuantityOccupiedPlaces() {
		return quantityOccupiedPlaces;
	}

	/**
	 * Addition of quantity of empty seats on unit
	 */
	public void addQuantityOccupiedPlaces() {
		quantityOccupiedPlaces++;
	}
	/**
	 * Reduction of quantity of empty seats by unit
	 */
	public void decQuantityOccupiedPlaces() {
		quantityOccupiedPlaces--;
	}
	/**
	 * @return the nameStation
	 */
	public String getNameStation() {
		return nameStation;
	}
	/**
	 * @param nameStation the nameStation to set
	 */
	public void setNameStation(String nameStation) {
		this.nameStation = nameStation;
	}
	/**
	 * @return the distOfBegin
	 */
	public int getDistOfBegin() {
		return distOfBegin;
	}
	/**
	 * @param distOfBegin the distOfBegin to set
	 */
	public void setDistOfBegin(int distOfBegin) {
		this.distOfBegin = distOfBegin;
	}
	/**
	 * @return the timeArrival
	 */
	public Calendar getTimeArrival() {
		return timeArrival;
	}
	/**
	 * @param timeInput the timeInput to set
	 */
	public void setTimeArrival(int hour,int minute) {
		this.timeArrival=new GregorianCalendar(0, 0, 0, hour, minute);
	}
	/**
	 * @return the timeOutput
	 */
	public Calendar getTimeDeparture() {
		return timeDeparture;
	}
	/**
	 * @param timeDeparture the timeOutput to set
	 */
	public void setTimeDeparture(int hour,int minute) {
		this.timeDeparture=new GregorianCalendar(0, 0, 0, hour, minute);
	}

	public Stoping(String nameStation, int distOfBegin, int hourDeparture,int minuteDeparture,int hourArrival,int minuteArrival) {
		super();
		this.nameStation = nameStation;
		this.distOfBegin = distOfBegin;
		setTimeDeparture(hourDeparture,minuteDeparture);
		setTimeArrival(hourArrival,minuteArrival);
	}
	/**
	 * @param nameStation
	 * @param distOfBegin
	 * @param timeInput
	 * @param timeOutput
	 */
	public Stoping(String nameStation, int distOfBegin, Calendar timeOutput,
			Calendar timeInput) {
		super();
		this.nameStation = nameStation;
		this.distOfBegin = distOfBegin;
		this.timeArrival = timeInput;
		this.timeDeparture = timeOutput;
	}

	
	
}
