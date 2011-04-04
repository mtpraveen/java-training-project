/**
 * 
 */
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter; 


/**
 * @author epam0020
 * 
 */
public class User {
	String index, adress, town,name, mail, tel1, tel2 = "(One PhoneNumber)";

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getTel1() {
		return tel1;
	}

	public String getTel2() {
		return tel2;
	}

	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}

	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}

	private void setTown(String town) {
		this.town = town;
	}

	private String getTown() {
		return town;
	}

	/**
	 * @param args
	 */
	public static Matcher makePattern(String regex1, String userInfo1) {
		Pattern pattern = Pattern.compile(regex1, Pattern.CASE_INSENSITIVE);
		Matcher matcher1 = pattern.matcher(userInfo1);
		return matcher1;
	}

	/*public static void makeFormat(Matcher matcher1){
		String str = matcher1.toString();
	System.out.println(str);
}*/
	
	public static void main(String[] args) {

		String userInfo = "\n 224001 г.Брест \n ул.Куйбышева, д.1 \n Иванов Иван \n ivanov@gmail.com \n +375(29)720-00-00 \n +375(29)720-00-01 ";
		System.out.println(userInfo);
		User user = new User();

		Matcher matcher = makePattern("\\d{6}", userInfo);
		while (matcher.find())
			user.setIndex(matcher.group());
	//	makeFormat(matcher);
		
		matcher = makePattern("г.[ ]*(.+)", userInfo); 
		while (matcher.find())
			user.setTown(matcher.group(1));

		matcher = makePattern("ул.[ ]*(.+)", userInfo);
		while (matcher.find())
			user.setAdress(matcher.group());

		matcher = makePattern("[А-я]+ [А-я]+", userInfo);
		while (matcher.find())
			user.setName(matcher.group());

		matcher = makePattern("[ ]+((.)+@(.)+)", userInfo);
		while (matcher.find())
			user.setMail(matcher.group(1));
		
		matcher = makePattern("[+]((.)+)\\S", userInfo);
		boolean f = true;
		while (matcher.find()) {
			if (f == true) {
				user.setTel1(matcher.group());
				f = false;
				} else
				user.setTel2(matcher.group());
		}

		Formatter str = new Formatter(); 
		
		//str.fоrmаt("Форматирование %s просто %d %f", "с Java", 10, 98.6); 
		
		str.format("Индекс:           %-6s \n", user.getIndex());
		str.format("Город:            %-10s \n", user.getTown());
		str.format("Адрес:            %-20s \n", user.getAdress());
		str.format("Имя:              %-20s \n", user.getName());
		str.format("е-mail:           %-20s \n", user.getMail());
		str.format("Телефон:          %-20s \n", user.getTel1());
		str.format("                  %-20s  \n", user.getTel2());
        System.out.println(str);
        
        // %t ??????????
 //       %tB
              
     //   Calendar rightNow = Calendar.getInstance();
     //	System.out.println(rightNow);
		//str.format("%t", );
	
		//str.format(" ", DateFormat.getDateInstance());???????????
        // DateFormat formDate = new DateFormat.getDateInstance(); ??????????????? 
		SimpleDateFormat formatter =
			new SimpleDateFormat("K:mm a EEE dd MM yyyy");
		String s = formatter.format(new Date());
		System.out.println(s);
		
		

		

	
	
}}
