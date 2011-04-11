package EntryPoint;

import stack.*;

/**
 * @author �������
 * ������� �� ���� ���������� �������������� ��������
 * 1. ��� ���������� ����� ������ Stack �������� ��������� �������������� ��������, ��� ��� ����������.
 * 2. �������� ��������� ����� �� ��������� ���� ��������, ����������� ��� ������ �� ������ (���������� ���������� � ���������� ���������, ������������). ����� ������ ���������� � ����� test.
 */

public class EntryPoint {
	/**
	 * @param args
	 * @throws Throwable 
	 */
	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("Start.");
		
		System.out.println("Stack:");
		Stack<Integer> stack = new Stack<Integer>();
		try{ System.out.println("pop: " + stack.pop()); }
		catch(Exception exc){ System.out.println("Exception: " + exc.getMessage()); }
		try{ System.out.println("peek: " + stack.peek()); }
		catch(Exception exc){ System.out.println("Exception: " + exc.getMessage()); }
		try{ System.out.println("toArray: " + stack.toArray(null)); }
		catch(NullPointerException exc){ System.out.println("Exception: " + exc.getMessage()); }
		System.out.println("size: " + stack.size());
		System.out.println("isEmpty: " + stack.isEmpty());
		System.out.println("isMember: " + stack.isMember(null));
		System.out.println("toString: " + stack.toString());
		stack.push(11);
		stack.push(22);
		stack.push(33);
		stack.push(44);
		stack.push(55);
		System.out.println("pop: " + stack.pop());
		System.out.println("peek: " + stack.peek());
		System.out.println("size: " + stack.size());
		System.out.println("isEmpty: " + stack.isEmpty());
		System.out.println("isMember: " + stack.isMember(null));
		System.out.println("toArray: " + stack.toArray(new Integer[0]) + "\nArray:");
		for(Integer i : stack.toArray(new Integer[0]))
			System.out.print(i + " ");
		System.out.println("\ntoString: " + stack.toString());
		
		System.out.println("End.");
	}

}
