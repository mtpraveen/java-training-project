/**
 * 
 */
import java.util.EmptyStackException;
import java.util.Stack;
/**
 * @author Kalgin I.V.
 *
 */

public class StackHW{
	static void push(Stack<Integer> stack, int a) 
	  { 
	    stack.push(new Integer(a));
	    System.out.println("push(" + a + ")");
	    System.out.println("stack: " + stack);
	  }
	  @SuppressWarnings("unused")
	private void push(Integer integer) {
		// TODO Auto-generated method stub
		
	}
	static void pop(Stack<Integer> stack) 
	  { 
	    System.out.print("pop -> ");
	    Integer a = (Integer) stack.pop();
	    System.out.println(a);
	    System.out.println("stack: " + stack);
	  }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 Stack<Integer> stack = new Stack<Integer>();
		    System.out.println("stack: " + stack);
		    push(stack, 1);
		    push(stack, 2);
		   push(stack, 3);
		    pop(stack);
		   pop(stack);
		   pop(stack);
		    try 
		    {
		      pop(stack);
		    } 
		    catch (EmptyStackException e) 
		    { 
		      System.out.println("empty stack");
		    } 
		// TODO Auto-generated method stub

	}

}
