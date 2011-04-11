/**
 * 
 */
package entryPoint;
import java.util.*;

/**
 * @author guest
 * Задание "Работа с текстом"
 * 1.	Напечатать без повторения слова текста, в которых первая и последняя буквы совпадают
 * 2.	Определить сумму всех целых чисел, встречающихся в данном тексте
 * 3.	Преобразовать текст так, чтобы каждое слово начиналось с заглавной буквы
 */
public class EntryPoint {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Start.");
		
		System.out.println("Task1:");
		String temp = "121,121 121	456:7.  88 1.0 jj";
		for(String str : EntryPoint.getWordsWithFirstCharEqualsLastChar(temp))
			System.out.println(str);
		
		System.out.println("Task2:");
		temp = "---3--..121.888fdfd1.-.-.-.1.1.;44-;  1.-0";
		System.out.println(getSumAllIntegers(temp));
		
		System.out.println("Task3:");
		temp = "123 123:123;123	projector peris hilton";
		System.out.println(transformTextAllWordsStartFromUpperCase(temp));
		
		System.out.println("End.");
	}
	
	//In all methods below splitters of words was chosen "\t\n\r:;,.!? "
	private static String[] getWordsWithFirstCharEqualsLastChar(String text){
		String temp;
		Set<String> set = new HashSet<String>();
		StringTokenizer tokenizer = new StringTokenizer(text, "\t\n\r:;,.!? ");
		while (tokenizer.hasMoreTokens()){
			temp = tokenizer.nextToken();
			if (temp.charAt(0) == temp.charAt(temp.length() - 1))
				set.add(temp);
				
		}
		return set.toArray(new String[0]);
	}
	
	//ignore decimals (with '.' as splitter of decimal) & include negative integers
	private static int getSumAllIntegers(String text){
		StringBuilder temp = new StringBuilder("");
		List<String> resultList = new ArrayList<String>();
		int result = 0;
		Integer tempInt;
		for (int i = 0; i < text.length(); i++){
			if ((text.charAt(i) >= 48 && text.charAt(i) <= 57) || (text.charAt(i) == '.') || (text.charAt(i) == '-')){
				if (temp.length() > 0 && (temp.charAt(temp.length() - 1) == '.' || temp.charAt(temp.length() - 1) == '-') && (text.charAt(i) == '.' || text.charAt(i) == '-')){
					temp.deleteCharAt(temp.length() - 1);
					if (temp.length() > 0)
						resultList.add(temp.toString());
					temp = new StringBuilder("");
					if (text.charAt(i) == '-')
						temp.append("-");
					continue;
				}
					
				if (temp.length() == 0 && text.charAt(i) == '.')
					continue;
				if (text.charAt(i) == '-' && temp.length() > 0){
					resultList.add(temp.toString());
					temp = new StringBuilder("");
					temp.append("-");
					continue;
				}
				temp.append(text.charAt(i));
			}
			else
			{
				if (temp.length() > 0 && (temp.charAt(temp.length() - 1) == '.' || temp.charAt(temp.length() - 1) == '-'))
					temp.deleteCharAt(temp.length() - 1);
				if (temp.length() > 0)
					resultList.add(temp.toString());
				temp = new StringBuilder("");
			}
		}
		
		if (temp.length() > 0 && (temp.charAt(temp.length() - 1) == '.' || temp.charAt(temp.length() - 1) == '-'))
			temp.deleteCharAt(temp.length() - 1);
		if (temp.length() > 0)
			resultList.add(temp.toString());
		temp = new StringBuilder("");
			
		for(String s : resultList){
			try{
				tempInt = Integer.parseInt(s);
				result += tempInt;
			}
			catch (NumberFormatException exc){
				System.out.println("Exception: double value!");
			}
		}
		return result;
	}

	private static String transformTextAllWordsStartFromUpperCase(String text){
		String temp;
		String result = text;
		StringTokenizer tokenizer = new StringTokenizer(text, "\t\n\r:;,.!? ");
		StringBuilder resultWord = new StringBuilder();
		StringBuilder character = new StringBuilder(1);
		while (tokenizer.hasMoreTokens()){
			temp = tokenizer.nextToken();
			character.append(temp.charAt(0));
			resultWord.append(character.toString().toUpperCase());
			character.deleteCharAt(0);
			resultWord.append(temp.substring(1));
			result = result.replaceAll(temp, resultWord.toString());
			resultWord.delete(0, resultWord.length());
		}
		return result;
	}
}