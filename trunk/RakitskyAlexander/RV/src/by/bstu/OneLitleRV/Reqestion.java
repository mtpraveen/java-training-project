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
	private List<Integer> seachResultTrain=new ArrayList<Integer>();
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
		System.out.println(getListTrainReq().get(0).seachStation(passenger.getStationOn()));
		System.out.println(getListTrainReq().get(0).seachStation(passenger.getStationIn()));
		for (int i=0;i<getListTrainReq().size();i++)
			if(getListTrainReq().get(i).seachStation(passenger.getStationOn())!=-1)
				if(getListTrainReq().get(i).seachStation(passenger.getStationIn())!=-1)
					if (getListTrainReq().get(i).getGoDayOfWeek(passenger.getGoDate().get(Calendar.DAY_OF_WEEK)))
					{
						seachResultTrain.add(i);
					}
}
	public Train EnterResultSeach() 
	{
		if (listTrainReq.size()==0)
		{
			System.out.println("Train is not initializetion");
			return null;
		}
		seachTrain();
		Train chooseTrain;
		Stoping stopingIn;
		Stoping stopingOn;
		for(int i=0;i<seachResultTrain.size();i++)
		{
			chooseTrain=getListTrainReq().get(i);
			stopingIn=chooseTrain.getStoping().get(chooseTrain.seachStation(passenger.getStationIn()));
			stopingOn=chooseTrain.getStoping().get(chooseTrain.seachStation(passenger.getStationOn()));
			System.out.println("Namber ->"+(i+1));
			System.out.println("Name ->"+chooseTrain.getName());
			System.out.println("Time Output->"+stopingOn.getTimeOutput().get(Calendar.HOUR_OF_DAY)+"h "+stopingOn.getTimeOutput().get(Calendar.MINUTE)+"m");
			System.out.println("Time Input->"+stopingIn.getTimeInput().get(Calendar.HOUR_OF_DAY)+"h "+stopingIn.getTimeInput().get(Calendar.MINUTE)+"m");
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
				return getListTrainReq().get(seachResultTrain.get(choose));
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
