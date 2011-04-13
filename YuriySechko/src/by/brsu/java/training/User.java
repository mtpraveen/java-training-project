package by.brsu.java.training;

import java.util.ArrayList;

/**
 * Class for modeling user in phone system 
 * @author yura
 * 
 */
public class User {
    private static int nextId = 1;
	
	private int id;
    private UserStatus status;
    private long phoneNumber;
    private long balance;
    private ArrayList<Service> services;

    
	/**
     * Creates user with specified properties
     * @param phoneNumber Phone number for new user
     * @param status User's status
     * @param balance Starting balance
     * @param totalMinutes Starting total spent minutes
     * @param avaibleServices Services, witch will be available for user 
     */
    public User(long phoneNumber, UserStatus status, long balance,
    			ArrayList<Service> avaibleServices) {
		super();
		this.status = status;
		this.phoneNumber = phoneNumber;
		this.balance = balance;
		this.services = avaibleServices;
		this.id = User.getNextId();
	}

    /**
     * Returns user's phone number
     */
	public long getPhoneNumber(){
        return this.phoneNumber;
    }

	/**
	 * Sets new user's phone number
	 * @param newPhoneNumber New phone number
	 */
    public void setPhoneNumber(long newPhoneNumber){
    	this.phoneNumber = newPhoneNumber;
    }

    /**
     * Returns user's id
     */
    public int getId(){
        return this.id;
    }

    /**
     * Returns user's status
     */
    public UserStatus getStatus(){
        return this.status;
    }

    /**
     * Returns user's balance
     */
    public long getBalance() {
    	return this.balance;
    }
    
    /**
     * Changes user's status for specified
     * @param newStatus New user's status
     */
    public void setStatus(UserStatus newStatus){
    	this.status = newStatus;
    }

    /**
     * Sets user's balance
     */
    public void setBalance(long newBalance){
    	this.balance = newBalance;
    }

    /**
     * Adds specified services for user
     */
    public void addServices(ArrayList<Service> services){
    	this.services = services;
    }

    /**
     * Deletes all user's services
     */ 
    public void deleteServices(){
    	this.services = null;
    }

    /**
     * Returns services witch available for user
     */
    public ArrayList<Service> getServices(){
        return this.services;
    }

    /**
     * Returns next available id for new user
     */
    private static int getNextId(){
        return nextId++;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", status=" + status + ", phoneNumber="
				+ phoneNumber + ", balance=" + balance + ", availableServices="
				+ services + "]";
	}
}
