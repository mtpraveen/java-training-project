package epam.course.webproject.repository;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import epam.course.webproject.domain.Order;

public interface OrderRepository extends CrudRepository<Order, Long>{
	
	@Query("select o from Order o where o.user.id =:id")
	public List<Order> findByUserLogin(@Param("id") String id);
	
	@Query("select o from Order o where o.date >=:date Order by o.date, o.hour")
	public List<Order> findByDate(@Param("date") DateTime date);
	
	
}
