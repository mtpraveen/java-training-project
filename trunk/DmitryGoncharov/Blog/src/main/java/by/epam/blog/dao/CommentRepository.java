package by.epam.blog.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import by.epam.blog.model.Comment;

/**
 * @author Dmitry_Goncharov
 *
 */
@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

}
