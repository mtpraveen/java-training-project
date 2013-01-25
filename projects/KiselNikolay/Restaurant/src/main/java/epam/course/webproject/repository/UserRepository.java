package epam.course.webproject.repository;

import org.springframework.data.repository.CrudRepository;

import epam.course.webproject.domain.User;

public interface UserRepository extends CrudRepository<User, String>{

}
