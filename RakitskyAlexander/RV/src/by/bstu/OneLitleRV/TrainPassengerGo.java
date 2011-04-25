/**
 * 
 */
package by.bstu.OneLitleRV;

/**
 * @author �������������
 *
 */
/**
 * The train on which will go the passenger
 * @param stopingDeparture 
 * @param stopingArrival
 * @param moneyFare Fare from station of departure to arrival station
 */
public class TrainPassengerGo extends Train {
	private Stoping stopingDeparture;
	private Stoping stopingArrival;
	private int moneyFare;
	
	/**
	 * @return the stopingDeparture
	 */
	public Stoping getStopingDeparture() {
		return stopingDeparture;
	}

	/**
	 * @param stopingDeparture the stopingDeparture to set
	 */
	public void setStopingDeparture(String stopingDeparture) {
		this.stopingDeparture = getStoping().get(searchStation(stopingDeparture));
	}

	/**
	 * @return the stopingArrival
	 */
	public Stoping getStopingArrival() {
		return stopingArrival;
	}

	/**
	 * @param stopingArrival the stopingArrival to set
	 */
	public void setStopingArrival(String stopingArrival) {
		this.stopingArrival = getStoping().get(searchStation(stopingArrival));
	}

	/**
	 * @return the moneyFare = dist*cosKm
	 */
	public int getMoneyFare() {
		return moneyFare;
	}

	/**
	 *  the moneyFare to set
	 */
	public void setMoneyFare() {
		moneyFare=(int) Math.round(distanceStoping()*getCostKm());
	}

	/**
	 * @return distance  which will pass a train from station of departure to arrival station
	 */
	
	public int distanceStoping()
	{
		return stopingArrival.getDistOfBegin()-stopingDeparture.getDistOfBegin();
	}

	public TrainPassengerGo() {
		super();
		stopingDeparture=null;
		stopingArrival=null;
		moneyFare=0;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param stopingDeparture
	 * @param stopingArrival
	 */
	public TrainPassengerGo(String stopingDeparture, String stopingArrival) {
		super();
		setStopingDeparture(stopingDeparture);
		setStopingArrival(stopingArrival);
	}

	/**
	 * @param nameStopingDeparture 
	 * @param nameStopinArrival 
	 * @param stopingDeparture
	 * @param stopingArrival
	 * @param moneyFare
	 */
	public TrainPassengerGo(Train train, String nameStopingDeparture, String nameStopinArrival) {
		super();
		stopingDeparture = train.getStoping().get(train.searchStation(nameStopingDeparture));
		stopingArrival=train.getStoping().get(train.searchStation(nameStopinArrival));;
		setCostKm(train.getCostKm());
		setStoping(train.getStoping());
		setIdName(train.getIdName());
		setGoDayOfWeek(train.getGoDayOfWeek());
		setOccupiedPlaces(train.getOccupiedPlaces());
		setMoneyFare();
	}
	
}
