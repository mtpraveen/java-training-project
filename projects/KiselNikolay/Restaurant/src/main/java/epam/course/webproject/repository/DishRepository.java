package epam.course.webproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import epam.course.webproject.domain.Dish;
import epam.course.webproject.domain.Order;
import epam.course.webproject.domain.Section;

public interface DishRepository extends CrudRepository<Dish, String> {

	@Query("select d from Dish d where d.section =:section")
	public List<Dish> findBySection(@Param("section") Section section);

	@Query("select d from Dish d where d.section =:section and d.cost>=:minPrice and d.cost<=:maxPrice")
	public List<Dish> findBySectionAndPrice(@Param("section") Section section,
			@Param("minPrice") int minPrice, @Param("maxPrice") int maxPrice);

	@Query("select d from Dish d where d.cost>=:minPrice and d.cost<=:maxPrice")
	public List<Dish> findByPrice(@Param("minPrice") int minPrice,
			@Param("maxPrice") int maxPrice);
}
