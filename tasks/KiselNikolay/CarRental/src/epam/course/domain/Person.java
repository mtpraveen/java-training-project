package epam.course.domain;

import java.io.Serializable;

public abstract class Person implements Serializable {

	private String name;
	private String surname;
	private String paspNumber;
	private String login;
	private String password;

	public Person(String name, String surname, String paspNumber, String login,
			String password) {
		this.name = name;
		this.surname = surname;
		this.paspNumber = paspNumber;
		this.login = login;
		this.password = password;
	}
	
	public Person() {
		
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPaspNumber() {
		return paspNumber;
	}

	public void setPaspNumber(String paspNumber) {
		this.paspNumber = paspNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((paspNumber == null) ? 0 : paspNumber.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
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
		Person other = (Person) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (paspNumber == null) {
			if (other.paspNumber != null)
				return false;
		} else if (!paspNumber.equals(other.paspNumber))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", surname=" + surname
				+ ", paspNumber=" + paspNumber + ", login=" + login
				+ ", password=" + password + "]";
	}

	
}
