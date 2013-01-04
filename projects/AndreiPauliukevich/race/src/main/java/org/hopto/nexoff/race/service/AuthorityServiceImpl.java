package org.hopto.nexoff.race.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hopto.nexoff.race.domain.Authority;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("authorityService")
@Repository
public class AuthorityServiceImpl implements AuthorityService {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Authority> findAll() {
		List<Authority> authorities = em.createNamedQuery("Authority.findAll", Authority.class).getResultList();
		return authorities;
	}

	@Override
	public Authority findByAuthority(String authority) {
		return em.createNamedQuery("Authority.findByAuthority", Authority.class).setParameter("authority", authority).getSingleResult();
		
	}

}
