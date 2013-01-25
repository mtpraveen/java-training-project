package epam.course.webproject.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import epam.course.webproject.domain.Dish;
import epam.course.webproject.domain.Section;
import epam.course.webproject.repository.DishRepository;
import epam.course.webproject.service.DishService;

@Service("dishService")
@Repository
@Transactional
public class DishServiceImpl implements DishService {

	@Autowired
	private DishRepository dishRepository;

	@Override
	public Dish save(Dish dish) {
		return dishRepository.save(dish);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Dish> findBySection(Section section) {
		return Lists.newArrayList(dishRepository.findBySection(section));
	}

	@Override
	@Transactional(readOnly = true)
	public Dish findByName(String name) {
		return dishRepository.findOne(name);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Dish> findAll() {
		return Lists.newArrayList(dishRepository.findAll());
	}

	@Override
	@Transactional(readOnly = true)
	public List<Dish> findBySectionAndPrice(Section section, int minPrice,
			int maxPrice) {
		return Lists.newArrayList(dishRepository.findBySectionAndPrice(section,
				minPrice, maxPrice));
	}

	@Override
	@Transactional(readOnly = true)
	public List<Dish> findByPrice(int minPrice, int maxPrice) {
		return Lists.newArrayList(dishRepository
				.findByPrice(minPrice, maxPrice));
	}

	@Override
	public void remove(Dish dish) {
		dishRepository.delete(dish);

	}

}
