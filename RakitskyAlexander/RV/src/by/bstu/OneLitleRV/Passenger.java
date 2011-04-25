/**
 * 
 */
package by.bstu.OneLitleRV;

import java.util.Calendar;

/**
 * @author Rakitsky Alexaner
 *
 */
/**
 * 
 * @param name Name of passenger
 * @param surName surname of passenger
 * @param money Money which is at the passenger
 * @param trainGo The train on which will go the passenger
 * @param reqestion  The requisition which submits the passenger
 * @param passengerGo Whether there will go the passenger - logic type
 * @param stopingDeparture name departure station 
 * @param stopingArrival name arrival station
 * @param geDate Date when the passenger is going to make a trip
 */
public class Passenger {

	private String name;
	private String surName;
	private int money;
	private TrainPassengerGo trainGo;
	private Requisition requisition;
	private boolean passengerGo;
	private String stopingDeparture;
	private String stopingArrival;
	private Calendar goDate;
	
	/**
	 * @return the stopingDeparture
	 */
	public String getStopingDeparture() {
		return stopingDeparture;
	}
	/**
	 * @param stopingDeparture the stationOn to set
	 */
	public void setStopingDeparture(String stopingDeparture) {
		this.stopingDeparture = stopingDeparture;
	}
	/**
	 * @return the stopingArrival
	 */
	public String getStopingArrival() {
		return stopingArrival;
	}
	/**
	 * @param stopingArrival the stationIn to set
	 */
	public void setStopingArrival(String stopingArrival) {
		this.stopingArrival = stopingArrival;
	}
	/**
	 * @return the goDate
	 */
	public Calendar getGoDate() {
		return goDate;
	}
	/**
	 * @param goDate the goDate to set
	 */
	public void setGoDate(Calendar goDate) {
		this.goDate = goDate;
	}
	/**
	 * @return the trainGo
	 */
	public TrainPassengerGo getTrainGo() {
		return trainGo;
	}
	/**
	 * @param passengerGo the passengerGo to set
	 */
	public void setPassengerGo(boolean passengerGo) {
		this.passengerGo = passengerGo;
	}
	/**
	 * @return the name
	 */
	
	public String getName() {
		return name;
	}
	/**
	 * @return the trainPassengerGo
	 */
	public Train getPassengerGo() {
		return trainGo;
	}
	/**
	 * @param trainGo the trainPassengerGo to set
	 */
	public void setTrainGo(TrainPassengerGo trainGo) {
		if (trainGo!=null)
			passengerGo=true; 
		else{
			passengerGo=false; 
			this.trainGo = trainGo;
		}
	}
	/**
	 * @return the passengerGo
	 */
	public boolean isPassengerGo() {
		return passengerGo;
	}
	/**
	 * @param passengerGo the passengerGo to set
	 */
	public void setGo(boolean passengerGo) {
		this.passengerGo = passengerGo;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the surName
	 */
	public String getSurName() {
		return surName;
	}
	/**
	 * @param surName the surName to set
	 */
	public void setSurName(String surName) {
		this.surName = surName;
	}
	/**
	 * @return the money
	 */
	public int getMoney() {
		return money;
	}
	/**
	 * @param money the money to set
	 */
	public void setMoney(int money) {
		this.money = money;
	}
	/**
	 * Getting full name of passenger
	 * @param name
	 * @param money
	 */
	public String getFullNume()
	{
		return (name+" "+surName);
	}
	/**
	 * 
	 * @param name
	 * @param serName
	 * @param money
	 */
	public Passenger(String name, String serName, int money) {
		super();
		this.name = name;
		this.surName = serName;
		this.money = money;
	}
	/**
	 * Application
	 */
	public void setReqestion(){
		trainGo=requisition.EnterResultSeach();
		//trainGo=reqestion.getTrainGo();
	}
	
	/**
	 * @return the requisition
	 */
	public Requisition getRequisition() {
		return requisition;
	}
	/**
	 * @param name
	 * @param surName
	 * @param money
	 * @param stopingDeparture
	 * @param stopingArrival
	 * @param goDate
	 */
	public Passenger(String name, String surName, int money, String stopingDeparture,
			String stopingArrival, Calendar goDate) {
		super();
		this.name = name;
		this.surName = surName;
		this.money = money;
		this.stopingDeparture = stopingDeparture;
		this.stopingArrival = stopingArrival;
		this.goDate = goDate;
		requisition=new Requisition(this);
	}
	
	}
	
	
