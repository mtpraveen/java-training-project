package epam.course.webproject;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

import epam.course.webproject.domain.Section;
import epam.course.webproject.service.SectionService;


public class TestDB {
	public static void main(String[] args) {

		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:datasource-tx-jpa.xml");
		ctx.refresh(); 
		SectionService sectionService = ctx.getBean(
				"sectionService", SectionService.class);
		
		// Find all section
		List<Section> sections = sectionService.findAll();
		
		listSection(sections);
	}
	
	private static void listSection(List<Section> sections) {
		System.out.println("");
		System.out.println("Listing sections");
		for (Section section: sections) {
			System.out.println(section);
			System.out.println();
		}
	}

}
