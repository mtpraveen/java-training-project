package entryPoint;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Formatter;
import java.util.List;
import java.util.ArrayList;
/**
 * 
 */

/**
 * @author Евгений
 *
 */
public class User {
	private String address;
	private String name;
	private List<String> eMailAddresses;
	private List<String> phoneNumbers ;
	
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the eMailAddress
	 */
	public List<String> getEMailAddresses() {
		return eMailAddresses;
	}

	/**
	 * @return the phoneNumber
	 */
	public List<String> getPhoneNumbers() {
		return phoneNumbers;
	}
	
	public User(){
		this.phoneNumbers = new ArrayList<String>();
		this.eMailAddresses = new ArrayList<String>();
		this.address = "empty address";
		this.name = "empty name";
	}
	public void fillAllFields(String inStr){
		
		Pattern pattern = Pattern.compile("\\+(\\s*\\d\\s*){3}\\((\\s*\\d\\s*){2}\\)(\\s*[\\d|-]\\s*){8}\\s*\\d");
		Matcher matcher = pattern.matcher(inStr);
		while (matcher.find())
			this.phoneNumbers.add(matcher.group());
		inStr = matcher.replaceAll("");
		
		pattern = Pattern.compile("\\S+@\\S+");
		matcher = pattern.matcher(inStr);
		while (matcher.find())
			this.eMailAddresses.add(matcher.group());
		inStr = matcher.replaceAll("");
		
		pattern = Pattern.compile("\\s*[а-я[А-Я|-]]+\\s+[а-я[А-Я|-]]+");
		matcher = pattern.matcher(inStr);
		if (matcher.find() && inStr.indexOf(this.name = matcher.group()) == 0){
			this.name = this.name.trim();
			this.address = matcher.replaceFirst("").trim();
		}else{
			while (matcher.find())
				this.name = matcher.group();
			this.name = this.name.trim();
			this.address = inStr.replaceFirst(this.name, "").trim();
		}
	}
	
	public String toString(){
		Formatter formatter = new Formatter();
		
		StringBuilder temp = new StringBuilder("");
		for (String str : this.phoneNumbers)
			temp.append(str + "\n");
		String tempPhones = temp.toString();
		temp.delete(0, temp.length());
		for (String str : this.eMailAddresses)
			temp.append(str + "\n");
		String tempEMail = temp.toString();
		
		formatter.format("%s%n%s%n%s%s", this.name, this.address, tempPhones, tempEMail);
		return formatter.toString();
	}
}
