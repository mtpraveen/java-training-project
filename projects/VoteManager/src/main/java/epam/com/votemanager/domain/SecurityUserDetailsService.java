package epam.com.votemanager.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import epam.com.votemanager.dao.ContactDAO;

public class SecurityUserDetailsService implements UserDetailsService {
	private ContactDAO userDao;

	@Autowired
	public void setUserDao(ContactDAO userDao) {
		this.userDao = userDao;
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {

		Contact user = userDao.selectUserByMail(username);

		if (user == null)
			throw new UsernameNotFoundException("User not found");
		return user;
	}
}
