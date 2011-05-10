package transports;
import java.util.Date;
/**
 * @author Мара
 * 
 * describe structure of element of List of Assignment.
 * This info is writing to a file "Assignment_DATE.txt", where DATE is current Date.
 * @param idAssign the identifier of the Assignment in file
 * @param idTrans the identifier of the Transport in List of Transport   
 * @param idWays the number of way of working transport as it's known for people
 * @param dat the date when the assignment was happened
 **/

public class Assignment {
	private int idAssign;
	private int idTrans;
	private int idWays;
	private Date date;

	public Assignment() {
	}

	public int getIdAssign() {
		return idAssign;
	}

	public void setIdAssign(int idAssign) {
		this.idAssign = idAssign;
	}

	public int getIdTrans() {
		return idTrans;
	}

	public void setIdTrans(int idTrans) {
		this.idTrans = idTrans;
	}

	public int getIdWays() {
		return idWays;
	}

	public void setIdWays(int idWays) {
		this.idWays = idWays;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}