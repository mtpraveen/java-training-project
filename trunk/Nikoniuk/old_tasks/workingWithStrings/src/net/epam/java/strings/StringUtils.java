/**
 * 
 */
package net.epam.java.strings;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * @author epam0001
 *
 */
public class StringUtils {
	public static void task1(String s) {
		Set<String> result = new HashSet<String>();
		StringTokenizer st = new StringTokenizer(s);
		while(st.hasMoreTokens()) {
			String buf = st.nextToken();
			if (buf.charAt(0) == buf.charAt(buf.length() - 1)) {
				result.add(buf);
			}
		}
		System.out.println(result);
	}
	
	public static void task2(String s) {
		boolean numberFound = false;
		int start = 0;
		int end = 0;
		int sum = 0;
		for(int i = 0; i < s.length(); i++) {
			if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
				if (numberFound == false) {
					numberFound = true;
					start = i;
				}
			}  else {
				if (numberFound == true) {
					numberFound = false;
					end = i;
					sum += Integer.parseInt(s.substring(start, end ));
				}
			}
		}
		System.out.println("sum = " + sum);
	}
	
	public static void task3(String s) {
		StringBuilder sb = new StringBuilder(s);
		for(int i = 0; i < sb.length(); ++i) {
			char ch = i > 0? sb.charAt(i - 1): 0;
			if (i == 0 || ch == ' ' || ch == ',' || ch == '.'  || ch == '\t') {
				sb.setCharAt(i, Character.toUpperCase(sb.charAt(i)));
			}
		}
		System.out.println(sb);
	}
	
	public static void main(String [] args) {
		String s = "hello,. text test text void dasdfd asda";
		String s1 = "asldkfjlk 100 gsdfg 908 ";
		task1(s);
		task2(s1);
		task3(s);
	}

}
