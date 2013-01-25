package epam.course.webproject.repository;

import org.springframework.data.repository.CrudRepository;

import epam.course.webproject.domain.Section;

public interface SectionRepository extends CrudRepository<Section, String> {

}
