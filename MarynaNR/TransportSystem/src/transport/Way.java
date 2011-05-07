package transport;
/**
 * @author Мара
 * 
 * describe structure of element of List of Way.
 * All info (except stopTime) read from the "Ways.txt" file.
 * @param stopNumber the number of Stops in the Way
 * @param idWay the identifier of the Way in file
 * @param transNumber the number of transports working on this Way
 * @param stopTime time between neighboring buses leaved one busStop - range of motion. StopTime calculate as: StopNumber*2*3/TransNumber. The number 2 is because of "there and back" direction. The number 3 is because of the time between two neighboring stops is taken as three minutes. 
 * @param wayNumber the number of way as it's known for people
 * @param typeTrans the identifier of transport: 0 - bus, 1 - trolleybus, 2- tram.
 * @param firstStop the First Stop in the Way
 * @param lastStop the Last Stop in the Way
 * **/
public class Way {
	private int stopNumber;
	private int idWays;
	private int transNumber;
	private int stopTime;
	private int wayNumber;
	private int typeTrans;
	private String firstStop;
	private String lastStop;

	public int getStopNumber() {
		return stopNumber;
	}

	public void setStopNumber(int stopNumber) {
		this.stopNumber = stopNumber;
	}

	public int getIdWays() {
		return idWays;
	}

	public void setIdWays(int idWays) {
		this.idWays = idWays;
	}

	public int getTransNumber() {
		return transNumber;
	}

	public void setTransNumber(int transNumber) {
		this.transNumber = transNumber;
	}

	public int getStopTime() {
		return stopTime;
	}

	public void setStopTime(int stopTime) {
		this.stopTime = stopTime;
	}

	public int getWayNumber() {
		return wayNumber;
	}

	public void setWayNumber(int wayNumber) {
		this.wayNumber = wayNumber;
	}

	public int getTypeTrans() {
		return typeTrans;
	}

	public void setTypeTrans(int typeTrans) {
		this.typeTrans = typeTrans;
	}

	public String getFirstStop() {
		return firstStop;
	}

	public void setFirstStop(String firstStop) {
		this.firstStop = firstStop;
	}

	public String getLastStop() {
		return lastStop;
	}

	public void setLastStop(String lastStop) {
		this.lastStop = lastStop;
	}
}