import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Order
{
	private static String l;
	static File file  = new File(Main.way()+"\\order.txt");

	public void order(){
		try {
			BufferedReader br = 
			new BufferedReader(new FileReader(file));
						String tmp = "";
						while ((tmp = br.readLine()) != null) {				
							String[] s = tmp.split("\\n");							
							for (String res : s){
								System.out.println(res);								
								l+=res;								
							}								
						}	
						br.close();
					}
		catch (IOException e) {
						e.printStackTrace();
					}								
	}
				
	
public String name() {			
		String name = "name:[A-Z]\\w+ [A-Z]\\w+" ;
		Pattern pattern = Pattern.compile(name);
		Matcher matcher = pattern.matcher(l);
		while (matcher.find()){
		  System.out.println("name: " + matcher.group());
		  name=matcher.group();}
		return name;
	}

public String passport() {
	String passport = "passport:[A-Z]\\w+" ;
	Pattern pattern = Pattern.compile(passport);
	Matcher matcher = pattern.matcher(l);
	while (matcher.find()){	 
	  passport=matcher.group();}
	return passport;
}
	
 public int days() {	
	StringTokenizer tokenizer = new StringTokenizer(l, " ,;:!@#$%^&*()+=_");	
	 int sum = 0;
		while (tokenizer.hasMoreTokens()) {
			try {
			sum += Integer.parseInt(tokenizer.nextToken());
			} catch (NumberFormatException ex) {			
		}
		}	
		 int days = sum ;	
	return days;
}	
 public int daysTest() {	
	 StringTokenizer tokenizer = new StringTokenizer(l, " ,;:!@#$%^&*()+=_");	
	 int sum = 0;
		while (tokenizer.hasMoreTokens()) {
			try {
			sum += Integer.parseInt(tokenizer.nextToken());
			} catch (NumberFormatException ex) {			
		}
		}	
		 int days = sum ;	
		return days;
	}	
}
