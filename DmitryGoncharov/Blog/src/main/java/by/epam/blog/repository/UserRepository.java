package by.epam.blog.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import by.epam.blog.model.User;

/**
 * @author Dmitry_Goncharov
 *
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	List<User> findAllByCustomer(String customer);
}
