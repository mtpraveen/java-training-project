package com.vb.testproj.service;

import java.util.Date;
import java.util.Collection;
import java.util.List;

import javax.persistence.Query;

import com.vb.testproj.helpers.CryptoHelper;
import com.vb.testproj.model.User;
import com.vb.testproj.model.UserRole;
import com.vb.testproj.model.UserStatus;
import com.vb.testproj.persistence.IUser;
import com.vb.testproj.persistence.PersistenceBase;

public class UserService extends PersistenceBase implements IUser {

	@Override
	public boolean hasUser(int id) {
		return getUser(id) != null;
	}

	@Override
	public boolean hasUser(String userName) {
		return getUser(userName) != null;
	}

	@Override
	public int addUser(String userName, String password, String firstName,
			String lastName, UserRole role) {

		if (hasUser(userName)) return -1;
		
		try {
			User u = new User(userName, CryptoHelper.crypt(password),
					firstName, lastName, role);
			addObject(u);
			
			return u.getId();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}

	@Override
	public void deleteUser(int id) {
		User u = getUser(id);
		deleteObject(u);
	}

	private void loginUser(User u){
		if (u == null) return;
		u.setUserStatus(UserStatus.ONLINE);
		u.setLastLoginDate(new Date());

		updateDatabase();
	}
	
	@Override
	public void loginUser(String userName) {
		User u = getUser(userName);
		loginUser(u);
	}
	@Override
	public void loginUser(int id) {
		User u = getUser(id);
		loginUser(u);
	}

	private void logoutUser(User u){
		if (u == null) return;
		u.setUserStatus(UserStatus.OFFLINE);
		u.setLastLogoutDate(new Date());

		updateDatabase();
	}
	
	@Override
	public void logoutUser(int id) {
		User u = getUser(id);
		logoutUser(u);
	}
	@Override
	public void logoutUser(String userName) {
		User u = getUser(userName);
		logoutUser(u);
	}

	private User getUser(Query query) {
		beginTransmition();

		List<User> allResults = query.getResultList();

		User res = null;
		
		if (allResults.size() != 0)
			res = allResults.get(0);
		endTransmition();

		return res;
	}

	@Override
	public User getUser(int id) {
		Query query = entityManager
				.createQuery("SELECT n FROM User n WHERE n.id = :id");
		query.setParameter("id", id);

		return getUser(query);
	}

	@Override
	public User getUser(String userName) {
		Query query = entityManager
				.createQuery("SELECT n FROM User n WHERE n.userName = :userName");
		query.setParameter("userName", userName);

		return getUser(query);
	}

	@Override
	public Collection<User> getOnlineUsers(int skipCount, int takeCount) {
		beginTransmition();

		Query query = entityManager.createQuery("SELECT n FROM User n WHERE n.userStatus = :userStatus");
		query.setParameter("userStatus", UserStatus.ONLINE.ordinal());

		List<User> res = query.setFirstResult(skipCount)
				.setMaxResults(takeCount).getResultList();

		endTransmition();

		return res;
	}

	private boolean isUserOnline(User u) {
		return u != null && u.isOnline();
	}

	@Override
	public boolean isUserOnline(int id) {
		return isUserOnline(getUser(id));
	}

	@Override
	public boolean isUserOnline(String userName) {
		return isUserOnline(getUser(userName));
	}

	private boolean changePassword(User u, String currentPassword,
			String newPassword) {
		if (u == null)
			return false;
		try {
			if (!CryptoHelper.isMatch(currentPassword, u.getPassword()))
				return false;
			u.setPassword(CryptoHelper.crypt(newPassword));
			updateDatabase();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean changePassword(String userName, String currentPassword,
			String newPassword) {
		User u = getUser(userName);
		return changePassword(u, currentPassword, newPassword);
	}

	@Override
	public boolean changePassword(int id, String currentPassword,
			String newPassword) {
		User u = getUser(id);
		return changePassword(u, currentPassword, newPassword);
	}

}
