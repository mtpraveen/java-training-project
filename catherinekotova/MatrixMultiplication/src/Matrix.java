

public class Matrix { 
   private final int[][] matrix; 

   public Matrix(int rows, int cols) { 
      
      matrix = new int[rows][cols]; 
   } 

   public void setElement(int row, int column, int value) { 
      
      matrix[row][column] = value; 
   } 

   public int getElement(int row, int column) { 
      
      return matrix[row][column]; 
   } 

   public int getRowsNum() { 
      return matrix.length; 
   } 

   public int getColumnsNum() { 
      return matrix[0].length; 
   } 

   
  
   public Matrix multiply(Matrix multiplier) { 

      int thisRows = getRowsNum(); 
      int thisCols = getColumnsNum(); 

      int otherCols = multiplier.getColumnsNum(); 
      
      Matrix result = new Matrix(thisRows, otherCols); 

      for (int thisRow = 0; thisRow < thisRows; ++thisRow) 
         for (int otherCol = 0; otherCol < otherCols; ++otherCol) 
            for (int thisCol = 0; thisCol < thisCols; ++thisCol) { 
               result.matrix[thisRow][otherCol] += matrix[thisRow][thisCol] 
                     * multiplier.getElement(thisCol, otherCol); 
            } 

      return result; 
   } 

} 