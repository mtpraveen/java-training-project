package epam.course.webproject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.context.support.GenericXmlApplicationContext;

import epam.course.webproject.domain.Dish;
import epam.course.webproject.domain.Order;
import epam.course.webproject.domain.Section;
import epam.course.webproject.domain.User;
import epam.course.webproject.service.DishService;
import epam.course.webproject.service.OrderService;
import epam.course.webproject.service.UserService;

public class TestOrderDB {
	
	public static void main(String[] args) {

		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:datasource-tx-jpa.xml");
		ctx.refresh();
		OrderService orderService = ctx.getBean("orderService", OrderService.class);

		UserService userService = ctx.getBean("userService", UserService.class);
		DishService dishService = ctx.getBean("dishService", DishService.class);
		List<Order> orders=orderService.findByUser("vgerton");
		System.out.println();
		listOrder(orders);
		
		System.out.println(orders.get(2));
		User user=userService.findById("vgerton");
		Order order=new Order(new DateTime(), 10, "Chicago", "lsdkf", user);
		Section s1=new Section("Second dishes");
//		Dish d1=new Dish("Steak", 18, s1);
		List<Dish> l1=new ArrayList<Dish>();
		Dish d1=dishService.findByName("Caesar");
		l1.add(d1);
		order.setDishes(l1);
		order.setUser(user);
		orderService.save(order);
		//order.setDelivered(true);
		//orderService.remove(orders.get(2));
		
		//orderService.removeById(3L);
		HashSet<String> hs=new HashSet<String>();
		hs.add("1");
		hs.add("2");
		hs.add("3");
		for (String s: hs)
			System.out.println(s);
	}

	private static void listOrder(List<Order> orders) {
		System.out.println("");
		System.out.println("Listing sections");
		for (Order order : orders) {
			System.out.println(order);
			System.out.println();
		}
	}
	
}
