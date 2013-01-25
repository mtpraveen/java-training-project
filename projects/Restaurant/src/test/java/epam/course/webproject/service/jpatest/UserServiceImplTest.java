package epam.course.webproject.service.jpatest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import epam.course.webproject.domain.User;
import epam.course.webproject.service.UserService;

public class UserServiceImplTest {

	private static UserService userService;

	@BeforeClass
	public static void createDB() {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:datasource-tx-jpa-test.xml");
		ctx.refresh();
		userService = ctx.getBean("userService", UserService.class);
	}
	
	@Test
	public void findAllTest() {
		List<User> users=userService.findAll();
		assertNotNull(users);
		assertEquals(2, users.size());
	}
	
	@Test
	public void findByIdTest() {
		User user=userService.findById("admin");
		assertNotNull(user);
		assertEquals("Nikolay", user.getName());
		user=userService.findById("asd");
		assertNull(user);
	}
	
	@Test
	public void saveTest() {
		User newUser=new User("qwerty", "123456", "Ivan", "Ivanov", new Date(), 12345, "Minsk", "123");
		userService.save(newUser);
		List<User> users=userService.findAll();
		assertEquals(3, users.size());
		
	}
	
}
