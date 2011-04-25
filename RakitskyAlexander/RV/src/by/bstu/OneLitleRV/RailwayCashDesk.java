package by.bstu.OneLitleRV;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Formatter;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @param cashier Cashier who serve cash desk
 * @param passenger Passenger who wants buy the ticket
 * @param administrator Manager who setting schedule train
 * @param listTrain array of train
 */
public class RailwayCashDesk {
	private Cashier cashier;
	private Passenger passenger;
	private Administrator administrator;
	private static List<Train> listTrain;
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
		this.passenger.getRequisition().setListTrainReq(listTrain);
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
	
	public RailwayCashDesk() {
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
	public RailwayCashDesk(Cashier cashier, Passenger passenger, Administrator administrator) {
		this();
		this.cashier = cashier;
//		this.passenger = passenger;
		this.administrator = administrator;
		listTrain=administrator.getListTrainAdmin();
		setPassenger(passenger);
	}
	public int getMaxQuantityPlacesInTrain(int numberTrainInList,int numberStopingDeparture,int numberStopingArrival)
	{
		int maxQuantityPlaces=0;
		Train train=listTrain.get(numberTrainInList);
//		System.out.println("Name train -gMQPIT"+train.getName());
		List<Stoping> arraySpoping=train.getStoping();  
		for(int i=numberStopingDeparture;i<numberStopingArrival;i++)
		{
			Stoping stoping=arraySpoping.get(i);
			if (train.getMaxQuantityPlaces()<=stoping.getQuantityOccupiedPlaces())
				return -1;
			if (maxQuantityPlaces<=stoping.getQuantityOccupiedPlaces())
			{
				maxQuantityPlaces=stoping.getQuantityOccupiedPlaces()+1;
//				System.out.println("This is getMaxQP"+stoping.getQuantityOccupiedPlaces());
			}
		}
		return maxQuantityPlaces;
	}
	public void addOccupiedPlaces(int numberTrainInList,int numberStopingDeparture,int numberStopingArrival)
	{
			for(int i=numberStopingDeparture;i<=numberStopingArrival;i++)
			{
				listTrain.get(numberTrainInList).getStoping().get(i).addQuantityOccupiedPlaces();
			}
	}
	/**
	 * Enter in monitor check 
	 */
	public void getCheck()
	{
		TrainPassengerGo trainPasGo=getPassenger().getTrainGo();
		if (passenger.getMoney()>trainPasGo.getMoneyFare())
		{
			int numberTrainInList=-1;
			Formatter form=new Formatter();
			int numberStopingDeparture=trainPasGo.getStoping().indexOf(trainPasGo.getStopingDeparture());
			int numberStopingArrival=trainPasGo.getStoping().indexOf(trainPasGo.getStopingArrival());
			for (Train trainInList:listTrain)
				if (trainPasGo.getIdName()==trainInList.getIdName())
					numberTrainInList=listTrain.indexOf(trainInList);
			int place=getMaxQuantityPlacesInTrain(numberTrainInList, numberStopingDeparture, numberStopingArrival);
			Formatter tripleDate=new Formatter().format("%tD", passenger.getGoDate());
			Formatter departureTime=new Formatter().format("%tR", trainPasGo.getStopingDeparture().getTimeArrival());
			Formatter arrivalTime=new Formatter().format("%tR", trainPasGo.getStopingArrival().getTimeDeparture());
			if (trainPasGo.getOccupiedPlaces()<trainPasGo.getMaxQuantityPlaces())
			{
				addOccupiedPlaces(numberTrainInList, numberStopingDeparture, numberStopingArrival);
				Calendar date=passenger.getGoDate();
				System.out.println("Check\nFor: "+getPassenger().getFullNume()+"\nTrain "+trainPasGo.getName()
						+"\nTrain nuber: "+trainPasGo.getName()
						+" Place:"+place
						+" Trip date: "+tripleDate
						+"\nDeparture time:"+departureTime+"\tArrival time"+arrivalTime
						+"\nCost->"+trainPasGo.getMoneyFare()
						+"\nCashier:"+cashier.getIdNumber());
				passenger.setMoney(passenger.getMoney()-trainPasGo.getMoneyFare());
				System.out.println("NUMBER DEP-"+numberStopingDeparture+"NUMBER ARR"+numberStopingArrival);
			}
			else
				System.out.println("No available spaces");
		}
		else
			System.out.println("You don't have money");
	}
	public static void GetReport()
	{
		String name="Report.csv";
		File fileWrite=new File(name);
		FileWriter fw;
		try {
			fw = new FileWriter(fileWrite,false);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			for(Train train:listTrain)
				if(train.getGoDayOfWeek(Calendar.DAY_OF_WEEK))
				{
					pw.println("Train: "+train.getName());
//					System.out.println(train.getName());
					for(Stoping stoping:listTrain.get(listTrain.indexOf(train)).getStoping())
						pw.print(stoping.getNameStation()+";");
					pw.println("");
					for(Stoping stoping:listTrain.get(listTrain.indexOf(train)).getStoping())
						pw.print(stoping.getQuantityOccupiedPlaces()+";");
					pw.println("");
				}
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error writing in file");
			e.printStackTrace();
		}

		

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
		Passenger petia=new Passenger("Petr","Ivanyuk",50000,"Minsk","Brest",date);
		Cashier galia=new Cashier(5,"Galina","Sikorkina");
		RailwayCashDesk litleRv=new RailwayCashDesk(galia,petia,vasia);
		litleRv.getCheck();
		Passenger oleg=new Passenger("Oleg","Gonchar",17500,"Minsk","Brest",date);
		litleRv.setPassenger(oleg);
		litleRv.getCheck();
		litleRv.GetReport();
	}
	
}
