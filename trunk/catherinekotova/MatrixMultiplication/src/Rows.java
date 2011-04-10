
public final class Rows {
	private Rows() { 
	   } 

	   public static Matrix getting(Matrix matrix) { 
		   System.out.print("row1 matrix1"+" ");
	         for (int j = 0; j < matrix.getColumnsNum(); ++j) { 

	        	 System.out.print(matrix.getElement(0, j) + " ");
	         } 
	      
	      return matrix; 
	   } 
}
