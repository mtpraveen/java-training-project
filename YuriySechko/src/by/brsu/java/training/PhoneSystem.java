package by.brsu.java.training;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

/**
 * Class for modeling automatic phone system
 * @author yura
 *
 */


public class PhoneSystem {
	private static long nextPhoneNumber = 1024;
	private static PhoneSystem instance = new PhoneSystem(DBMode.SIMPLE); 
	
	/**
     * Returns instance of PhoneSystem singleton
     * @return reference to unique PhoneSystem instance
     */
	public static PhoneSystem getInstance(){
    	return instance;
    }
	
	private ISystemDB database;
	private Administrator administrator = new Administrator();
	
	/**
	 * Returns next possibly available phone number in system
	 * @return Next possibly available phone number
	 */
	private static long getNextPhoneNumber() {
		return nextPhoneNumber++;
	}
	
    /**
     * Creates phone system with <code>userCount</code> users and 1 administrator
     * @param userCount Specifies total user count
     */
    private PhoneSystem(DBMode mode) { 
    	switch ( mode ) {
    	case SIMPLE:
    		database = new SimpleDB();
    		break;
    	}
    }
    
    private void AddUsers(int userCount) {
    	for(int i = 0; i < userCount; i++) {
    		this.createRandomUser();
    	}
    }
    
    /**
     * Returns payment for user with specified id
     * @param userId User's id
     * @return Payment for user's minutes and services if user is exist, otherwise <code>null</code>
     */
	public Payment getPayment(int userId){
    	if ( !database.getUsers().containsKey(userId) )
    		return null;

    	User user = getUser(userId);
    	
    	if ( user == null )
    		return null;

    	Payment lastPayment = getLastPayment(userId);
    	long totalCost = 0;
    	Date curDate = new Date(System.currentTimeMillis());

    	
    	if ( lastPayment != null)
    		if ( curDate.getMonth() == lastPayment.getDate().getMonth() ) 
    			return null;
    		
    	if ( user.getServices() != null ) {
    		for( Service service : user.getServices() ) {
    			totalCost += service.getCost();
	    	}
    	}
    	
    	if ( totalCost == 0 ) 
    		return null;
    	    	
    	Payment payment = new Payment(totalCost, curDate);
        return payment;
	}

	private Payment getLastPayment(int userId) {
    	if ( !database.getUsers().containsKey(userId) )
    		return null;
		
		ArrayList<Payment> userPayments = database.getPayments(userId);
    	Collections.sort(userPayments, new Comparator<Payment>() {
    										public int compare(Payment o1, Payment o2) {
    										return o1.getDate().compareTo(o2.getDate());
    										}
    								});
		
    	Payment lastPayment = userPayments.size() > 0 ? 
    							userPayments.get(userPayments.size() - 1) :
    							null;
    	return lastPayment;
	}

	/**
	 * Automatically changes user's balance and minutes
	 * @param userId User's id
	 * @param howMoney How much money must be payed
	 * @return <code>true</code> if user with specified id is exist and have sufficient balance, otherwise <code>false</code>
	 */
    public boolean pay(int userId, Payment payment){
    	if ( !database.getUsers().containsKey(userId) )
    		return false;

    	User user = getUser(userId);
    	
    	if ( user == null )
    		return false;
    	
    	Date curDate = new Date(System.currentTimeMillis());
    	Payment lastPayment = getLastPayment(userId);
    	
    	if ( lastPayment != null)
    		if ( curDate.getMonth() == lastPayment.getDate().getMonth() ) 
    			return false;
    	long totalCost = payment.getCost();
    	if ( totalCost <= 0 )
    		return false;
    	
    	long balance = user.getBalance();    	
    	if ( (balance - totalCost) < 0 )
    		return false;
    	
    	user.setBalance(balance - totalCost);
    	database.addPayment(payment);

    	return true;
    }

    /**
     * Deletes services for specified user
     * @param userId User's ids
     */
    public void deleteUserServices(int userId){
    	User user = getUser(userId);
       	user.deleteServices();
    }

