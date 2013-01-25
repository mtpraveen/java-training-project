package epam.course.webproject.service.jpatest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import epam.course.webproject.domain.Section;
import epam.course.webproject.service.SectionService;

public class SectionServiceImplTest {

	private static SectionService sectionService;
	private static String[] expectedSections;

	@BeforeClass
	public static void createDB() {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:datasource-tx-jpa-test.xml");
		ctx.refresh();
		sectionService = ctx.getBean("sectionService", SectionService.class);
		expectedSections = new String[] { "Salads", "First dishes",
				"Second dishes", "Drinks" };

	}

	@Test
	public void testFindAll() {
		List<Section> sections = sectionService.findAll();
		assertNotNull(sections);
		assertEquals(4, sections.size());
		for (int i = 0; i < sections.size(); i++)
			assertEquals(expectedSections[i], sections.get(i).getNameSection());
	}

	@Test
	public void testFindByName() {
		Section section = sectionService.findSection("Salads");
		assertNotNull(section);
		section = sectionService.findSection("FirstDIsh");
		assertNull(section);
	}

}
