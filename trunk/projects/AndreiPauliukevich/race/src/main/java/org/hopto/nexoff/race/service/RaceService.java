package org.hopto.nexoff.race.service;

import java.util.List;
import org.hopto.nexoff.race.domain.Race;

public interface RaceService {
	
	public List<Race> findAll();
	
	public Race findById(Long id);
	
	public void delete(Race race);

	public Race findByIdFetch(Long id);

	public Race close(Race race);

	public Race create(Race race);


}
