package epam.course.webproject.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "section")
public class Section implements Serializable {

	private String nameSection;

	// for one to many mapping
	private Set<Dish> dishes = new HashSet<Dish>();

	public Section() {

	}

	public Section(String section) {
		this.nameSection = section;
	}

	@Id
	@Column(name = "NAME_SECTION")
	public String getNameSection() {
		return nameSection;
	}

	public void setNameSection(String nameSection) {
		this.nameSection = nameSection;
	}

	@OneToMany(mappedBy = "section", cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<Dish> getDishes() {
		return dishes;
	}

	public void setDishes(Set<Dish> dishes) {
		this.dishes = dishes;
	}

	public void addDish(Dish dish) {
		dish.setSection(this);
		getDishes().add(dish);
	}

	public void removeDish(Dish dish) {
		getDishes().remove(dish);
	}

	@Override
	public String toString() {
		return "nameSection= " + nameSection;
	}

	public int compareTo(Section s) {
		return nameSection.compareTo(s.nameSection);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((nameSection == null) ? 0 : nameSection.hashCode());
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
		Section other = (Section) obj;
		if (nameSection == null) {
			if (other.nameSection != null)
				return false;
		} else if (!nameSection.equals(other.nameSection))
			return false;
		return true;
	}

}
