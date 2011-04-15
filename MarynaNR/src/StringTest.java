/**
 * 
 */

/**
 * @author epam0020

 *
 */
import java.lang.String;
import java.util.StringTokenizer;


public class StringTest {

	public static boolean equal(String ss1, String ss2) {
		return ss1.equalsIgnoreCase(ss2);
	}

	public static String upper(String ss1, String sst2) {
		String ss2=ss1.toUpperCase(); 
		String sst3=sst2.substring(1);
		sst3= ss2+sst3;
		return sst3;
		
	}
	
	public static void main(String[] args) {
		System.out.println ("Задание 1:  ");
		String st1 = new String("акула сон  кук 55 мама мом 111 33");

		StringTokenizer st = new StringTokenizer(st1, " \t\n\r,:-");
		while (st.hasMoreTokens()) {
			String st2 = st.nextToken();
			String s1 = st2.substring(0, 1);
			String s2 = st2.substring(st2.length() - 1, st2.length());

			if (equal(s1, s2)) {
			 System.out.print(" " +st2 );} 
			
		}
		System.out.println (" \n");
		
		System.out.println ("Задание 2:  ");
		StringTokenizer stt = new StringTokenizer(st1, " \t\n\r,:-");
		while (stt.hasMoreTokens()) {
			String st2 = stt.nextToken();
			String s1 = st2.substring(0, 1);
			System.out.print (" " + upper (s1, st2));	}
	
		System.out.println ("\n");
		byte[] array = st1.getBytes(); 
		//System.out.print(st1);
		int s=0;
		for (int i=0; i<array.length; i++){
			if ((array[i]<57)&&(array[i]>48)) 
			{s=s+array[i]-48;}	
					}
		System.out.println ("Задание 3:  ");
		System.out.print(s);
	}

}
