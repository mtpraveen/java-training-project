package by.bstu.OneLitleRV;
/**
 * 
 * @author Rakitsky Alexander
 * @param name - Name of cashier
 * @param surName - Surname of cashier
 * 
 */
public class Cashier {
  private int idNumber;
  private String name;
  private String surname;
/**
 * @return the idNumber
 */
public int getIdNumber() {
	return idNumber;
}
/**
 * @param idNomber the idNomber to set
 */
public void setIdNumber(int idNomber) {
	this.idNumber = idNomber;
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
public String getSurname() {
	return surname;
}
/**
 * @param surname the surName to set
 */
public void setSurname(String surname) {
	this.surname = surname;
}
/**
 * @param idNumber
 * @param name
 * @param surname
 */
public Cashier(int idNumber, String name, String surname) {
	super();
	this.idNumber = idNumber;
	this.name = name;
	this.surname = surname;
}
  
}
