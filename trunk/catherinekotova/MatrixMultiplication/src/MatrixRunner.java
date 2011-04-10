public class MatrixRunner { 

  
   public static void main(String[] args)  { 

      Matrix first = new Matrix(2, 2); 

      MatrixUtil.fill(first); 
      MatrixUtil.print(first); 
      Matrix second = new Matrix(2, 3); 
      MatrixUtil.fill(second); 
      MatrixUtil.print(second); 

      Matrix mul = first.multiply(second); 

      MatrixUtil.print(mul); 
      
      Rows.getting (first);
      Columns.getting (first);
      Element.getting (first);


   } 
}
