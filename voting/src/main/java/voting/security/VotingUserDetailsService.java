package voting.security;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import voting.domain.Account;
import voting.domain.SystemRole;

/**
 * <p>Voting user details(provides information about account authorities, ban status to spring security) </p>
 * 
 * @author Alexander Nikoniuk
 */
public class VotingUserDetailsService implements UserDetailsService {
	
	private  static final Logger LOGGER = Logger.getLogger(VotingAuthenticationProvider.class);

	@Override
	public UserDetails loadUserByUsername(String username) {

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		Account account = Account.findAccountByName(username);
		if (account == null) {
			LOGGER.error("attempt to log in with unexisting username " + username);
			throw new BadCredentialsException("security_invalid_username");
		}
		authorities.add(new GrantedAuthorityImpl("ROLE_USER"));
		if (account.getSystemRole() == SystemRole.ADMIN)
			authorities.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
		if (account.getBanned()) {
			LOGGER.error(username + " tries to log in the system, but he has been banned");
			throw new BadCredentialsException("security_banned_user");
		}
		
		return new User(account.getName(), account.getPassword(), true, // enabled
				true, // account not expired
				true, // credentials not expired
				!account.getBanned(), // account is not banned
				authorities);
	}

	public static Account getCurrentAccount(HttpServletRequest httpServletRequest) {
		return Account.findAccountByName(httpServletRequest.getUserPrincipal().getName());
	}
}
