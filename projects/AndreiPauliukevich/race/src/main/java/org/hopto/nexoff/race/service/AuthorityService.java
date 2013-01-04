package org.hopto.nexoff.race.service;

import java.util.List;

import org.hopto.nexoff.race.domain.Authority;

public interface AuthorityService {
	
	public List<Authority> findAll();
	
	public Authority findByAuthority(String authority);

}
