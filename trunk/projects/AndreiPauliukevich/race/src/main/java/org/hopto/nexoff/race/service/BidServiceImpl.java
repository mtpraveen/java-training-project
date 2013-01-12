package org.hopto.nexoff.race.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hopto.nexoff.race.domain.Bid;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("orderService")
@Repository
public class BidServiceImpl implements BidService {
	

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Bid> findAll() {
		List<Bid> bids = em.createNamedQuery("Bid.findAll", Bid.class).getResultList();
		return bids;
	}

	@Override
	public Bid findById(Long id) {
		return em.find(Bid.class, id);
	}

	@Override
	public Bid save(Bid bid) {
		if (bid.getId() == null) {
			em.persist(bid);
		} else{
			em.merge(bid);
		}
		return bid;		
		
	}

	@Override
	public void delete(Bid bid) {
		em.remove(bid);
	}

}
