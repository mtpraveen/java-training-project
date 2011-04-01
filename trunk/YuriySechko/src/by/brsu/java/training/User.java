package by.brsu.java.training;

import java.util.Arrays;

/**
 * Class for modeling user in phone system 
 * @author yura
 * 
 */
public class User {
    private static int nextId = 0;
	
	private int id;
    private UserStatus status;
    private long phoneNumber;
    private int balance;
    private Service[] avaibleServices;
    private int totalMinutes;
    
    /**
     * Creates user with specified properties
     * @param phoneNumber Phone number for new user
     * @param status User's status
     * @param balance Starting balance
     * @param totalMinutes Starting total spent minutes
     * @param avaibleServices Services, witch will be available for user 
     */
    public User(long phoneNumber, UserStatus status, int balance,
				int totalMinutes, Service[] avaibleServices) {
		super();
		this.status = status;
		this.phoneNumber = phoneNumber;
		this.balance = balance;
		this.avaibleServices = avaibleServices;
		this.totalMinutes = totalMinutes;
		this.id = User.nextId;
		User.nextId++;
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
    public int getBalance() {
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
    public void setBalance(int newBalance){
    	this.balance = newBalance;
    }

    /**
     * Gets total spent minutes
     */
    public int getTotalMinutes(){
        return this.totalMinutes;
    }

    /**
     * Sets total spent minutes
     */
    public void setTotalMinutes(int newTotalMinutes){
    	this.totalMinutes = newTotalMinutes;
    }

    /**
     * Adds specified services for user
     */
    public void addServices(Service[] services){
    	this.avaibleServices = services.clone();
    }

    /**
     * Deletes all user's services
     */ 
    public void deleteServices(){
    	this.avaibleServices = null;
    }

    /**
     * Returns services witch available for user
     */
    public Service[] getServices(){
        return this.avaibleServices.clone();
    }

    /**
     * Returns next available id for new user
     */
    public static int getNextId(){
        return nextId;
    }

	
    @Override
	public String toString() {
		return "User [id=" + id + ", status=" + status + ", phoneNumber="
				+ phoneNumber + ", balance=" + balance + ", totalMinutes="
				+ totalMinutes + ", avaibleServices="	+ Arrays.toString(avaibleServices) + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(avaibleServices);
		result = prime * result + balance;
		result = prime * result + id;
		result = prime * result + (int) (phoneNumber ^ (phoneNumber >>> 32));
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + totalMinutes;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (!Arrays.equals(avaibleServices, other.avaibleServices))
			return false;
		if (balance != other.balance)
			return false;
		if (id != other.id)
			return false;
		if (phoneNumber != other.phoneNumber)
			return false;
		if (status != other.status)
			return false;
		if (totalMinutes != other.totalMinutes)
			return false;
		return true;
	}

    
}
