package org.hopto.nexoff.race.service;

import java.util.List;

import org.hopto.nexoff.race.domain.Bid;

public interface BidService {
	
	public List<Bid> findAll();
	
	public Bid findById(Long id);
	
	public Bid save(Bid bid);
	
	public void delete(Bid bid);

}
