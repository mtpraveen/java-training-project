package org.hopto.nexoff.race.service;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hopto.nexoff.race.domain.Authority;
import org.hopto.nexoff.race.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("userService")
@Repository
public class UserServiceImpl implements UserService {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private AuthorityService authorityService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional(readOnly = true)
	public List<User> findAll() {
		List<User> users = em.createNamedQuery("User.findAll", User.class)
				.getResultList();
		return users;
	}

	@Override
	@Transactional(readOnly = true)
	public User findById(Long id) {
		User user = em.find(User.class, id);
		return user;
	}

	@Override
	public User save(User user) {
		if (user.getId() == null){
			List<Authority> authorities = new ArrayList<Authority>();
			authorities.add(authorityService.findByAuthority("ROLE_USER"));
			user.setPassword(passwordEncoder.encodePassword(user.getPassword(), user.getUsername()));
			user.setAuthorities(authorities);
			user.setMoney(100);
			em.persist(user);
		} else {
			User refreshUser = em.find(User.class, user.getId());
			refreshUser.setEmail(user.getEmail());
			refreshUser.setFio(user.getFio());
			refreshUser.setUsername(user.getUsername());
			refreshUser.setPassword(user.getPassword().toLowerCase());
		}
		return user;
	}

	@Override
	public void delete(User user) {
		// em не связан с user
		User mergeUser = em.merge(user);
		em.remove(mergeUser);

	}

	@Override
	@Transactional(readOnly=true)
	public User loadUserByUsername(String username){
		User user = em.createNamedQuery("User.findByUsername", User.class).setParameter("username", username).getSingleResult();
		return user;
	}

}
