package by.epam.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.epam.blog.repository.UserRepository;

/**
 * @author Dmitry_Goncharov
 *
 */
@Service
@Transactional
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
}
