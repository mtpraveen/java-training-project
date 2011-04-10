import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class User {
	
	private String data;
	
	
	public User(String da){
		data=da;
	}
	
	public String email() {
		
		String email = "\\S+(?<=@)\\S+" ;
		Pattern pattern = Pattern.compile(email);
		Matcher matcher = pattern.matcher(data);
		while (matcher.find()){
		  System.out.println("email: " + matcher.group());
		email=matcher.group();}
		return email;
	}
	
	
public String surnameName() {
		
		String surnameName = "[A-Z]\\w+ [A-Z]\\w+" ;
		Pattern pattern = Pattern.compile(surnameName);
		Matcher matcher = pattern.matcher(data);
		while (matcher.find()){
		  System.out.println("surnameName: " + matcher.group());
		  surnameName=matcher.group();}
		return surnameName;
	}

public String index() {
	String index = "s?[0-9]{6}" ;
	Pattern pattern = Pattern.compile(index);
	Matcher matcher = pattern.matcher(data);
	while (matcher.find()){
		 System.out.println("index: " + matcher.group());
		 index=matcher.group();}
	return index;
}

public String phoneNumber() {
	
	String phoneNumber = " [+][0-9]{3}[(][0-9]{2}[)][0-9]{3}[-][0-9]{2}[-][0-9]{2}" ;
	Pattern pattern = Pattern.compile(phoneNumber);
	Matcher matcher = pattern.matcher(data);
	while (matcher.find()){
		 System.out.println("phoneNumber: " + matcher.group());
		 phoneNumber=matcher.group();}
	return phoneNumber;
}
public String adress() {
	String adress = "(b\\-r|pr\\-t|ul\\.)\\s+[A-Z][a-z]+\\,\\s+d\\.\\s+\\d+\\,\\s+kv\\.\\s+\\d+\n\\s?\\d{6}\\s+g\\.\\s+[A-Z][a-z]+" ;
	Pattern pattern = Pattern.compile(adress);
	Matcher matcher = pattern.matcher(data);
	while (matcher.find()){
		 System.out.println("adress: " + matcher.group());
		 adress=matcher.group();}
	return adress;
}

}
