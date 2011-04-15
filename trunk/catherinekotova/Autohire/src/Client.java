import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


 
public class Client
{	
	private static  String q;
	static File file  = new File(Main.way()+"\\client.txt");	
	private int p;
	private int cost;
	private String name;

	public void identity(){
		try {
			BufferedReader br = 
			new BufferedReader(new FileReader(file));
						String tmp = "";
						while ((tmp = br.readLine()) != null) {					
							String[] s = tmp.split("\\n");
							for (String res : s){
								System.out.println(res);								
								q+=res;
								Main.clientCreator().name(q);
								p=Main.clientCreator().choice();																 
								if(Main.adminCreator().desision()==true){									
								cost=Main.orderCreator().days()*Main.autobaseCreator().whatPrice(p);
								System.out.println("cost "+cost);
								if(Main.clientCreator().payTest()==true){
									 try 
									    {
									        BufferedWriter outfile = 
									          new BufferedWriter(new FileWriter("account.txt",true));
									        outfile.write(" \n"+Main.clientCreator().name(q)+" is going to return auto "+Main.autobaseCreator().whatAuto(p)+
													" in "+Main.orderCreator().days()+"days");
									        outfile.close();
									    }
									    catch (IOException e)    {   }								
									Main.clientCreator().using();								
									if(Main.clientCreator().damage()==true){
										Main.adminCreator().notes();										
										}
									}}
							}							
						}	
						br.close();
					}
		catch (IOException e) {
			
					}	
	}
	
boolean damageTest() {
	
	boolean y=true;;
	
			//System.out.println("client "+" has damaged auto");
	
	return y;				
	}

public int choiceTest() {
	int n=2;
	//System.out.println("client "+"choise "+ n);
		return n;
	}

public String name(String q) {		
		String name1 = "client: [A-Z]\\w+ [A-Z]\\w+" ;
		Pattern pattern = Pattern.compile(name1);
		Matcher matcher = pattern.matcher(q);		
		while (matcher.find()){		 
		  name=matcher.group();}		 
		return name;
	}


			public int choice(){
				int n= (int) ((Math.random() * 9)+1)	;
				System.out.println("client "+"choise "+ n);
				return n;				
			}
			
		
						
			public boolean pay() {
				int n= (int) (Math.round(Math.random()))	;				
				boolean y;
				if(n==1 ){
					y=true;
			}
			else y=false;									
				if(y==true ){
						System.out.println("client "+ " has payed");
				}
				else System.out.println("client "+ " hasn't payed");
				return y;										
			}	
			
			public boolean payTest() {				
				boolean y=true;						
						//System.out.println("client "+ " has payed");
							return y;										
			}			
			
public void using() {				
				System.out.println("client "+ " took auto,has used auto, has returned auto");
			}

public boolean damage(){	
	int n= (int) (Math.round(Math.random()))	;	
	boolean y;
	if(n==1 ){
		y=true;
}
else y=false;			
	if(y==true ){
			System.out.println("client "+" has damaged auto");
	}	
	return y;				
}
}