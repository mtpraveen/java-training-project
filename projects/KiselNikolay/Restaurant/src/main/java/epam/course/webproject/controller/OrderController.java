package epam.course.webproject.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import epam.course.webproject.domain.Dish;
import epam.course.webproject.domain.Dishes;
import epam.course.webproject.domain.Order;
import epam.course.webproject.domain.Section;
import epam.course.webproject.domain.User;
import epam.course.webproject.form.Message;
import epam.course.webproject.service.DishService;
import epam.course.webproject.service.DishesService;
import epam.course.webproject.service.OrderService;
import epam.course.webproject.service.SectionService;
import epam.course.webproject.service.UserService;

@RequestMapping("/order")
@Controller
public class OrderController {

	final Logger logger = LoggerFactory.getLogger(DishController.class);

	@Autowired
	private DishService dishService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserService userService;

	@Autowired
	private SectionService sectionService;

	@Autowired
	MessageSource messageSource;

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(params = "form", method = RequestMethod.GET)
	public String createForm(Model uiModel) {
		List<Dish> allDishes = dishService.findAll();
		Collections.sort(allDishes);
		List<Section> sections = sectionService.findAll();
		Dishes dishes = new Dishes();
		Authentication at = SecurityContextHolder.getContext()
				.getAuthentication();
		UserDetails ud = (UserDetails) at.getPrincipal();
		User user = userService.findById(ud.getUsername());
		dishes.setAdress(user.getAdress());
		dishes.setCardNumber(user.getCardNumber());
		List<Integer> hours=new ArrayList<Integer>();
		for (int i=0; i<=24; i++)
			hours.add(i);
		uiModel.addAttribute("hours", hours);
		uiModel.addAttribute("alldishes", allDishes);
		uiModel.addAttribute("dishes", dishes);
		uiModel.addAttribute("sections", sections);
		return "order/new";
	}

	@RequestMapping(params = "form", method = RequestMethod.POST)
	public String create(@Valid Dishes dishes, BindingResult bindingResult,
			Model uiModel, HttpServletRequest httpServletRequest,
			RedirectAttributes redirectAttributes, Locale locale) {
		if (bindingResult.hasErrors()) {
			createModelForOrder(uiModel);
			return "order/new";
		}
		if (dishes.getNameDishes().length == 0) {
			createModelForOrder(uiModel);
			uiModel.addAttribute(
					"message",
					new Message("error", messageSource.getMessage(
							"dishes_not_checked", null, locale)));
			return "order/new";
		}
		String[] nameDishes = dishes.getNameDishes();
		List<Dish> orderedDishes = new ArrayList<Dish>();
		for (int i = 0; i < nameDishes.length; i++) {
			orderedDishes.add(dishService.findByName(nameDishes[i]));

		}
		Order newOrder = new Order();
		newOrder.setDishes(orderedDishes);
		Authentication at = SecurityContextHolder.getContext()
				.getAuthentication();
		UserDetails ud = (UserDetails) at.getPrincipal();
		User user = userService.findById(ud.getUsername());
		newOrder.setUser(user);
		int totalSum = 0;
		for (Dish dish : orderedDishes) {
			totalSum += dish.getCost();
		}
		newOrder.setCost(totalSum);
		newOrder.setAdress(dishes.getAdress());
		newOrder.setCardNumber(dishes.getCardNumber());
		newOrder.setDate(dishes.getDate()
				.withZone(DateTimeZone.forID("Europe/Minsk")).plusHours(10));
		newOrder.setHour(dishes.getHour());
		System.out.println(dishes.getDate());
		DateTime dt = dishes.getDate();
		dt = dt.withZone(DateTimeZone.forID("Europe/Minsk"));
		System.out.println(dt);
		System.out.println(dt.getZone());
		System.out.println(newOrder.getDate());
		orderService.save(newOrder);
		uiModel.addAttribute("order", newOrder);
		uiModel.addAttribute(
				"message",
				new Message("success", messageSource.getMessage(
						"order_accepted", null, locale)));
		return "order/check";
	}

	public void createModelForOrder(Model uiModel) {
		List<Dish> allDishes = dishService.findAll();
		List<Section> sections = sectionService.findAll();
		List<Integer> hours=new ArrayList<Integer>();
		for (int i=0; i<=24; i++)
			hours.add(i);
		uiModel.addAttribute("hours", hours);
		uiModel.addAttribute("alldishes", allDishes);
		uiModel.addAttribute("sections", sections);
	}

	@PreAuthorize("hasRole('admin')")
	@RequestMapping(value = "/showlist", params = "new", method = RequestMethod.GET)
	public String showOrders(Model uiModel) {
		List<Order> orders = orderService.findByDate(new DateTime());
		uiModel.addAttribute("orders", orders);
		return "order/list";

	}

	@PreAuthorize("hasRole('admin')")
	@RequestMapping(value = "/showlist", params = "all", method = RequestMethod.GET)
	public String showOrdersAll(Model uiModel) {
		List<Order> orders = orderService.findAll();
		uiModel.addAttribute("orders", orders);
		return "order/list";

	}

	@PreAuthorize("hasRole('admin')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteDish(@PathVariable("id") Long id, Model uiModel,
			Locale locale) {
		// Order order=orderService.findById(id);
		orderService.removeById(id);
		return "redirect:/order?form";
	}

	//@PreAuthorize("hasRole('admin')")
	@RequestMapping(value = "/update/{id}/{rowCounter}", method = RequestMethod.GET)
	public @ResponseBody
	String updateOrder(@PathVariable("id") Long id, @PathVariable int rowCounter) {
		Order order=orderService.findById(id);
		if (order.isDelivered())
			order.setDelivered(false);
		else
			order.setDelivered(true);
		orderService.save(order);
		
		return ""+rowCounter;
	}

}
