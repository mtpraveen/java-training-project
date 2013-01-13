package org.hopto.nexoff.race.service;

import java.util.List;

import org.hopto.nexoff.race.domain.Bid;
import org.hopto.nexoff.race.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

	public List<User> findAll();
	
	public User findById(Long id);
	
	public void delete(User user);

	public User loadUserByUsername(String username);

	public Boolean isUniqueUsername(User user);
	
	public void debit(User user, Double money);

	public User update(User user);

	public User create(User user);

	public void credit(User user, Bid bid);
	

}
