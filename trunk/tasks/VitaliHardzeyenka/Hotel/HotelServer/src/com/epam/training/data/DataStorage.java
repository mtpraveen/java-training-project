package com.epam.training.data;

import java.util.ArrayList;

import com.epam.training.model.application.Application;
import com.epam.training.model.room.Room;
import com.epam.training.model.user.User;

/**
 * In this class upload data after reading it from text file. 
 * @author Gordeenko
 */
public class DataStorage {
	public static ArrayList<Room> ROOMS_LIST = new ArrayList<>(); // information about rooms all in hotel
	public static ArrayList<User> USERS_LIST = new ArrayList<>(); // info about user can connect to server 
	public static ArrayList<Application> APPLICATIONS_LIST = new ArrayList<>(); // info about applications submitted to server
}
