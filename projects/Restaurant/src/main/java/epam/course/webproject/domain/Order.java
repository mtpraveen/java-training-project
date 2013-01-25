package epam.course.webproject.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.*;
import javax.validation.constraints.Future;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "user_order")
public class Order implements Serializable {

	private Long id;
	private DateTime date;
	private Integer hour;
	private String adress;
	private String cardNumber;
	private int cost;
	private boolean paid;
	private boolean delivered;

	// for many to one mapping
	private User user;
	// for many to many mapping
	// private Set<Dish> dishes=new HashSet<Dish>();
	private List<Dish> dishes = new ArrayList<Dish>();

	public Order() {

	}

	public Order(DateTime date, int hour, String adress, String cardNumber,
			int cost, boolean paid, boolean delivered) {
		this.date = date;
		this.hour = hour;
		this.adress = adress;
		this.cardNumber = cardNumber;
		this.cost = cost;
		this.paid = paid;
		this.delivered = delivered;
	}

	public Order(DateTime date, int hour, String adress, String cardNumber,
			User user) {
		this.date = date;
		this.hour = hour;
		this.adress = adress;
		this.cardNumber = cardNumber;
		this.user = user;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID_ORDER")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	// @Temporal(TemporalType.DATE)
	//@Future(message="{validation.date.Future.message}")
	@Column(name = "ORDER_DATE")
	@Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
	@DateTimeFormat(iso = ISO.DATE)
	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	@Column(name = "ORDER_HOUR")
	public Integer getHour() {
		return hour;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
	}

	@Column(name = "ADRESS")
	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	@Column(name = "CARD_NUMBER")
	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	@Column(name = "COST")
	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	@Column(name = "PAID")
	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	@Column(name = "DELIVERED")
	public boolean isDelivered() {
		return delivered;
	}

	public void setDelivered(boolean delivered) {
		this.delivered = delivered;
	}

	@ManyToOne
	@JoinColumn(name = "ID_USER")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	// @ManyToMany
	// @JoinTable(name="order_has_dish",
	// joinColumns=@JoinColumn(name="ID_ORDER"),
	// inverseJoinColumns=@JoinColumn(name="NAME_DISH"))
	// public Set<Dish> getDishes() {
	// return dishes;
	// }
	//
	// public void setDishes(Set<Dish> dishes) {
	// this.dishes = dishes;
	// }

	@ManyToMany
	@JoinTable(name = "order_has_dish", joinColumns = @JoinColumn(name = "ID_ORDER"), inverseJoinColumns = @JoinColumn(name = "NAME_DISH"))
	public List<Dish> getDishes() {
		return dishes;
	}

	public void setDishes(List<Dish> dishes) {
		this.dishes = dishes;
	}

	@Override
	public String toString() {
		StringBuilder result=new StringBuilder();
		for (Dish dish: dishes)
			result.append(dish.getNameDish()).append("\n");
		return result.toString();
	}

}
