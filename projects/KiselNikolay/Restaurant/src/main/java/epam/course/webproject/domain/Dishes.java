package epam.course.webproject.domain;

import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class Dishes {

	//List<Dish> dishes;
	String[] nameDishes;
	DateTime date;
	Integer hour;
	String adress;
	String cardNumber;

	public Dishes() {

	}

	public String[] getNameDishes() {
		return nameDishes;
	}

	public void setNameDishes(String[] nameDishes) {
		this.nameDishes = nameDishes;
	}

//	 public Dishes(List<Dish> dishes) {
//	 this.dishes = dishes;
//	 }
//
//	 public List<Dish> getDishes() {
//	 return dishes;
//	 }
//
//	public void setDishes(List<Dish> dishes) {
//		this.dishes = dishes;
//	}

	@NotNull(message = "{validation.date.NotNull.message}")
	@Future(message="{validation.date.Future.message}")
	@Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
	@DateTimeFormat(iso = ISO.DATE)
	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	@NotNull(message = "{validation.hour.NotNull.message}")
	@Max(value = 24, message = "{validation.hour.Max.message}")
	@Min(value = 0, message = "{validation.hour.Min.message}")
	public Integer getHour() {
		return hour;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
	}

	@NotNull(message = "{validation.adress.NotNull.message}")
	@Size(min=5, max=250, message="{validation.adress.Size.message}")
	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	@NotNull(message = "{validation.cardNumber.NotNull.message}")
	@Size(min=5, max=40, message="{validation.cardNumber.Size.message}")
	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	@Override
	public String toString() {
		return "Dishes [nameDishes=" + Arrays.toString(nameDishes) + "]";
	}
	
	

}
