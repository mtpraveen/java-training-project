package autobase.model;

import autobase.exceptions.AuthorizationException;

/**
 * <p>Represents user operations </p>
 * 
 * @author Alexander Nikoniuk
 */
public interface IUser {

	/**
	 * Changes user's password
	 * 
	 * @param sid
	 *            current session id
	 * @param password
	 *            new password   
	 */
    public void changePassword(int sid, String password);

	/**
	 * Changes user's suspend status
	 * 
	 * @param sid
	 *            current session id
	 * @param value
	 *            suspended status value 
	 */
    public void setSuspended(int sid, boolean value);

	/**
	 * Logs the user in the system
	 * 
	 * @param username
	 *            username of the user
	 * @param password
	 *            password of the user 
	 * @return
	 * 			 session id for the authorized user
	 * @throws
	 * 			 AuthorizationException
	 */
    public int logIn(String username, String password) throws AuthorizationException;
    
	/**
	 * Logs the user out of the system
	 * 
	 * @param sid
	 *            current session id
	 */
    public void logOut(int sid);

}
