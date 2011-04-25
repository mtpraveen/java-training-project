/**
 * 
 */
package by.bstu.OneLitleRV;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Formatter;
import java.util.List;

/**
 * @author Администратор
 *
 */
/**
 * @param seachResultTrain The array in which to be stored a search result of trains satisfying to the demand 
 * @param listTainReq The array of train
 * @param passenger Parameters of the passenger
 */
public class Requisition {
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
	 * The list of trains satisfying to inquiry is formed
	 */

	public void seachTrain()
	{
		for (int i=0;i<getListTrainReq().size();i++)
		{
			Train train=getListTrainReq().get(i);
			int numberStationDeparture=train.searchStation(passenger.getStopingDeparture());
			int numberStationArrival=train.searchStation(passenger.getStopingArrival());
			if(numberStationDeparture!=-1)
				if(numberStationArrival!=-1)
					if (train.getGoDayOfWeek(passenger.getGoDate().get(Calendar.DAY_OF_WEEK)) && numberStationDeparture<numberStationArrival)
					{
						TrainPassengerGo addTrainGo= new TrainPassengerGo(train,passenger.getStopingDeparture(),passenger.getStopingArrival());
						seachResultTrain.add(addTrainGo);
					}		
		}
}
	/**
	 * @return the seachResultTrain
	 */
	public List<TrainPassengerGo> getSeachResultTrain() {
		return seachResultTrain;
	}
	/**
	 * Deduces the list of trains satisfying to inquiry and offers for a choice to the passenger of a train for movement
	 * @return The train on which will go the passenger
	 */
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
			Formatter formArrival=new Formatter();
			Formatter formDeparture=new Formatter();
			chooseTrain=seachResultTrain.get(i);;
			formDeparture.format("%tR", chooseTrain.getStopingDeparture().getTimeArrival());
			formArrival.format("%tR",chooseTrain.getStopingArrival().getTimeDeparture() );
			System.out.println("Namber ->"+(i+1));
			System.out.println("Name ->"+chooseTrain.getName());
			System.out.println("Time Output->"+formDeparture);
			System.out.println("Time Input->"+formArrival);
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
	public Requisition(Passenger passenger) {
		super();
		this.passenger = passenger;
	}

	
	

}
