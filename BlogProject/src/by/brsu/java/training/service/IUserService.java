package by.brsu.java.training.service;

import java.util.List;

import by.brsu.java.training.entity.User;

public interface IUserService {
	boolean addUser(User user);
	User getUserByName(String name);
	User getUserById(long userId);
	List<User> getUsers();
	void setUser(User user);
	boolean deleteUser(long userId);
}
