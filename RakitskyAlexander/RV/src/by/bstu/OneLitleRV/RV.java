package by.bstu.OneLitleRV;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @param cashier Cashier who serve cash desk
 * @param passenger Passenger who wants buy the ticket
 * @param administrator Meneger who setting schedule train
 * @param listTrain array of train
 */
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
		this.passenger.getReqestion().setListTrainReq(listTrain);
		this.passenger.setReqestion();
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
		setPassenger(passenger);
	}
	/**
	 * Enter in monitor check 
	 */
	public void getCheck()
	{
		TrainPassengerGo trainPasGo=getPassenger().getTrainGo();
		if (passenger.getMoney()>trainPasGo.getMoneyFare())
		{
			if (trainPasGo.getOccupiedPlaces()<trainPasGo.getMaxQuantityPlaces())
			{
				trainPasGo.addOccupiedPlaces();
				Calendar date=passenger.getGoDate();
				System.out.println("Check\nFor: "+getPassenger().getFullNume()+"\nTrain "+trainPasGo.getStoping().get(0).getNameStation()+'-'+trainPasGo.getStoping().get(trainPasGo.getStoping().size()-1).getNameStation()
						+"\nTrain nuber: "+trainPasGo.getName()
						+" Place:"+trainPasGo.getOccupiedPlaces()
						+" Trip date:"+date.get(Calendar.DAY_OF_MONTH)+'.'+date.get(Calendar.MONTH)+'.'+date.get(Calendar.YEAR)+"\nDeparture station->"+passenger.getStationIn()+" Arrival station->"+passenger.getStationOn()+
						"\nDeparture time:"+trainPasGo.getStopingDeparture().getTimeOutput().get(Calendar.HOUR_OF_DAY)+':'+trainPasGo.getStopingDeparture().getTimeOutput().get(Calendar.MINUTE)
						+"\tArrival time"+trainPasGo.getStopingArrival().getTimeInput().get(Calendar.HOUR_OF_DAY)+':'+trainPasGo.getStopingArrival().getTimeInput().get(Calendar.MINUTE)
						+"\nCost->"+trainPasGo.getMoneyFare()
						+"\nCashier:"+cashier.getIdNomber());
				passenger.setMoney(passenger.getMoney()-trainPasGo.getMoneyFare());
			}
			else
				System.out.println("No available spaces");
		}
		else
			System.out.println("You don't have money");
	}
	/**
	 * main function
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Administrator vasia=new Administrator("Vasia");
		Calendar date=new GregorianCalendar();
		date.set(2011, 03, 30);
		Passenger petia=new Passenger("Petr","Vasil'evich",10000,"Kaluga","Minsk",date);
		Cashier galia=new Cashier(5,"Galina","Sikorkina");
		RV litleRv=new RV(galia,petia,vasia);
		litleRv.getCheck();
	}
}
