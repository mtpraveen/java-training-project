import static junit.framework.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;


public class Tests {
	static File file  = new File(Main.way()+"\\client.txt");
	private String q;
	private int p;
	private int cost;
	private String m="";	
	@Test
	public void test() {
		System.out.println("test");
		String expected=" \nclient: Petr Petrov chose audi price 130 \nclient: Ivan Ivanov chose audi price 130";
		try {
			BufferedReader br = 
			new BufferedReader(new FileReader(file));
						String tmp = "";
						while ((tmp = br.readLine()) != null) {					
							String[] s = tmp.split("\\n");
							for (String res : s){
															
								q+=res;				
								p=Main.clientCreator().choiceTest();																 
								if(Main.adminCreator().desisionTest()==true){									
								if(Main.clientCreator().payTest()==true){
									
									try 
								    {
								        m+=" \n"+Main.clientCreator().name(q)+" chose"+Main.autobaseCreator().whatAuto(p)+
										"price "+Main.autobaseCreator().whatPrice(p);
										BufferedWriter outfile = 
								          new BufferedWriter(new FileWriter("accounttest.txt",true));
								        outfile.write(" \n"+Main.clientCreator().name(q)+" chose"+Main.autobaseCreator().whatAuto(p)+
												"price "+Main.autobaseCreator().whatPrice(p));
								        outfile.close();
								    }
								    catch (IOException e)    {    }		
														
									
									}}
							}							
						}	
						br.close();
					}
		catch (IOException e) {
						e.printStackTrace();
					}	
		assertEquals(expected,m);
	
	}
	
	
	}
