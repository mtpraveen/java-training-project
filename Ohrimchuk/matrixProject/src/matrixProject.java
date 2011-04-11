/**
 * 
 * @author Valery Ohrimchuk
 * April 2011
 */
public class matrixProject {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Matrix a = new Matrix(3, 4);
		double[][] aArr = { { 2, 6, 0, 4 }, { 2, 0, 3, 2 }, { 1, 2, 3, 0 } };
		
		a.setContent(aArr);
		
		System.out.println("Matrix a\n" + a);
		
		
		a.multNumber(0.5);
		System.out.println("Matrix a after multiplication on 0.5\n" + a);
		

		
		// Second matrix
		Matrix b = new Matrix(4, 2);
		double[][] bArr = { { 1, 2 }, { 2, 0 }, { 0, 3 }, { 3, 1 } };
		b.setContent(bArr);
		System.out.println("Matrix b\n" + b);

		
		// Multiplication
		try {
			System.out.println("Multiplication a on b\n" + a.multMatrix(b));
		} catch (ArithmeticException e) {
			e.printStackTrace();
		}
		try {
			System.out.println("ArithmeticException multiplication b on a\n" + b.multMatrix(a));
		} catch (ArithmeticException e) {
			System.out.println(e);
			//e.printStackTrace();
		}

		
		// Export column
		System.out.println("Column 2 in a (begin at 0)");
		double[] c = a.getColArray(2);
		for (int i = 0; i < c.length; i++)
			System.out.print(c[i] + " ");
		System.out.println();

		// Export row
		System.out.println("Row 2 in a (begin at 0)");
		c = a.getRowArray(2);
		for (double elem : c)
			System.out.print(elem + " ");
	}
}
