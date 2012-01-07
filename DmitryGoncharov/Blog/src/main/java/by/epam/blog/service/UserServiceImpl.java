package by.epam.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.epam.blog.dao.UserRepository;
import by.epam.blog.model.User;

/**
 * @author Dmitry_Goncharov
 *
 */
@Service
@Transactional
public class UserServiceImpl{
	
    @Autowired
    private UserRepository userRepository;

	public Iterable<User> findAllUsers() {
		return 	userRepository.findAll();
		}
	public User findUserById(Long user) {
		return userRepository.findOne(user);
	}

	public Long saveUser(User user) {
		return userRepository.save(user).getId();
	}
}
