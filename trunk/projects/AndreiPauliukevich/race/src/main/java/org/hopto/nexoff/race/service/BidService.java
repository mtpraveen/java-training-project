package org.hopto.nexoff.race.service;

import java.util.List;

import org.hopto.nexoff.race.domain.Bid;
import org.hopto.nexoff.race.domain.Horse;
import org.hopto.nexoff.race.domain.Race;
import org.hopto.nexoff.race.domain.User;

public interface BidService {
	
	public List<Bid> findAll(User currentUser);
	
	public Bid findById(Long id);
	
	public Bid save(Bid bid);
	
	public void delete(Bid bid);
	
	public void setWinBids(Race race, Horse horse);

}
