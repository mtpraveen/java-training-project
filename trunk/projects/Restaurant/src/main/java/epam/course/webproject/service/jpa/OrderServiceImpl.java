package epam.course.webproject.service.jpa;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import epam.course.webproject.domain.Order;
import epam.course.webproject.repository.OrderRepository;
import epam.course.webproject.service.OrderService;

@Service("orderService")
@Repository
@Transactional
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public Order save(Order order) {
		return orderRepository.save(order);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Order> findByUser(String login) {
		return Lists.newArrayList(orderRepository.findByUserLogin(login));
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Order> findAll() {
		return Lists.newArrayList(orderRepository.findAll());
	}

	@Override
	public void remove(Order order) {
		orderRepository.delete(order);
		
	}
	
	@Override
	public void removeById(Long id) {
		orderRepository.delete(id);
	}

	@Override
	public List<Order> findByDate(DateTime date) {
		return Lists.newArrayList(orderRepository.findByDate(date));
	}

	@Override
	@Transactional(readOnly=true)
	public Order findById(Long id) {
		return orderRepository.findOne(id);
	}

	
}
