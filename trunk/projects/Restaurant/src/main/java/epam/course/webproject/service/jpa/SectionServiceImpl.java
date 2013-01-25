package epam.course.webproject.service.jpa;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import epam.course.webproject.domain.Section;
import epam.course.webproject.repository.SectionRepository;
import epam.course.webproject.service.SectionService;

@Service("sectionService")
@Repository
@Transactional
public class SectionServiceImpl implements SectionService {

	@Autowired
	private SectionRepository sectionRepository;
	
	@Override
	@Transactional(readOnly=true)
	public List<Section> findAll() {
//		List<Section> sections=(List<Section>) sectionRepository.findAll();
//		return sections;
//		new ArrayList<Section>((Collection<? extends Section>) sectionRepository.findAll());
		return Lists.newArrayList(sectionRepository.findAll());
	}

	@Override
	@Transactional(readOnly=true)
	public Section findSection(String section) {
		return sectionRepository.findOne(section);
	}
	
	
}
