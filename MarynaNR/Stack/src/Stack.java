/**
 * @author Мара
 *
 */

import java.util.ArrayList;

public class Stack<E> implements IStack<E> {
	public Element<E> head;
	public int size;

	public class Element<E> {

		public Element<E> previous;
		private E value;

		public Element<E> getPrevious() {
			return previous;
		}

		public void setPrevious(Element<E> previous) {
			this.previous = previous;
		}

		public E getValue() {
			return value;
		}

		public void setValue(E value) {
			this.value = value;
		}
	}

	public void push(E newElement) {
		Element<E> element = new Element<E>();
		element.setValue(newElement);
		element.setPrevious(head);
		head = element;
		size++;
	}

	public E pop() {
		Element<E> element = new Element<E>();
		E value = head.value;
		head = element.getPrevious();
		size--;
		return value;
	}

	@Override
	public E top() {
		return head.getValue();
	}

	public Element<E> getHead() {
		return head;
	}

	public E[] toArray(E[] array) {
		Element<E> pointer;
		ArrayList<E> myArray = new ArrayList<E>();
		pointer = (Element<E>) this.getHead();
		while (pointer.previous != null) {
			myArray.add(pointer.getValue());
			pointer = pointer.getPrevious();
		}
		myArray.add(pointer.getValue());
		E[] array3 = myArray.toArray(array);
		return array3; // myArray.toArray(array);
	}

	public int getSize(Integer[] array3) {
		return array3.length;
	}

	public boolean isEmpty(Integer[] array3) {
		boolean f = false;
		if (array3.length == 0) {
			f = true;
			System.out.println("Stack is Empty");
		} else
			System.out.println("NotEmpty");// - " + array3.length + "
											// элемента");
		return f;
	}

	private static int getButtonValue() {
		int val = 0, // введенное значение
		len = 0; // реальное количество введенных символов
		byte b[] = new byte[9]; // массив для хранения кодов введенных символов
		int by = 0; // вспомогат. переменная для ввода кода очередного символа
		try {
			System.in.skip(System.in.available()); // метод available
													// возвр.число символов в
													// буфере, а skip - удаляет
													// их
			while (true) // число может содержать несколько символов
			{
				by = System.in.read(); // код очередного символа
				// первым символом (len == 0) может быть символ Enter, пробел
				// или не цифра
				if (by == 13 || by == 32 || by < 48 || by > 57)
					break;
				b[len++] = (byte) (by - 48); // превращаем код символа в число
				if (len > 8)
					break;// слишком много символов в буфере клавиатуры
			}
		} catch (java.io.IOException e) {
		}
		int ten = 1; // для преобразования последовательности цифр в число 10 в
						// степени, соответствующей позиции цифры в числе
		for (int i = len - 1; i >= 0; i--) // от последней цифры - к первой
		{
			val += b[i] * ten;
			ten *= 10;
		}
		return val;
	}

	public boolean isMember(E elem, Integer[] array) {
		boolean f = false;
		for (int i = 0; i < array.length; i++) {
			if (elem == array[i])
				f = true;
		}
		return f;
	}

	/*
	 * public E[] asArray() { Element<E> pointer; ArrayList<E> myArray = new
	 * ArrayList<E>(); pointer = this.getHead(); while (pointer.previous !=
	 * null) { myArray.add(pointer.getValue()); pointer = pointer.getPrevious();
	 * } myArray.add(pointer.getValue());
	 * 
	 * // for (int i=0; i<myArray.size();i++) int i = myArray.size(); while (i
	 * != 0) { Integer j1 = (Integer) myArray.get(i - 1);
	 * System.out.println(j1); i--; } return null; }
	 */

	/*
	 * public boolean isEmpty2() { boolean f; Element<E> pointer;
	 * 
	 * ArrayList<E> myArray = new ArrayList<E>(); pointer = this.getHead();
	 * while (pointer.previous != null) { myArray.add(pointer.getValue());
	 * pointer = pointer.getPrevious(); } myArray.add(pointer.getValue()); if
	 * (myArray.isEmpty()) { System.out.println("Empty"); f = false; } else {
	 * System.out.println("NotEmpty - " + myArray.size() + " элемента"); f =
	 * true; } return f; }
	 */

	/*
	 * public int getSize2() { Element<E> pointer;
	 * 
	 * ArrayList<E> myArray = new ArrayList<E>(); pointer = this.getHead();
	 * while (pointer.previous != null) { myArray.add(pointer.getValue());
	 * pointer = pointer.getPrevious(); } myArray.add(pointer.getValue());
	 * return myArray.size(); }
	 */

	public static void invite() {
		System.out.println("0 - Выход");
		System.out.println("1 - Стек пустой?");
		System.out.println("2 - Определить размер стека");
		System.out.println("3 - Вывести элементы");
		System.out.println("4 - Вывести вершину");
		System.out.println("5 - Принадлежит стеку?");
	}

	public static void main(String[] args) {
		int j = 0;

		Stack<Integer> s = new Stack<Integer>();
		System.out.println("Введите элементы стека, разделяя их символом Enter.");
		System.out.println("По окончанию введите 0.");
		j = getButtonValue();
		while (j != 0) {
			s.push(j);
			j = getButtonValue();
		}
		invite();

		Integer[] array2 = null;
		array2 = s.toArray(new Integer[] {}); // преобразовали в массив

		int by = 0;
		try {
			while (true) // число может содержать несколько символов
			{
				by = System.in.read(); // код очередного символа
				int code = by - 48; // превращаем код символа в число
				if (code == 0) {
					System.out.println("Вуе");
					break;
				} else if (code == 1) {
					s.isEmpty(array2);
				} else if (code == 2) {
					System.out.println("Количество элементов стека - "
							+ s.getSize(array2));
				} else if (code == 3) {
					{
						for (int i = 0; i < array2.length; i++)
							System.out.print(array2[i] + " ");
					}
					System.out.println(" ");
				} else if (code == 4) {
					System.out.println("Вершина стека - " + s.top());
				} else if (code == 5) {
					System.out.println("Введите число для проверки. Нажмите Enter. ");
					System.out.println("По окончании нажмите 0");
					j = getButtonValue();
					while (j != 0) {
						boolean f = s.isMember(j, array2);
						if (f == true)
							System.out.println("Принадлежит стеку");
						else
							System.out.println("Не принадлежит стеку");
						j = getButtonValue();
					}
					System.out.println("Выход. Проверка завершена");
					System.out.println("");
					invite();
				}

			}
		} catch (java.io.IOException e) {
		}

	}

}
