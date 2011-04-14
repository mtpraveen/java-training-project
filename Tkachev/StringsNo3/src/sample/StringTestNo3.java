package sample;

import java.util.StringTokenizer;

public class StringTestNo3 {
	private static String text = "Words in this text should start with big letters" +
			" after terminating This Code.";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StringTokenizer st = new StringTokenizer(text," \r\t\n.,:-");
		StringBuffer outputText = new StringBuffer();
		StringBuffer buf;
		
		while (st.hasMoreTokens()) {
			buf = new StringBuffer(st.nextToken());
			byte b = (byte)buf.charAt(0);
			if (b>96 && b<123) {
				buf.setCharAt(0, (char)(b-32));
			}
			outputText.append(' ');
			outputText.append(buf);
		}
		
		System.out.println("Primary text -> " + text);
		System.out.println("Changed text -> " + outputText.append('.').toString().trim());
		
		/*char c = 'a';
		byte b = (byte)c;
		System.out.println(c + " is -> " + b);
		c = (char)(b-32);
		System.out.println(c);*/
	}

}
