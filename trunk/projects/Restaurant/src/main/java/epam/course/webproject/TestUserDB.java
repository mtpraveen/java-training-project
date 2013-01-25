package epam.course.webproject;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.support.GenericXmlApplicationContext;

import epam.course.webproject.domain.Role;
import epam.course.webproject.domain.Section;
import epam.course.webproject.domain.User;
import epam.course.webproject.service.SectionService;
import epam.course.webproject.service.UserService;

public class TestUserDB {
	public static void main(String[] args) {

		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:datasource-tx-jpa.xml");
		ctx.refresh();
		UserService userService = ctx.getBean("userService", UserService.class);

		// Find all users
		List<User> users = userService.findAll();

		listSection(users);

		System.out.println();
		// Find one user
		System.out.println(userService.findById("vgerton"));

		User user1 = new User("first", "skf", "Vasilyi", "Petrov", new Date(),
				234434, "Moskow", "saldkjf");
		System.out.println(userService.save(user1));
		Set<Role> roles=new HashSet<Role>();
		roles.add(new Role("user"));
		user1.setRoles(roles);
		
		userService.save(user1);
		users = userService.findAll();
		listSection(users);
		
	}

	private static void listSection(List<User> users) {
		System.out.println("");
		System.out.println("Listing sections");
		for (User user : users) {
			System.out.println(user);
			System.out.println();
		}
	}
}
