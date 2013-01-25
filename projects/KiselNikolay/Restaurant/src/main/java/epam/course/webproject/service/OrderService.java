package epam.course.webproject.service;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

import epam.course.webproject.domain.Order;
import epam.course.webproject.domain.User;

public interface OrderService {

	public Order save(Order order);
	
	public List<Order> findByUser(String login);
	
	public void remove(Order order);
	
	public void removeById(Long id);

	public List<Order> findAll();
	
	public List<Order> findByDate(DateTime date);
	
	public Order findById(Long id);
	
}
