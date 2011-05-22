package voting.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import voting.domain.Account;
import voting.domain.SystemRole;

public class VotingUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) {

		return null;
	}

}
