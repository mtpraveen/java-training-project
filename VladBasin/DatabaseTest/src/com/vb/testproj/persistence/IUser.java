package com.vb.testproj.persistence;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;

import com.vb.testproj.model.User;
import com.vb.testproj.model.UserRole;

public interface IUser {
	boolean hasUser(int id);
	boolean hasUser(String userName);

	int addUser(String userName, String password, String firstName, String lastName, UserRole role);
	void deleteUser(int id);
	
	void loginUser(int id);
	void loginUser(String userName);
	
	void logoutUser(int id);
	void logoutUser(String userName);
	
	User getUser(int id);
	User getUser(String userName);
	
	boolean isUserOnline(int id);
	boolean isUserOnline(String userName);
	
	Collection<?> getOnlineUsers(int skipCount, int takeCount);

	boolean changePassword(int userId, String currentPassword, String newPassword);
	boolean changePassword(String userName, String currentPassword, String newPassword);
}
