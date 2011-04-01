package by.bstu.OneLitleRV;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class RV {
	private Cashier cashier;
	private Passenger passenger;
	private Administrator administrator;
	private List<Train> listTrain;
	/**
	 * @return the passenger
	 */
	public Passenger getPassenger() {
		return passenger;
	}
	/**
	 * @param passenger the passenger to set
	 */
	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	/**
	 * @return the listTrain
	 */
	public List<Train> getListTrain() {
		return listTrain;
	}
	/**
	 * 
	 */
	
	public RV() {
		super();
		//listTrain=new ArrayList<Train>();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param listTrain the listTrain to set
	 */
	public void setListTrain(List<Train> listTrain) {
		this.listTrain = listTrain;
	}
	/**
	 * @param cashier
	 * @param passenger
	 * @param administrator
	 */
	public RV(Cashier cashier, Passenger passenger, Administrator administrator) {
		this();
		this.cashier = cashier;
		this.passenger = passenger;
		this.administrator = administrator;
		listTrain=administrator.getListTrainAdmin();
		this.passenger.getReqestion().setListTrainReq(listTrain);
		this.passenger.setReqestion();
		System.out.println(this.passenger.getReqestion().getListTrainReq().size());
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Administrator vasia=new Administrator("Vasia");
		Calendar date=new GregorianCalendar();
		date.set(2011, 03, 30);
		Passenger petia=new Passenger("Petr","Vasil'evich",10000,"Kaluga","Minsk",date);
		Cashier galia=new Cashier(5,"Galina","Sikorkina");
		RV litleRv=new RV(galia,petia,vasia);
		//litleRv.administrator.setListTrain();
		//System.out.println(litleRv.getListTrain().size());
		//litleRv.passenger.setReqestion();
	}
}
