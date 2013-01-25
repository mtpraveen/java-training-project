package epam.course.webproject.service;

import java.util.ArrayList;
import java.util.List;

import epam.course.webproject.domain.Dish;

public class DishesService {

	public static List<Dish> findByName(List<Dish> dishes, String name) {
		List<Dish> resultDishes = new ArrayList<Dish>();
		if (name == null || name.equals(""))
			resultDishes = dishes;
		else {
			for (Dish dish : dishes) {
				if (name.length() <= dish.getNameDish().length()
						&& dish.getNameDish().substring(0, name.length())
								.equalsIgnoreCase(name))
					resultDishes.add(dish);
			}
		}
		return resultDishes;
	}
}
