package voting.security;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class VotingAuthenticationProvider extends
		AbstractUserDetailsAuthenticationProvider {

	private  static final Logger LOGGER = Logger.getLogger(VotingAuthenticationProvider.class);

	private UserDetailsService userDetailsService;

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected UserDetails retrieveUser(String username,
			UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {

		UserDetails loadedUser = userDetailsService
				.loadUserByUsername(username);
		String password = authentication.getCredentials().toString();

		if (!password.equals(loadedUser.getPassword())) {
			LOGGER.error("unsuccessfull attempt to log in as " + username
					+ " with wrong password " + password);
			throw new BadCredentialsException("security_invalid_password");
		}
		return loadedUser;
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		// TODO Auto-generated method stub
	}
}