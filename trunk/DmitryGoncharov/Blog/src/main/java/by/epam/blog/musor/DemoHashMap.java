package by.epam.blog.musor;

import java.util.*;

import java.util.*;

public class DemoHashMap {
	public static void main(String[] args) {
		Map hm = new HashMap(5);
		for (int i = 1; i < 10; i++)
			hm.put(Integer.toString(i), i + " element");
		hm.put("14s", new Double(1.01f));
		System.out.println(hm);
		hm.put("5", "NEW");
		System.out.println(hm + "с заменой элемента ");
		Object a = hm.get("5");
		System.out.println(a + " - найден по ключу '5'");
		/* вывод хэш-таблицы с помощью методов интерфейса Map.Entry */
		Set set = hm.entrySet();
		Iterator i = set.iterator();
		while (i.hasNext()) {
			Map.Entry me = (Map.Entry) i.next();
			System.out.print(me.getKey() + " : ");
			System.out.println(me.getValue());
		}
	}
}