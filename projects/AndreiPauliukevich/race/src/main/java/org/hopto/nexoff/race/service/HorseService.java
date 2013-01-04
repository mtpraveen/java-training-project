package org.hopto.nexoff.race.service;

import java.util.List;

import org.hopto.nexoff.race.domain.Horse;

public interface HorseService {

	public List<Horse> findAll();
	
	public Horse findById(Long id);
	
	public Horse save(Horse horse);
	
	public void delete(Horse horse);
	
	
}
