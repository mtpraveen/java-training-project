package org.hopto.nexoff.race.service;

import java.util.List;

import org.hopto.nexoff.race.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

	public List<User> findAll();
	
	public User findById(Long id);
	
	public User save(User user);
	
	public void delete(User user);

	public User loadUserByUsername(String username);

}
