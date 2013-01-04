package org.hopto.nexoff.race.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hopto.nexoff.race.domain.Race;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("raceService")
@Repository
public class RaceServiceImpl implements RaceService {

	@PersistenceContext
	private EntityManager em;

	@Transactional(readOnly=true)
	@Override
	public List<Race> findAll() {
		List<Race> races = em.createNamedQuery("Race.findAll", Race.class).getResultList();
		return races;
	}

	@Transactional(readOnly=true)
	@Override
	public Race findById(Long id) {
		Race race = em.find(Race.class, id);
		return race;
	}
	
	@Transactional(readOnly=true)
	@Override
	public Race findByIdFetch(Long id) {
		Query query = em.createNamedQuery("Race.findByIdFetch", Race.class).setParameter("id", id);
		Race race = (Race) query.getSingleResult();
		return race;
	}

	@Override
	public Race save(Race race) {
		if (race.getId() == null){
			em.persist(race);
		}
		else{
			Race refreshRace = em.find(Race.class, race.getId());
			refreshRace.setWinner(race.getWinner());
		}
			
		return race;
	}
	
	@Override
	public void delete(Race race) {
		System.out.println(race);
		Race mergeRace = em.merge(race);
		em.remove(mergeRace);
	}

}
