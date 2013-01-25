package epam.course.webproject.domain;

import java.util.Collections;
import java.util.List;

public class RestaurantMenu{

	List<Dish> dishes;

	public RestaurantMenu() {
		
	}
	
	public RestaurantMenu(List<Dish> dishes) {
		this.dishes = dishes;
	}

	public List<Dish> getDishes() {
		return dishes;
	}

	public void setDishes(List<Dish> dishes) {
		this.dishes = dishes;
	}
	
	public void sort() {
		Collections.sort(dishes);
	}

}
