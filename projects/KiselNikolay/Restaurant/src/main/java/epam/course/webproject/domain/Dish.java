package epam.course.webproject.domain;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "dish")
public class Dish implements Serializable, Comparable<Dish> {

	private String nameDish;
	private String ingredients;
	private Integer cost;
	private String description;
	private byte[] image;

	// for many to one mapping
	private Section section;
	// for many to many mapping
	private Set<Order> orders = new HashSet<Order>();

	public Dish() {

	}

	public Dish(String nameDish, Integer cost, Section section) {
		this.nameDish = nameDish;
		this.cost = cost;
		this.section = section;
	}

	@NotEmpty(message="{validation.nameDish.NotEmpty.message}")
	@Size(min=3, max=50, message="{validation.nameDish.Size.message}")
	@Id
	@Column(name = "NAME_DISH")
	public String getNameDish() {
		return nameDish;
	}

	public void setNameDish(String nameDish) {
		this.nameDish = nameDish;
	}

	@Column(name = "INGREDIENTS")
	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	@DecimalMin(value = "1", message="{validation.cost.DecimalMin.message}")
	@DecimalMax(value = "100000", message="{validation.cost.DecimalMax.message}")
	@Column(name = "COST")
	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Basic(fetch = FetchType.LAZY)
	@Lob
	@Column(name = "IMAGE")
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	// many to one mapping
	@ManyToOne
	@JoinColumn(name = "NAME_SECTION")
	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	@ManyToMany
	@JoinTable(name = "order_has_dish", joinColumns = @JoinColumn(name = "NAME_DISH"), inverseJoinColumns = @JoinColumn(name = "ID_ORDER"))
	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "nameDish=" + nameDish + ", ingredients=" + ingredients
				+ ", cost=" + cost + ", description=" + description
				+ ", image=" + Arrays.toString(image);
	}

	@Override
	public int compareTo(Dish o) {
		int i=section.compareTo(o.section);
		if (i==0) {
			i=nameDish.compareTo(o.nameDish);
		}
		return i;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((nameDish == null) ? 0 : nameDish.hashCode());
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
		Dish other = (Dish) obj;
		if (nameDish == null) {
			if (other.nameDish != null)
				return false;
		} else if (!nameDish.equals(other.nameDish))
			return false;
		return true;
	}
	
	

}
