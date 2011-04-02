/**
 * 
 */
package by.bstu.OneLitleRV;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ArrayList;

/**
 * @author Администратор
 *
 */
public class Reqestion {
	private List<TrainPassengerGo> seachResultTrain=new ArrayList<TrainPassengerGo>();
	private List<Train> listTrainReq;
	private Passenger passenger;
	/**
	 * @return the listTrainReq
	 */
	public List<Train> getListTrainReq() {
		return listTrainReq;
	}
	/**
	 * @param listTrainReq the listTrainReq to set
	 */
	public void setListTrainReq(List<Train> listTrainReq) {
		this.listTrainReq = listTrainReq;
	}
	/**
	 * @return the spisTrain
	 */
	/**
	 * @param passengerStationIn the passengerStationIn to set
	 */

	private void seachTrain()
	{
	//	System.out.println(getListTrainReq().get(0).seachStation(passenger.getStationOn()));
	//	System.out.println(getListTrainReq().get(0).seachStation(passenger.getStationIn()));
		for (int i=0;i<getListTrainReq().size();i++)
			if(getListTrainReq().get(i).searchStation(passenger.getStationOn())!=-1)
				if(getListTrainReq().get(i).searchStation(passenger.getStationIn())!=-1)
					if (getListTrainReq().get(i).getGoDayOfWeek(passenger.getGoDate().get(Calendar.DAY_OF_WEEK)))
					{
						TrainPassengerGo addTrainGo= new TrainPassengerGo();
						Train train=getListTrainReq().get(i);
						addTrainGo.setCostKm(train.getCostKm());
						addTrainGo.setGoDayOfWeek(train.getGoDayOfWeek());
						addTrainGo.setIdName(train.getIdName());
						addTrainGo.setName(train.getName());
						addTrainGo.setOccupiedPlaces(train.getOccupiedPlaces());
						addTrainGo.setStoping(train.getStoping());
						addTrainGo.setMaxQuantityPlaces(train.getMaxQuantityPlaces());
						addTrainGo.setStopingArrival(passenger.getStationIn());
						addTrainGo.setStopingDeparture(passenger.getStationOn());
						seachResultTrain.add(addTrainGo);
					}
}
	public TrainPassengerGo EnterResultSeach() 
	{
		if (listTrainReq.size()==0)
		{
			System.out.println("Train is not initializetion");
			return null;
		}
		seachTrain();
		TrainPassengerGo chooseTrain;
		for(int i=0;i<seachResultTrain.size();i++)
		{
			chooseTrain=seachResultTrain.get(i);
			Stoping stopingIn=chooseTrain.getStopingDeparture();
			Stoping stopingOn=chooseTrain.getStopingArrival();
			System.out.println("Namber ->"+(i+1));
			System.out.println("Name ->"+chooseTrain.getName());
			System.out.println("Time Output->"+stopingOn.getTimeOutput().get(Calendar.HOUR_OF_DAY)+"h "+stopingOn.getTimeOutput().get(Calendar.MINUTE)+"m");
			System.out.println("Time Input->"+stopingIn.getTimeInput().get(Calendar.HOUR_OF_DAY)+"h "+stopingIn.getTimeInput().get(Calendar.MINUTE)+"m");
			System.out.println("Cost->"+Math.round(chooseTrain.getMoneyFare())+"BYB");
		}
		if (seachResultTrain.size()!=0)
		{
			BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Choose please nomber train");
			int choose = 0;
			try {
				choose = Integer.parseInt(r.readLine());
				choose--;
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (choose<=getListTrainReq().size())
				return seachResultTrain.get(choose);
			else 
				return null;
		}
		else
		{
			System.out.println("In this day in this station goes nothing ");
			return null;
		}
	}

	/**
	 * @param passenger
	 */
	public Reqestion(Passenger passenger) {
		super();
		this.passenger = passenger;
	}

	
	

}
