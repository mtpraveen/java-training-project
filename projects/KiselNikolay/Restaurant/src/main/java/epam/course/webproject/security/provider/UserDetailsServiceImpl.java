package epam.course.webproject.security.provider;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import epam.course.webproject.domain.Role;
import epam.course.webproject.domain.User;
import epam.course.webproject.service.UserService;

public class UserDetailsServiceImpl implements UserDetailsService {

	final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		
		logger.info("Load user record for ", userName);

		UserDetails userDetails = null;
		User user = userService.findById(userName);

		if (user != null) {
			String password = user.getPassword();
			Set<Role> roles = user.getRoles();
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			for (Role role : roles) {
				String roleName = role.getName();
				GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(
						roleName);
				authorities.add(grantedAuthority);
			}
			userDetails=new org.springframework.security.core.userdetails.User(userName, password, authorities);
		} else {
            
            throw new UsernameNotFoundException("User " + userName + " not found");
		}
		
		return userDetails;

	}

}
