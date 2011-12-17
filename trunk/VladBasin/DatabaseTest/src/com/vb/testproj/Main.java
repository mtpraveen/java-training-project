package com.vb.testproj;

import static java.lang.System.out;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.List;

import com.vb.testproj.helpers.CryptoHelper;
import com.vb.testproj.model.Notification;
import com.vb.testproj.model.User;
import com.vb.testproj.model.UserRole;
import com.vb.testproj.service.NotificationService;
import com.vb.testproj.service.UserService;

public class Main {

	public static NotificationService service;
	
	public static void getNewNotificationsTest() {
		Collection<Notification> all = service.getNewNotifications(1, 10, 10);

		for (Notification n : all) {
			out.println(n);
		}
	}

	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		UserService service = new UserService();
		
		//out.println(service.hasUser("vb"));
		//service.addUser("vb", "qwertyui", "Vlad", "Basin", UserRole.MANAGER);
		//out.println(service.hasUser("vb"));
		
		//User u = service.getUser("vb");
		
		//out.println(u);
	
		
		service.addUser("bevb", "qwertyui", "Nevlad", "Nebasin", UserRole.USER);
		
		service.logoutUser("bevb");
		
		for (User t : service.getOnlineUsers(0, 1000)){
			out.println(t);
		}
		
	}

}
