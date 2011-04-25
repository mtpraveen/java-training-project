package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Formatter;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;

import by.bstu.OneLitleRV.Administrator;
import by.bstu.OneLitleRV.Cashier;
import by.bstu.OneLitleRV.Passenger;
import by.bstu.OneLitleRV.RailwayCashDesk;
import by.bstu.OneLitleRV.Requisition;
import by.bstu.OneLitleRV.Stoping;
import by.bstu.OneLitleRV.Train;
import by.bstu.OneLitleRV.TrainPassengerGo;

public class testSystem {
	@Test(timeout = 5000)
	public void seachTrain() {
		List<Train> ListTrainNew = formListOfTrain();
		Calendar date = new GregorianCalendar();
		date.set(2011, 03, 30);
		Passenger passenger = new Passenger("Oleg", "Gonchar", 17500, "Minsk",
				"Brest", date);
		Requisition req = new Requisition(passenger);
		req.setListTrainReq(ListTrainNew);
		req.seachTrain();
		List<TrainPassengerGo> resultTrainList = req.getSeachResultTrain();
		assertEquals(resultTrainList.size(), 2);
		assertEquals(resultTrainList.get(0).getIdName(), 1);
		assertEquals(resultTrainList.get(1).getIdName(), 2);
		;
	}
	@Test
	public void AdminConstructor()
	{
		Administrator admin=new Administrator("vasia");
		assertEquals( admin.getName(), "vasia" );
	}
	@Test
	public void testSistem()
	{
		Administrator admin=new Administrator("Vasia");
		List<Train> ListTrainNew = formListOfTrain();
		admin.setListTrainAdmin(ListTrainNew);
		Calendar date=new GregorianCalendar();
		date.set(2011, 03, 30);
		Passenger passenger=new Passenger("Petr","Ivanyuk",50000,"Minsk","Brest",date);
		Requisition requisition= new Requisition(passenger);
		requisition.setListTrainReq(ListTrainNew);
		requisition.seachTrain();
		Cashier cashier=new Cashier(5,"Galina","Sikorkina");
		TrainPassengerGo trainPasGo = requisition.getSeachResultTrain().get(0);
		passenger.setTrainGo(trainPasGo);
		String numberStopingDeparture=""+trainPasGo.getStoping().indexOf(trainPasGo.getStopingDeparture());
		String numberStopingArrival=""+trainPasGo.getStoping().indexOf(trainPasGo.getStopingArrival());
		assertEquals( numberStopingDeparture, "1" );
		assertEquals( numberStopingArrival, "3" );
		assertEquals( trainPasGo.getIdName(), 1 );
//		Calendar timeArrival = trainPasGo.getStopingDeparture().getTimeArrival();
//		Calendar timeDeparture = trainPasGo.getStopingDeparture().getTimeDeparture();
//		Calendar timeArTest=new GregorianCalendar(0, 0, 0,23,0);
//		Calendar timeDepTest=new GregorianCalendar(0, 0, 0, 17,15);
		//assertEquals( timeArTest, timeArrival );
		//assertEquals(  timeDepTest, timeDeparture );
		
	}
	private List<Train> formListOfTrain() {
		List<Train> ListTrainNew = new ArrayList<Train>();
		Train tr1 = new Train(1);
		Train tr2 = new Train(2);
		Train tr3 = new Train(3);
		tr1.addStation(new Stoping("Grodno", 0, 14, 00, 14, 15));
		tr1.addStation(new Stoping("Minsk", 400, 17, 00, 17, 15));
		tr1.addStation(new Stoping("Pinsk", 500, 20, 00, 20, 15));
		tr1.addStation(new Stoping("Brest", 800, 23, 00, 19, 15));
		tr1.setGoDayOfWeek(1, true);
		tr1.setGoDayOfWeek(2, false);
		tr1.setGoDayOfWeek(3, true);
		tr1.setGoDayOfWeek(4, false);
		tr1.setGoDayOfWeek(5, true);
		tr1.setGoDayOfWeek(6, true);
		tr1.setGoDayOfWeek(7, true);
		tr1.setCostKm(25);
		tr1.setMaxQuantityPlaces(150);
		ListTrainNew.add(tr1);
		tr2.addStation(new Stoping("Grodno", 0, 14, 00, 14, 15));
		tr2.addStation(new Stoping("Minsk", 400, 19, 00, 19, 15));
		tr2.addStation(new Stoping("Brest", 800, 23, 00, 23, 15));
		tr2.setGoDayOfWeek(1, false);
		tr2.setGoDayOfWeek(2, false);
		tr2.setGoDayOfWeek(3, false);
		tr2.setGoDayOfWeek(4, false);
		tr2.setGoDayOfWeek(5, false);
		tr2.setGoDayOfWeek(6, true);
		tr2.setGoDayOfWeek(7, true);
		tr2.setCostKm(30);
		tr2.setMaxQuantityPlaces(170);
		ListTrainNew.add(tr2);
		tr3.addStation(new Stoping("Grodno", 0, 21, 00, 21, 15));
		tr3.addStation(new Stoping("Minsk", 400, 19, 00, 19, 15));
		tr3.addStation(new Stoping("Pinsk", 500, 23, 00, 23, 15));
		tr3.addStation(new Stoping("Brest", 800, 17, 00, 17, 15));
		tr3.addStation(new Stoping("Varshava", 1000, 17, 00, 17, 15));
		tr3.setGoDayOfWeek(1, true);
		tr3.setGoDayOfWeek(2, false);
		tr3.setGoDayOfWeek(3, false);
		tr3.setGoDayOfWeek(4, false);
		tr3.setGoDayOfWeek(5, false);
		tr3.setGoDayOfWeek(6, true);
		tr3.setGoDayOfWeek(7, false);
		tr3.setCostKm(50);
		tr3.setMaxQuantityPlaces(100);
		ListTrainNew.add(tr3);
		return ListTrainNew;
	}
}
