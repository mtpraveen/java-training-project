package by.epam.blog.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import by.epam.blog.model.Blog;

/**
 * @author Dmitry_Goncharov
 *
 */
@Repository
public interface BlogRepository extends CrudRepository<Blog, Long> {

}
