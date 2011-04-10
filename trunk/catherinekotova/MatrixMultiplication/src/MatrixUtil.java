
public final class MatrixUtil {
	 private MatrixUtil() { 
	   } 

	   public static Matrix fill(Matrix matrix) { 
	      for (int i = 0; i < matrix.getRowsNum(); ++i) { 
	         for (int j = 0; j < matrix.getColumnsNum(); ++j) { 

	            matrix.setElement(i, j, (int) (Math.random() * 10)); 
	         } 
	      } 
	      return matrix; 
	   } 
	   public static void print(Matrix matrix) { 
	      int rows = matrix.getRowsNum(); 
	      int cols = matrix.getColumnsNum(); 
	      System.out.println("Matrix dimensions: [" + rows + "; " + cols + "]"); 
	      for (int i = 0; i < rows; ++i) { 
	         for (int j = 0; j < cols; ++j) { 
	            System.out.print(matrix.getElement(i, j) + " "); 
	         } 
	         System.out.println(); 
	      } 
	   } 


}
