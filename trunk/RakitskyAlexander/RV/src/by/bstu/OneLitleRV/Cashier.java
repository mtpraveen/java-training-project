package by.bstu.OneLitleRV;
/**
 * 
 * @author Rakitsky Alexander
 * @param name - Name of cashier
 * @param surName - Surname of cashier
 * 
 */
public class Cashier {
  private int idNomber;
  private String name;
  private String surName;
/**
 * @return the idNomber
 */
public int getIdNomber() {
	return idNomber;
}
/**
 * @param idNomber the idNomber to set
 */
public void setIdNomber(int idNomber) {
	this.idNomber = idNomber;
}
/**
 * @return the name
 */
public String getName() {
	return name;
}
/**
 * @param name the name to set
 */
public void setName(String name) {
	this.name = name;
}
/**
 * @return the surName
 */
public String getSurName() {
	return surName;
}
/**
 * @param surName the surName to set
 */
public void setSurName(String surName) {
	this.surName = surName;
}
/**
 * @param idNomber
 * @param name
 * @param surName
 */
public Cashier(int idNomber, String name, String surName) {
	super();
	this.idNomber = idNomber;
	this.name = name;
	this.surName = surName;
}
  
}
