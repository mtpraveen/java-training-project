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
 * @param reqestion  The reqestion which submits the passenger
 * @param passengerGo Whether there will go the passenger - logic type
 * @param stationOn name departure station 
 * @param stationIn name arrival station
 * @param geDate Date when the passenger is going to make a trip
 */
public class Passenger {

	private String name;
	private String surName;
	private int money;
	private TrainPassengerGo trainGo;
	private Reqestion reqestion;
	private boolean passengerGo;
	private String stationOn;
	private String stationIn;
	private Calendar goDate;
	
	/**
	 * @return the stationOn
	 */
	public String getStationOn() {
		return stationOn;
	}
	/**
	 * @param stationOn the stationOn to set
	 */
	public void setStationOn(String stationOn) {
		this.stationOn = stationOn;
	}
	/**
	 * @return the stationIn
	 */
	public String getStationIn() {
		return stationIn;
	}
	/**
	 * @param stationIn the stationIn to set
	 */
	public void setStationIn(String stationIn) {
		this.stationIn = stationIn;
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
	 * Geting full name of passenger
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
		trainGo=reqestion.EnterResultSeach();
		//trainGo=reqestion.getTrainGo();
	}
	
	/**
	 * @return the reqestion
	 */
	public Reqestion getReqestion() {
		return reqestion;
	}
	/**
	 * @param name
	 * @param surName
	 * @param money
	 * @param stationOn
	 * @param stationIn
	 * @param goDate
	 */
	public Passenger(String name, String surName, int money, String stationOn,
			String stationIn, Calendar goDate) {
		super();
		this.name = name;
		this.surName = surName;
		this.money = money;
		this.stationOn = stationOn;
		this.stationIn = stationIn;
		this.goDate = goDate;
		reqestion=new Reqestion(this);
	}
	
	}
	
	
