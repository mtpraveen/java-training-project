package net.epam.java.autobase;

import java.util.ArrayList;

public class Admin extends User implements IAdmin {

	public Admin(int id, String username, String password) {
		super(id, username, password);
	}
	
	public Admin() { }

	@Override
	public void setUserSuspended(int sid, User user, boolean value) {
		server.setUserSuspended(sid, user, value);
		
	}

	@Override
	public void addUser(int sid, String username, String password, UserType userType) throws addUserException {
		server.addUser(sid, username, password, userType);		
	}

	@Override
	public void deleteUser(int sid, User user) {
		server.deleteUser(sid, user);	
	}

	@Override
	public ArrayList<User> getUsersList(int sid) {
		return server.getUsersList(sid);
	}

	@Override
	public void setUserPassword(int sid, User user, String password) {
		server.setUserPassword(sid, user, password);
	}

	@Override
	public User getUserById(int sid, int id) {
		return server.getUserById(sid, id);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Admin " + getUsername() + "(suspended=" + isSuspended()
		+ ", id=" + getId() + ")";
	}

}
