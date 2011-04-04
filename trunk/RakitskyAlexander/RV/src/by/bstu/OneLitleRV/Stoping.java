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
 * @param timeOutput Train arrival time
 */
public class Stoping {
	private String nameStation;
	private int distOfBegin;
	private Calendar timeInput;
	private Calendar timeOutput;
	/**
	 * 
	 */
	
	public Stoping() {
		// TODO Auto-generated constructor stub
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
	 * @return the timeInput
	 */
	public Calendar getTimeInput() {
		return timeInput;
	}
	/**
	 * @param timeInput the timeInput to set
	 */
	public void setTimeInput(int hour,int minute) {
		this.timeInput=new GregorianCalendar(0, 0, 0, hour, minute);
	}
	/**
	 * @return the timeOutput
	 */
	public Calendar getTimeOutput() {
		return timeOutput;
	}
	/**
	 * @param timeOutput the timeOutput to set
	 */
	public void setTimeOutput(int hour,int minute) {
		this.timeOutput=new GregorianCalendar(0, 0, 0, hour, minute);
	}

	public Stoping(String nameStation, int distOfBegin, int hourOutput,int minuteOutput,int hourInput,int minuteInput) {
		super();
		this.nameStation = nameStation;
		this.distOfBegin = distOfBegin;
		setTimeOutput(hourOutput,minuteOutput);
		setTimeInput(hourInput,minuteInput);
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
		this.timeInput = timeInput;
		this.timeOutput = timeOutput;
	}

	
	
}
