package epam.course.webproject.service;

import java.util.List;

import epam.course.webproject.domain.Dish;
import epam.course.webproject.domain.Section;

public interface DishService {

	public Dish save(Dish dish);

	public List<Dish> findBySection(Section section);

	public List<Dish> findBySectionAndPrice(Section section, int minPrice,
			int maxPrice);

	public Dish findByName(String name);

	public List<Dish> findAll();
	
	public List<Dish> findByPrice(int minPrice, int maxPrice);
	
	public void remove(Dish dish);
}
