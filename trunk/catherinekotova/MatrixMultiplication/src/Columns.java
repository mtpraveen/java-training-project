
public class Columns {
	private Columns() { 
	   } 
	
	   public static Matrix getting(Matrix matrix) { 
		   System.out.print("column1 matrix1"+ " ");
	         for (int i = 0; i < matrix.getRowsNum(); ++i) { 

	        	 System.out.print( matrix.getElement(i, 0) + " ");
	         } 
	      
	      return matrix; 
	   } 
}
