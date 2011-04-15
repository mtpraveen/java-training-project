import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Autobase {
	private static String q;
	private int[] a;
	private String [] b;


	
	
	public void autobase(){			     		    
		    File file  = new File(Main.way()+"\\autos.txt");		    						
				try {
					BufferedReader br = 
					new BufferedReader(new FileReader(file));
								String tmp = "";
								while ((tmp = br.readLine()) != null) {									
									String[] s = tmp.split("\\n");									
									for (String res : s){
										System.out.println(res);										
										q+=res;																	
									}										
								}	
								br.close();
							} catch (IOException e) {
								e.printStackTrace();
								}
	}					    

public int price() {	
	StringTokenizer tokenizer = new StringTokenizer(q, ":");	
	 int sum = 0;
		while (tokenizer.hasMoreTokens()) {
			try {
			sum += Integer.parseInt(tokenizer.nextToken());			
			} catch (NumberFormatException ex) {			
		}
		}	
		int price = sum ;		
	return price;
}

public int whatPrice(int i) {
	int n = 1;
	 a=new int[11]; 
	File file  = new File(Main.way()+"\\autos.txt");
	try {
		BufferedReader br = 
		new BufferedReader(new FileReader(file));
					String tmp = "";
					while ((tmp = br.readLine()) != null) {						
						String[] s = tmp.split("\\n");						
						for (String res : s){					
							q+=res;
							a[n]=price();							
							n+=1;
						}							
					}	
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	return a[i];						
}

public String auto(String q) {	
	String  auto = " [a-z]\\w+ " ;
	Pattern pattern = Pattern.compile(auto);
	Matcher matcher = pattern.matcher(q);
	while (matcher.find()){
  auto=matcher.group();}
	return auto;
}
		
public String whatAuto(int r) {
	b=new String[11]; 
	File file  = new File(Main.way()+"\\autos.txt");
	int v=1;
		try {
		BufferedReader br = 
		new BufferedReader(new FileReader(file));
					String tmp = "";
					while ((tmp = br.readLine()) != null) {
						String[] s = tmp.split("\\n");					
						for (String res : s){						
							q+=res;
							b[v]=auto(q);}						
							v+=1;												
					}	
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		return b[r];			
}
}

