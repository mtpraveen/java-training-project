package epam.course.webproject.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "app_user")
public class User implements Serializable {

	private String login;
	private String password;
	private String name;
	private String surname;
	private Date birthDate;
	private Integer telephone;
	private String adress;
	private String cardNumber;

	// for one to many mapping
	private Set<Order> orders = new HashSet<Order>();
	// for many to many mapping
	private Set<Role> roles = new HashSet<Role>();

	public User() {

	}

	public User(String login, String password, String name, String surname,
			Date birthDate, int telephone, String adress, String cardNumber) {
		super();
		this.login = login;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.birthDate = birthDate;
		this.telephone = telephone;
		this.adress = adress;
		this.cardNumber = cardNumber;
	}

	@NotEmpty(message="{validation.login.NotEmpty.message}")
	@Size(min=3, max=20, message="{validation.login.Size.message}")
	@Id
	@Column(name = "ID_USER")
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@NotEmpty(message="{validation.password.NotEmpty.message}")
	@Size(min=6, max=250, message="{validation.password.Size.message}")
	@Column(name = "USER_PASSWORD")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@NotEmpty(message="{validation.name.NotEmpty.message}")
	@Size(min=2, max=40, message="{validation.name.Size.message}")
	@Column(name = "USER_NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@NotEmpty(message="{validation.surname.NotEmpty.message}")
	@Size(min=2, max=40, message="{validation.surname.Size.message}")
	@Column(name = "SURNAME")
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTH_DATE")
	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Column(name = "TELEPHONE")
	public Integer getTelephone() {
		return telephone;
	}

	public void setTelephone(Integer telephone) {
		this.telephone = telephone;
	}

	@Size(min=0, max=250, message="{validation.adress.Size.message}")
	@Column(name = "ADRESS")
	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	@Size(min=0, max=40, message="{validation.cardNumber.Size.message}")
	@Column(name = "CARD_NUMBER")
	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	// one to many mapping
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	public void addOrder(Order order) {
		order.setUser(this);
		getOrders().add(order);
	}

	public void removeUser(User user) {
		getOrders().remove(user);
	}

	@ManyToMany
	@JoinTable(name = "user_has_role", joinColumns = @JoinColumn(name = "ID_USER"), inverseJoinColumns = @JoinColumn(name = "ID_ROLE"))
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "login=" + login + ", password=" + password + ", name=" + name
				+ ", surname=" + surname + ", birthDate=" + birthDate
				+ ", telephone=" + telephone + ", adress=" + adress
				+ ", cardNumber=" + cardNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}
	
}
