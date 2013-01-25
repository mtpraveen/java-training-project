package epam.course.webproject.service;

import java.util.List;

import epam.course.webproject.domain.User;

public interface UserService {

	public User findById(String login);
	
	public User save(User user);
	
	public List<User> findAll();
	
	public void remove(String login);
}
