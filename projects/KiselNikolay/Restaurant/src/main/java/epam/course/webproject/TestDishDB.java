package epam.course.webproject;

import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

import epam.course.webproject.domain.Dish;
import epam.course.webproject.domain.Section;
import epam.course.webproject.service.DishService;
import epam.course.webproject.service.SectionService;

public class TestDishDB {

	public static void main(String[] args) {

		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:datasource-tx-jpa.xml");
		ctx.refresh();
		DishService dishService = ctx.getBean("dishService", DishService.class);
		SectionService sectionService = ctx.getBean("sectionService",
				SectionService.class);

		// Find all section
		List<Dish> sections = dishService.findAll();

		listSection(sections);

		 Section section=new Section("Drinks");
		// sections=dishService.findBySection(sectionService.findSection("Drinks"));
		listSection(dishService.findBySection(section));
		//listSection(sections);
		
		System.out.println(dishService.findByName("Tea"));
	}

	private static void listSection(List<Dish> dishes) {
		System.out.println("");
		System.out.println("Listing sections");
		for (Dish dish : dishes) {
			System.out.println(dish);
			System.out.println();
		}
	}
}
