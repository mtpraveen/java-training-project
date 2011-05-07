package transport;
/**
 * @author Мара
 * 
 * describe structure of element of List of Transport.
 * A part of info (idTrans, transType, carNumber) read from the "transport.txt" file, the other from Way's&Breakage's Lists.
 * @param idTrans the identifier of the Transport in file
 * @param typeTrans the identifier of transport: 0 - bus, 1 - trolleybus, 2- tram.
 * @param breakage the identifier of transport's ability to work: 0 - working; 1 - broken.
 * @param idWay the identifier of the Way in file
 * @param carNumber the car registration number
 * **/
public class Transport {
	private int idTrans;
	private int transType;
	private int breakage;
	private int idWay;
	private String carNumber;

	public Transport() {
	}

	public int getIdTrans() {
		return idTrans;
	}

	public void setIdTrans(int idTrans) {
		this.idTrans = idTrans;
	}

	public int getTransType() {
		return transType;
	}

	public void setTransType(int transType) {
		this.transType = transType;
	}

	public int getBreakage() {
		return breakage;
	}

	public void setBreakage(int breakage) {
		this.breakage = breakage;
	}

	public int getIdWay() {
		return idWay;
	}

	public void setIdWay(int idWay) {
		this.idWay = idWay;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
}