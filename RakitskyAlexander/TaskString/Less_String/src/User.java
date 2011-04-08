import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 */

/**
 * @author epam0003
 *
 */
public class User {
	private String address;
	private String fullName;
	private String email;
	private String phoneNomber;
	
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return fullName;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.fullName = name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phoneNomber
	 */
	public String getPhoneNomber() {
		return phoneNomber;
	}

	/**
	 * @param phoneNomber the phoneNomber to set
	 */
	public void setPhoneNomber(String phoneNomber) {
		this.phoneNomber = phoneNomber;
	}

	/**
	 * 
	 */
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		User user=new User();
		String str="ул.        Ленина,   д.     1,   кв.  10\n224001 г.Брест\nПечко Елена\n+375(29)690-49-89\nelenapechko@gmail.com";
		String strChange=str;
		boolean isChange = false;
		while (!isChange)
		{
			strChange=str.replaceAll("  ", " ");
			if(strChange.equals(str))
				isChange=true;
			str=strChange;
		}
		Pattern pat=Pattern.compile("((ул.|(б-р)|пер) ((.){1,})\n((.){1,}))");
		Matcher matcher = pat.matcher(str);
		while (matcher.find())
			user.setAddress(matcher.group());
		pat=Pattern.compile("\\s\\S++ \\S++\\s");
		matcher = pat.matcher(str);
		while (matcher.find())
			user.setName(matcher.group());
		str.replace("\n", "");
		pat=Pattern.compile("((.){1,}@(.){1,})");
		matcher = pat.matcher(str);
		while (matcher.find())
			user.setEmail(matcher.group());
		pat=Pattern.compile("\\+\\d++\\(\\d\\d\\)\\d++-\\d++-\\d++");
		matcher = pat.matcher(str);
		while (matcher.find())
			user.setPhoneNomber(matcher.group());
		System.out.println(user.toString());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		address=String.format("Addres: %s\n", address);
		fullName=String.format("Full name: %s\n", fullName);
		email=String.format("Emial: %s\n", email);
		phoneNomber=String.format("Phone nomber: %s\n",phoneNomber);
		return "" + address + fullName + email + phoneNomber;
	}

}
