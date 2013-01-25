package epam.course.webproject.service.jpatest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import epam.course.webproject.domain.Dish;
import epam.course.webproject.domain.Section;
import epam.course.webproject.service.DishService;

public class DishServiceImplTest {

	private static DishService dishService;

	@BeforeClass
	public static void createDB() {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:datasource-tx-jpa-test.xml");
		ctx.refresh();
		dishService = ctx.getBean("dishService", DishService.class);
	}

	@Test
	public void findAllTest() {
		List<Dish> dishes = dishService.findAll();
		assertNotNull(dishes);
		assertEquals(5, dishes.size());
	}

	@Test
	public void findBySectionTest() {
		Section section = new Section("Drinks");
		List<Dish> dishes = dishService.findBySection(section);
		assertNotNull(dishes);
		assertEquals(2, dishes.size());
		section = new Section("New Drinks");
		dishes = dishService.findBySection(section);
		assertEquals(0, dishes.size());
	}

	@Test
	public void findByPriceTest() {
		List<Dish> dishes = dishService.findByPrice(10, 15);
		assertNotNull(dishes);
		assertEquals(2, dishes.size());
		dishes = dishService.findByPrice(30, 40);
		assertEquals(0, dishes.size());
	}

	@Test
	public void findBySectionAndPrice() {
		Section section = new Section("Salads");
		List<Dish> dishes = dishService.findBySectionAndPrice(section, 5, 14);
		assertNotNull(dishes);
		System.out.println(dishes);
		assertEquals(1, dishes.size());
		dishes = dishService.findBySectionAndPrice(section, 1, 4);
		assertEquals(0, dishes.size());
	}

	@Test
	public void findByNameTest() {
		Dish dish = dishService.findByName("Caesar");
		assertNotNull(dish);
		int price = dish.getCost();
		assertEquals(15, price);
		dish = dishService.findByName("Abc");
		assertNull(dish);
	}

//	@Test
//	public void saveTest() {
//		Section section = new Section("Salads");
//		Dish newDish = new Dish("MySalad", 12, section);
//		dishService.save(newDish);
//		List<Dish> dishes = dishService.findAll();
//		assertNotNull(dishes);
//		assertEquals(6, dishes.size());
//		Dish dish = dishService.findByName("MySalad");
//		int price = dish.getCost();
//		System.out.println(dish);
//		assertEquals(12, price);
//		dish=dishService.findByName("Caesar");
//		dishService.remove(dish);
//	}

}
