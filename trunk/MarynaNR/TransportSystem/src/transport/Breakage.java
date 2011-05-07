package transport;
/**
 * @author Мара
 * 
 * describe structure of element of List of Breakage.
 * A part of info (idTrans, transType, carNumber) read from the "breakage.txt" file, the other from Way's&Breakage's Lists.
 * @param idTrans the identifier of the Transport in List of Transport   
 * @param idBreakage the identifier of the Transport in "breakage.txt" file    
 * @param idWay the number of way of broken transport as it's known for people
 * @param dateBr the date when the breakage was happened
 * @param descroption describe the nature of breakage.
 * **/
public class Breakage {
	private int idTrans;
	private int idBreakage;
	private int idWay;
	private String dateBr;
	private String description;

	public Breakage() {
	}

	public int getIdTrans() {
		return idTrans;
	}

	public void setIdTrans(int idTrans) {
		this.idTrans = idTrans;
	}

	public int getIdBreakage() {
		return idBreakage;
	}

	public void setIdBreakage(int idBreakage) {
		this.idBreakage = idBreakage;
	}

	public int getIdWay() {
		return idWay;
	}

	public void setIdWay(int idWay) {
		this.idWay = idWay;
	}

	public String getDateBr() {
		return dateBr;
	}

	public void setDateBr(String dateBr) {
		this.dateBr = dateBr;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}