/**
 * 
 */
package net.epam.java.formatter;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * @author epam0001
 * 
 */
public class User {
	private String address;
	private String name;
	private String email;
	private ArrayList<String> phones;

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phones
	 */
	public ArrayList<String> getPhones() {
		return phones;
	}

	/**
	 * @param phones
	 *            the phones to set
	 */
	public void setPhones(ArrayList<String> phones) {
		this.phones = phones;
	}

	public ArrayList<String> parse(String str, String regExpr) {
		ArrayList<String> result = new ArrayList<String>();
		Pattern pattern = Pattern.compile(regExpr);
		Matcher matcher = pattern.matcher(str);
		while (matcher.find()) {
			String match = matcher.group();
			result.add(match);
		}
		return result;
	}

	public User(String str) {
		str = str.replaceAll("[ ]+", " ");

		name = parse(str, "[A-Z][a-z]+\\s+[A-Z][a-z]+").get(0);
		email = parse(str, "\\w+@\\w+(\\.\\w{2,3})+").get(0);
		address = parse(
				str,
				"(b\\-r|ul\\.)\\s+[A-Z][a-z]+\\,\\s+d\\.\\s+\\d+\\,\\s+kv\\.\\s+\\d+\n\\s?\\d{6}\\s+g\\.\\s+[A-Z][a-z]+")
				.get(0);
		phones = parse(str, "\\d{3}\\(\\d{2}\\)\\d{3}\\-\\d{2}\\-\\d{2}");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		Formatter fmt = new Formatter();
		fmt.format("name: %s \naddress: %s \nemail: %s \nphones: %s", name,
				address, email,
				phones.toString().substring(1, phones.toString().length() - 1));
		return fmt.toString();
	}

	public static void main(String[] args) {
		String data = "Alexander Nikoniuk     \n      123(00)123-22-44 nikoniuk@mail.ru.edu     ul. Laktionova, d.      30, kv. 63\n224001 g. Brest  345(00)123-22-44";
		try {
			User user = new User(data);
			System.out.println(user);
		} catch(RuntimeException e) {
			System.err.println("Parse error");
		}
	}
}
