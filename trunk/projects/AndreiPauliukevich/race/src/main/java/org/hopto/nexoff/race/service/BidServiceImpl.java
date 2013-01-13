package org.hopto.nexoff.race.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hopto.nexoff.race.domain.Bid;
import org.hopto.nexoff.race.domain.Horse;
import org.hopto.nexoff.race.domain.Race;
import org.hopto.nexoff.race.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("orderService")
@Repository
public class BidServiceImpl implements BidService {
	

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private UserService userService;

	@Override
	@Transactional(readOnly=true)
	public List<Bid> findAll(User currentUser) {
		List<Bid> bids = em.createNamedQuery("Bid.findAll", Bid.class).setParameter("user", currentUser).getResultList();
		return bids;
	}

	@Override
	@Transactional(readOnly=true)
	public Bid findById(Long id) {
		return em.find(Bid.class, id);
	}

	@Override
	public Bid save(Bid bid) {
		userService.debit(bid.getUser(), bid.getAmount());
		em.persist(bid);
		return bid;		
	}

	@Override
	public void delete(Bid bid) {
		em.remove(bid);
	}

	@Override
	public void setWinBids(Race race, Horse horse) {
		List<Bid> bids = em.createNamedQuery("Bid.winBids", Bid.class).setParameter("race", race).setParameter("horse", horse).getResultList();
		for(Bid bid : bids){
			userService.credit(bid.getUser(), bid);
		}
	}

}
