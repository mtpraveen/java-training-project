package by.epam.blog.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import by.epam.blog.model.User;

/**
 * @author Dmitry_Goncharov
 *
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}

