package epam.course.webproject.service;

import java.util.List;

import epam.course.webproject.domain.Section;

public interface SectionService {

	//Find all sections
	public List<Section> findAll();
	
	public Section findSection(String section);
}
