package epam.course.webproject.domain;

public class SearchCriteria {

	private Section section;
	private String dishName;
	private int minPrice;
	private int maxPrice;

	public SearchCriteria() {

	}

	public SearchCriteria(Section section, String dishName) {
		this.section = section;
		this.dishName = dishName;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public int getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}

	public int getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}

	@Override
	public String toString() {
		return "SearchCriteria [section=" + section + ", dishName=" + dishName
				+ ", minPrice=" + minPrice + ", maxPrice=" + maxPrice + "]";
	}

}
