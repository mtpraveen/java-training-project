package by.brsu.java.training;

import java.util.ArrayList;


/**
 * Class for modeling administrator in automatic phone system
 * @author yura
 * 
 */
public class Administrator {

	/**
	 * Checks all users in system and ban everyone who has adverse balance
	 */
	public void checkUsers() {
		// get instance of phone system
		PhoneSystem ps = PhoneSystem.getInstance();
		
		// check every user
		for(Integer id: ps.getUsersIds() ) {
			User user = ps.getUser(id);
			// ban user if balance is negate 
			if ( user.getBalance() < 0 )
				ps.changeUserStatus(id, UserStatus.BANNED);
		}
	}
	
	/**
	 * Changes users phone number if possible
	 * @param userId User's id
	 * @param newPhoneNumber New phone number 
	 * @return <code>true</code> if user with specified id is exist and new phone number is available, otherwise <code>false</code> 
	 */
    public boolean requestChangeUserPhoneNumber(int userId, long newPhoneNumber){
    	// get instance of phone system
    	PhoneSystem phoneSystem = PhoneSystem.getInstance(); 
    	// get user with specified id
    	User user = phoneSystem.getUser(userId);
    	
    	if ( user == null )
    		return false;
    	
    	// check new phone number
    	if ( !phoneSystem.isPhoneNumberAvailable(newPhoneNumber) )
    		return false;
    	// set new phone number
    	phoneSystem.setUserPhoneNumber(userId, newPhoneNumber);
    	return true;
    }

    /**
     * Deletes user's services
     * @param userId User's id
     * @return <code>true</code> if user with specified id is exist, otherwise <code>false</code>
     */
    public boolean requestDeleteUserServices(int userId){
    	// get instance of phone system
    	PhoneSystem phoneSystem = PhoneSystem.getInstance(); 
    	// get user with specified id
    	User user = phoneSystem.getUser(userId);
    	
    	if ( user == null ) 
    		return false;
    	
    	phoneSystem.setUserServices(userId, null);
    	return true;
    }
    
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
    
    public boolean requestChangeServices(int userId, ArrayList<Service> services) {
    	// get instance of phone system
    	PhoneSystem phoneSystem = PhoneSystem.getInstance(); 
    	// get user with specified id
    	User user = phoneSystem.getUser(userId);
    	
    	if ( user == null ) 
    		return false;
    	
    	phoneSystem.setUserServices(userId, services);
    	return true;
    }
	@Override
	public String toString() {
		return "Administrator []";
	}
}
