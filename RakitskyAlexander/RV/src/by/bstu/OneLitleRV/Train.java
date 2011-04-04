/**
 * 
 */
package by.bstu.OneLitleRV;

import java.util.ArrayList;
import java.util.List;

/**
 * @author RakitskyAlexander
 *@param costKm Kilometer cost
 *@param stoping Array of stopping
 *@param idName ID number train
 *@param goDayOfWeek Days when there is a train
 *@param maxQuantityPlaces - max quantity places
 *@param occupiedPlaces - occupied places of train
 */
public class Train {
	private double costKm;
	private List<Stoping> stoping;
	private int idName;
	private boolean goDayOfWeek[]=new boolean[7];
	private static int maxQuantityPlaces;
	private int occupiedPlaces;
	
	/**
	 * @return the idName
	 */
	public int getIdName() {
		return idName;
	}
	/**
	 * @param idName the idName to set
	 */
	public void setIdName(int idName) {
		this.idName = idName;
	}
	/**
	 * @return the goDayOfWeek
	 */
	public boolean[] getGoDayOfWeek() {
		return goDayOfWeek;
	}
	/**
	 * @param goDayOfWeek the goDayOfWeek to set
	 */
	public void setGoDayOfWeek(boolean[] goDayOfWeek) {
		this.goDayOfWeek = goDayOfWeek;
	}
	/**
	 * @param args
	 */
	

	
	public Train() {
		super();
		stoping=new ArrayList<Stoping>();
		//station=new ArrayList
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the costKm
	 */
	public double getCostKm() {
		return costKm;
	}
	/**
	 * @param costKm the costKm to set
	 */
	public void setCostKm(double costKm) {
		this.costKm = costKm;
	}
	/**
	 * 
	 * @param name
	 */
	public Train(int name) {
		this();
		this.idName = name;
	}
	/**
	 * @return the station
	 */
	public List<Stoping> getStoping() {
		return stoping;
	}
	
	/**
	 * @return the maxQuantityPlaces
	 */
	public static int getMaxQuantityPlaces() {
		return maxQuantityPlaces;
	}
	/**
	 * @param maxQuantityPlaces the maxQuantityPlaces to set
	 */
	public static void setMaxQuantityPlaces(int maxQuantityPlaces) {
		Train.maxQuantityPlaces = maxQuantityPlaces;
	}
	/**
	 * @return the quantityPlaces
	 */
	public int getOccupiedPlaces() {
		return occupiedPlaces;
	}
	/**
	 * add occupiedPlaces with one 
	 */
	public void addOccupiedPlaces()
	{
		occupiedPlaces++;
	}
	/**
	 * @param quantityPlaces the quantityPlaces to set
	 */
	public void setOccupiedPlaces(int quantityPlaces) {
		this.occupiedPlaces = quantityPlaces;
	}
	/**
	 * @param station the station to set
	 */
	public void setStoping(List<Stoping> station) {
		this.stoping = station;
	}
	/**
	 * @param station the station to add
	 */
	public void addStation(Stoping station) {
		if (this.stoping.size()!=0)
		for (int i=0;i<this.stoping.size();i++)
			if (this.stoping.get(i).getDistOfBegin()>station.getDistOfBegin())
			{
				this.stoping.add(i,station);
				return;
			}
		this.stoping.add(station);	
	}
	/**
	 * @param name Name search station
	 * @return Station number - if the station with such name isn't found returns-1
	 */
	 public int searchStation(String name){
		//Stoping tempStation;
		for(int i=0;i<stoping.size();i++)
		{
			//tempStation=station.get(i);
			if ((stoping.get(i).getNameStation())==name)
			{
				return i;
			}
		}
		return -1;
	}
	/**
	 * Sorts stations (at addition)
	 */
	private void sortStation(List<Stoping> station) 
	{
		Stoping swapStation;
		int endDistOfBegin,prevDistOfbegin;
		for(int i=station.size()-1;i>0;i--)
		{
			endDistOfBegin=station.get(i).getDistOfBegin();
			prevDistOfbegin=station.get(i).getDistOfBegin();
			if (endDistOfBegin<prevDistOfbegin)
			{
				swapStation=station.get(prevDistOfbegin);
				station.set(endDistOfBegin, station.get(prevDistOfbegin));
				station.set(endDistOfBegin, swapStation);
			}
			else
				break;
		}
		
	}
	/**
	 * @return the name
	 */
	public int getName() {
		return idName;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(int name) {
		this.idName = name;
	}
	/**
	 * @return the goDayOfWeek
	 */
	public boolean getGoDayOfWeek(int numberDayOfWeek) {
		if (numberDayOfWeek>7)
			return false;
		return goDayOfWeek[numberDayOfWeek-1];
	}
	/**
	 * @param numberDayOfWeek the goDayOfWeek to set
	 */
	public void setGoDayOfWeek(int numberDayOfWeek,boolean goTrainInDay) {
		if (numberDayOfWeek<=7)
		this.goDayOfWeek[numberDayOfWeek-1]=goTrainInDay;
	}

}
