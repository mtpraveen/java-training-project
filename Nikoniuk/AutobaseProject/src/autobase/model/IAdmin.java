package autobase.model;

import java.util.ArrayList;

import autobase.exceptions.AutobaseException;

/**
 * <p>Represents dispatcher operations </p>
 * 
 * @author Alexander Nikoniuk
 */
public interface IAdmin {

	/**
	 * Sets user suspend status
	 * 
	 * @param sid
	 *            current session id
	 * @param user
	 *            user to set the status
	 * @param value
	 *            suspend status value      
	 */
    public void setUserSuspended(int sid, User user, boolean value);

	/**
	 * Creates user account in the system
	 * 
	 * @param sid
	 *            current session id
	 * @param username
	 *            username of the new user
	 * @param password
	 *            password of the new user    
	 * @param userType
	 *            type of the new user   
	 * @return
	 * 			  new user
	 * @throws
	 * 		  AutobaseException
	 */
    public User addUser(int sid, String username, String password, UserType userType) throws AutobaseException;

	/**
	 * Deletes user from autobase
	 * 
	 * @param sid
	 *            current session id
	 * @param user
	 *            user for deleting
	 */
    public void deleteUser(int sid, User user);

	/**
	 * Deletes user from autobase
	 * 
	 * @param sid
	 *            current session id
	 * @param user
	 *            user for deleting
	 */
    public ArrayList<User> getUsersList(int sid);

	/**
	 * Returns list of all the registered users
	 * 
	 * @param sid
	 *            current session id
	 * @return
	 * 			 list of all the users 
	 */
    public void setUserPassword(int sid, User user, String password);

	/**
	 * Returns user by id
	 * 
	 * @param sid
	 *            current session id
	 * @param id
	 * 			 id of user to return
	 * @return
	 * 			 found by id user
	 */
    public User getUserById(int sid, int id);

}
