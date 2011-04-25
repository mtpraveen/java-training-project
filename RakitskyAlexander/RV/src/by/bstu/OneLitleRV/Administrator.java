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
		Train tr1=new Train(1);
		Train tr2=new Train(2);
		Train tr3=new Train(3);
		tr1.addStation(new Stoping("Grodno",0,14,00,14,15));
		tr1.addStation(new Stoping("Minsk",400,17,00,17,15));
		tr1.addStation(new Stoping("Pinsk",500,20,00,20,15));
		tr1.addStation(new Stoping("Brest",800,23,00,19,15));
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
		tr2.addStation(new Stoping("Grodno",0,14,00,14,15));
		tr2.addStation(new Stoping("Minsk",400,19,00,19,15));
		tr2.addStation(new Stoping("Brest",800,23,00,23,15));
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
		tr3.addStation(new Stoping("Grodno",0,21,00,21,15));
		tr3.addStation(new Stoping("Minsk",400,19,00,19,15));
		tr3.addStation(new Stoping("Pinsk",500,23,00,23,15));
		tr3.addStation(new Stoping("Brest",800,17,00,17,15));
		tr3.addStation(new Stoping("Varshava",1000,17,00,17,15));
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

		listTrainAdmin=ListTrainNew;
	}

	/**
	 * @param listTrainAdmin the listTrainAdmin to set
	 */
	public void setListTrainAdmin(List<Train> listTrainAdmin) {
		this.listTrainAdmin = listTrainAdmin;
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
	


