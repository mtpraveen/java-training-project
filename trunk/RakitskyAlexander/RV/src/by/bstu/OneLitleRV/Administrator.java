/**
 * 
 */
package by.bstu.OneLitleRV;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Администратор
 *
 */
/**
 * The adminstrarot establishes the list of trains
 * @param name Name of administrator
 * @param listTrainAdmin The list of trains established by the manager
 */
public class Administrator{
	
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
	/**
	 * The list of trains is established
	 */
	private void setListTrain() {
		List<Train> ListTrainNew=new ArrayList<Train>();
		Train tr1=new Train(5);
		Train tr2=new Train(7);
		Train tr3=new Train(5);
		Stoping kaluga=new Stoping("Kaluga",1200,14,00,14,15);;
		Stoping brest=new Stoping("Brest",1,17,00,17,15);	
		Stoping minsk=new Stoping("Minsk",1300,19,00,19,15);	
		Stoping grodno=new Stoping("Grodno",1700,21,00,21,15);		
		Stoping pinsk=new Stoping("Pinsk",1500,23,00,23,15);	
		tr1.addStation(kaluga);
		tr1.addStation(brest);
		tr1.addStation(minsk);
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
		tr2.addStation(kaluga);
		tr2.addStation(minsk);
		tr2.addStation(pinsk);
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
		tr3.addStation(kaluga);
		tr3.addStation(minsk);
		tr3.addStation(brest);
		tr3.addStation(grodno);
		tr3.addStation(pinsk);
		tr3.setGoDayOfWeek(1, true);
		tr3.setGoDayOfWeek(2, false);
		tr3.setGoDayOfWeek(3, false);
		tr3.setGoDayOfWeek(4, false);
		tr3.setGoDayOfWeek(5, false);
		tr3.setGoDayOfWeek(6, false);
		tr3.setGoDayOfWeek(7, false);
		tr3.setCostKm(50);
		tr3.setMaxQuantityPlaces(100);
		ListTrainNew.add(tr3);

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
	


