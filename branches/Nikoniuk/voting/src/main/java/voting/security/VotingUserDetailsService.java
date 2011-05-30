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

public class VotingUserDetailsService implements UserDetailsService {
	
	private  static final Logger LOGGER = Logger.getLogger(VotingUserDetailsService.class);

	@Override
	public UserDetails loadUserByUsername(String username) {

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		Account account = Account.findAccountByName(username);
		if (account == null)
			throw new BadCredentialsException("security_invalid_username");
		authorities.add(new GrantedAuthorityImpl("ROLE_USER"));
		if (account.getSystemRole() == SystemRole.ADMIN)
			authorities.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
		if (account.getBanned())
			throw new BadCredentialsException("security_banned_user");
		
		LOGGER.error(username + " successfully entered system as  " + account.getSystemRole());
		
		return new User(account.getName(), account.getPassword(), true, // enabled
				true, // account not expired
				true, // credentials not expired
				true, // we checked banned status before
				authorities);
	}

	public static Account getCurrentAccount(HttpServletRequest httpServletRequest) {
		return Account.findAccountByName(httpServletRequest.getUserPrincipal().getName());
	}
}
