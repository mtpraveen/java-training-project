package org.hopto.nexoff.race.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hopto.nexoff.race.domain.Horse;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("horseService")
@Repository
public class HorseServiceImpl implements HorseService {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional(readOnly=true)
	public List<Horse> findAll() {
		List<Horse> horse = em.createNamedQuery("Horse.findAll", Horse.class).getResultList();
		return horse;
	}

	@Override
	@Transactional(readOnly=true)
	public Horse findById(Long id) {
		Horse horse = em.find(Horse.class, id);
		return horse;
	}

	@Override
	public Horse save(Horse horse) {
		if (horse.getId() == null)
			em.persist(horse);
		else
			em.merge(horse);
		return horse;
	}

	@Override
	public void delete(Horse horse) {
		Horse mergeHorse = em.merge(horse);
		em.remove(mergeHorse);
	}

}
