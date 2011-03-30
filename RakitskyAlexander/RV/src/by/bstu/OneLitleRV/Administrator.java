/**
 * 
 */
package by.bstu.OneLitleRV;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author Администратор
 *
 */
public class Administrator{
	/**
	 * @param listTrain the listTrain to set
	 */
	private String name;
	private List<Train> listTrainAdmin;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the listTrainAdim
	 */
	public List<Train> getListTrainAdmin() {
		return listTrainAdmin;
	}

	public void setListTrain() {
		List<Train> ListTrainNew=new ArrayList<Train>();
		Train tr=new Train(5);
		Train tr1=new Train(7);
		Train tr2=new Train(5);
		Calendar s=new GregorianCalendar();
		Calendar s1=new GregorianCalendar();
		s.set(0, 0, 0,14,00);
		s1.set(0, 0, 0,14,15);
		Stoping kaluga=new Stoping("Kaluga",1200,s,s1);
		s.set(0, 0, 0,17,00);
		s1.set(0, 0, 0,17,15);
		Stoping brest=new Stoping("Brest",1,s,s1);
		s.set(0, 0, 0,17,00);
		s1.set(0, 0, 0,17,15);		
		Stoping minsk=new Stoping("Minsk",1300,s,s1);
		s.set(0, 0, 0,19,00);
		s1.set(0, 0, 0,19,15);		
		Stoping grodno=new Stoping("Grodno",1700,s,s1);	
		s.set(0, 0, 0,21,00);
		s1.set(0, 0, 0,23,15);		
		Stoping pinsk=new Stoping("Pinsk",1500,s,s1);	
		tr.addStation(kaluga);
		tr.addStation(brest);
		tr.addStation(minsk);
		tr.setGoDayOfWeek(1, true);
		tr.setGoDayOfWeek(2, false);
		tr.setGoDayOfWeek(3, true);
		tr.setGoDayOfWeek(4, false);
		tr.setGoDayOfWeek(5, true);
		tr.setGoDayOfWeek(6, true);
		tr.setGoDayOfWeek(7, true);
		ListTrainNew.add(tr);
		tr1.addStation(kaluga);
		tr1.addStation(minsk);
		tr1.addStation(pinsk);
		tr1.setGoDayOfWeek(1, false);
		tr1.setGoDayOfWeek(2, false);
		tr1.setGoDayOfWeek(3, false);
		tr1.setGoDayOfWeek(4, false);
		tr1.setGoDayOfWeek(5, false);
		tr1.setGoDayOfWeek(6, true);
		tr1.setGoDayOfWeek(7, true);
		ListTrainNew.add(tr1);
		tr2.addStation(kaluga);
		tr2.addStation(minsk);
		tr2.addStation(brest);
		tr2.addStation(grodno);
		tr2.addStation(pinsk);
		tr2.setGoDayOfWeek(1, true);
		tr2.setGoDayOfWeek(2, false);
		tr2.setGoDayOfWeek(3, false);
		tr2.setGoDayOfWeek(4, false);
		tr2.setGoDayOfWeek(5, false);
		tr2.setGoDayOfWeek(6, false);
		tr2.setGoDayOfWeek(7, false);
		ListTrainNew.add(tr2);
		System.out.println("I am Admin "+ListTrainNew.size());
		listTrainAdmin=ListTrainNew;
	}

	/**
	 * 
	 */
	public Administrator(String name) {
		super();
		this.name=name;
		setListTrain();
	}
		// TODO Auto-generated constructor stub
		
	}
	


