package by.epam.blog.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import by.epam.blog.model.Topic;

/**
 * @author Dmitry_Goncharov
 *
 */
@Repository
public interface TopicRepository extends CrudRepository<Topic, Long> {

}