    /**
     * Sets new phone number for specified user
     * @param userId User's id
     * @param newPhoneNumber New phone number
     */
    public void setUserPhoneNumber(int userId, long newPhoneNumber){
        User user = getUser(userId);
        user.setPhoneNumber(newPhoneNumber);
    }
    
    /**
     * Returns array witch contains id's for all users in system
     */
    public ArrayList<Integer> getUsersIds() {
    	ArrayList<Integer> idArray = new ArrayList<Integer>(database.getUsers().size());
    	
    	int i = 0;
    	for(Integer	id : (Integer[])(database.getUsers().keySet().toArray()) ) {
    		idArray.set(i, id);
    		i++;
    	}
    	return idArray;
    }
   
    /**
     * Check phone number availability
     * @param phoneNumber Phone number to check
     * @return <code>true</code> if specified phone number is available
     */
    public boolean isPhoneNumberAvailable(long phoneNumber){
    	TreeMap<Integer, User> users = database.getUsers();
    	Set<Integer> idSet = users.keySet();
    	for(Integer userId : idSet ) {
    		if ( users.get(userId).getPhoneNumber() == phoneNumber)
    			return false;
    	}
        return true;
    }

    /**
     * Returns array witch contains all available services
     */
    public ArrayList<Service> getAvailableServices(){
        return database.getAvailableServices();
    }

    /**
     * Creates new random user in system
     * @return <code>false</code> if there are not available phone numbers
     */
    public boolean createRandomUser(){
    	// create new user with random properties
    	java.util.Random randGenerator = new java.util.Random();
    	User user = new User(getNextPhoneNumber(), 
							UserStatus.NORMAL, 
							randGenerator.nextInt() % 100 + 100,
							database.getAvailableServices());
		long curPhoneNumber = user.getPhoneNumber();
		
		// checking phone number, if phone number is exist, take previous + 1
		while ( !this.isPhoneNumberAvailable( user.getPhoneNumber() )  ) {
			curPhoneNumber = getNextPhoneNumber();
			user.setPhoneNumber(curPhoneNumber);
			if ( curPhoneNumber > 999999)
				return false;
		}
		
		// add new user in list of users
		database.addUser(user);
		return true;	
    }

    /**
     * Returns current administrator
     * @return
     */
    public Administrator getAdministrator(){
        return administrator;
    }

    /**
     * Sets new status for specified user
     * @param userId User's id
     * @param newStatus New status
     */
    public void changeUserStatus(int userId, UserStatus newStatus) {
    	getUser(userId).setStatus(newStatus);	
    }
    
    /**
     * Returns reference to user with specified id 
     * @param userId User's id
     * @return <code>null</code> if users with specified id is not exist 
     */
    public User getUser(int userId) {
    	return database.getUser(userId);	
    }
        
    @Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("PhoneSystem \n[\n");
		
    	ArrayList<User> users = new ArrayList<User>(database.getUsers().values());
    	
    	for(User user : users ) {
    		strBuf.append(user.toString()).append("\n");	
    	}
    	
    	strBuf.append(administrator.toString());
       	strBuf.append("\n]");
    	
    	return strBuf.toString();
	}
    
    public static void main(String[] args) {
      	int id = 1;
    	PhoneSystem  ps = PhoneSystem.getInstance();
    	ps.AddUsers(2);
    	
    	System.out.println( ps.toString() );
    	
    	Payment payment = ps.getPayment(id);
    	System.out.println(payment);
    	
    	ps.pay(id, payment);
    	System.out.println(ps.getUser(id));

    	ps.pay(id, payment);
    	System.out.println(ps.getUser(id));
    	
    	ps.getAdministrator().requestChangeUserPhoneNumber(id, 2070666);
    	System.out.println(ps.getUser(id));
    	
    	ps.getAdministrator().requestChangeUserPhoneNumber(id, 1025);
    	System.out.println(ps.getUser(id));
 
    	System.out.println( ps.getAdministrator().requestDeleteUserServices(id) );
    	
    	System.out.println(ps.getAvailableServices());
    	
    	System.out.println( ps.toString() );
    	
    	payment = ps.getPayment(id);
    	System.out.println(payment);
    	
    	ps.pay(id, payment);
    	System.out.println(ps.getUser(id));
    	
    	System.out.println( ps.toString() );
    }
}
