package by.brsu.java.training;

import java.util.HashMap;

/**
 * Class for modeling automatic phone system
 * @author yura
 *
 */
public class PhoneSystem {
    public static final int DOLLARS_PER_MINUTE = 1;
	private static long nextPhoneNumber = 1024;
	private static PhoneSystem instance = new PhoneSystem(5); 
    public static Service[] AvailableServices;
    
    static {
    	Service service1 = new Service(1);
        Service service2 = new Service(2);
        Service service3 = new Service(3);
        Service[] services = {service1, service2, service3};
        AvailableServices = services;
    }
	
    /**
     * Returns instance of PhoneSystem singleton
     * @return reference to unique PhoneSystem instance
     */
	public static PhoneSystem getInstance(){
    	return instance;
    }
	
	/**
	 * Returns next possibly available phone number in system
	 * @return Next possibly available phone number
	 */
	private static long getNextPhoneNumber() {
		return nextPhoneNumber++;
	}
	
    private HashMap<Integer, User> usersList = new HashMap<Integer, User>();
    private Administrator administrator = new Administrator();
    
    /**
     * Creates phone system with <code>userCount</code> users and 1 administrator
     * @param userCount Specifies total user count
     */
    private PhoneSystem(int userCount) { 
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
    	User user = getUser(userId);
    	
    	if ( user == null )
    		return null;
    	
        Payment payment = new Payment(user.getTotalMinutes() * PhoneSystem.DOLLARS_PER_MINUTE);
        return payment;
    }

	/**
	 * Automatically changes user's balance and minutes
	 * @param userId User's id
	 * @param howMoney How much money must be payed
	 * @return <code>true</code> if user with specified id is exist and have sufficient balance, otherwise <code>false</code>
	 */
    public boolean pay(int userId, int howMoney){
    	User user = getUser(userId);
    	
    	if ( user == null )
    		return false;
    	
    	if ( howMoney <= 0 )
    		return false;
    		
    	int balance = user.getBalance();
    	int minutes = user.getTotalMinutes();
    	
    	if ( (balance - howMoney) < 0 )
    		return false;
    	
    	user.setBalance(balance - howMoney);
    	user.setTotalMinutes( minutes - howMoney / PhoneSystem.DOLLARS_PER_MINUTE );
    	
        return true;
    }

    /**
     * Deletes services for specified user
     * @param userId User's id
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
    public int[] getUsersIds() {
    	int[] idArray = new int[usersList.size()];
    	Object[] users = usersList.values().toArray();
    	for(int i = 0; i < users.length; i++) {
    		idArray[i] = ((User)users[i]).getId();
    	}
    	return idArray;
    }
   
    /**
     * Check phone number availability
     * @param phoneNumber Phone number to check
     * @return <code>true</code> if specified phone number is available
     */
    public boolean isPhoneNumberAvailable(long phoneNumber){
    	Object[] users = usersList.values().toArray();
    	
    	for(Object user : users ) {
    		if ( ((User)user).getPhoneNumber() == phoneNumber)
    			return false;
    	}
    	
        return true;
    }

    /**
     * Returns array witch contains all available services
     */
    public static Service[] getAvailableServices(){
        return AvailableServices;
    }

    /**
     * Creates new random user in system
     * @return <code>false</code> if there are not available phone numbers
     */
    public boolean createRandomUser(){
    	// create ne user with random properties
    	java.util.Random randGenerator = new java.util.Random();
		User user = new User(getNextPhoneNumber(), 
							UserStatus.NORMAL, 
							randGenerator.nextInt() % 100 + 100,
							randGenerator.nextInt() % 100 + 100,
							getAvailableServices());
		long curPhoneNumber = user.getPhoneNumber();
		
		// checking phone number, if phone number is exist, take previous + 1
		while ( !this.isPhoneNumberAvailable( user.getPhoneNumber() )  ) {
			curPhoneNumber = getNextPhoneNumber();
			user.setPhoneNumber(curPhoneNumber);
			if ( curPhoneNumber > 999999)
				return false;
		}
		
		// add new user in list of users
		usersList.put( user.getId(), user );
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
    	return this.usersList.get(userId);	
    }
        
    @Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("PhoneSystem \n[\n");
		
    	Object[] users = usersList.values().toArray();
    	
    	for(Object user : users ) {
    		User curUser = (User)user;
    		strBuf.append(curUser.toString()).append("\n");	
    	}
    	
    	strBuf.append(administrator.toString());
    	
    	strBuf.append("\n]");
    	return strBuf.toString();
	}
    
    public static void main(String[] args) {
    	
    	    	
    	int id = 0;
    	PhoneSystem  ps = PhoneSystem.getInstance();
    	
    	System.out.println( ps.toString() );
    	
    	Payment payment = ps.getPayment(id);
    	System.out.println(payment);
    	
    	ps.pay(id, 150);
    	System.out.println(ps.getUser(id));
    	
    	ps.getAdministrator().requestChangeUserPhoneNumber(id, 2070666);
    	System.out.println(ps.getUser(id));
    	
    	ps.getAdministrator().requestChangeUserPhoneNumber(id, 1025);
    	System.out.println(ps.getUser(id));
 
    	System.out.println( ps.getAdministrator().requestDeleteUserServices(id) );
    	
    	//System.out.println(ps.getAvailableServices());
    	
    	System.out.println( ps.toString() );
    	
    }
    
    
}
