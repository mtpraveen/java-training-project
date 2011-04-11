/**
 * 
 */

/**
 * @author Евгений
 *
 */
public class Matrix {
	private double[][] matrix;
	public Matrix(){
		matrix = new double[5][5];
	}
	public Matrix(int rows, int columns){
		matrix = new double[rows][columns];
	}
	public void setElement(double element, int row, int column){
		matrix[row][column] = element;
	}
	public double getElement(int row, int column){
		return matrix[row][column];
	}
	public Matrix Multiply(Matrix lhs, Matrix rhs){
		Matrix temp = new Matrix(lhs.getRowsCount(), rhs.getColumnsCount());
		double buf;
		for(int i = 0; i < temp.getRowsCount(); i++)
			for (int j = 0; j < temp.getColumnsCount(); j++){
				buf = 0;
				for (int k = 0; k < lhs.getRowsCount(); k++)
					buf += lhs.getElement(i, k) * rhs.getElement(k, j);
				temp.setElement(buf, i, j);
			}
		return temp;
	}
	public Matrix Multiply(Matrix lhs, double rhs){
		Matrix temp = new Matrix(lhs.getRowsCount(), lhs.getColumnsCount());
		for (int i = 0; i < temp.getRowsCount(); i++)
			for (int j = 0; j < temp.getColumnsCount(); j++)
				temp.setElement(lhs.getElement(i, j) * rhs, i, j);
		return temp;
	}
	public Matrix getRow(int rowNumber){
		Matrix temp = new Matrix(1, matrix[0].length);
		for (int i = 0; i < temp.getColumnsCount(); i++)
			temp.setElement(matrix[rowNumber][i], 0, i);
		return temp;
	}
	public Matrix getColumn(int columnNumber){
		Matrix temp = new Matrix(matrix.length, 1);
		for (int i = 0; i < temp.getRowsCount(); i++)
			temp.setElement(matrix[i][columnNumber], i, 0);
		return temp;
	}
	public int getRowsCount(){
		return matrix.length;
	}
	public int getColumnsCount(){
		return matrix[0].length;
	}
	public String toString(){
		StringBuilder sb = new StringBuilder("");
		for (double[] i : matrix){
			for (double j : i){
				sb.append(j + ", "); 
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Start.");
		Matrix matrix = new Matrix(5, 5);
		for(int i = 0; i < 5; i++)
			for (int j = 0; j < 5; j++)
				matrix.setElement(i + j, i, j);
		System.out.println("matrix:\n" + matrix.toString());
		System.out.println("matrix * matrix:\n" + matrix.Multiply(matrix, matrix).toString());
		System.out.println("matrix * 10:\n" + matrix.Multiply(matrix, 10));
		System.out.println("first row:\n" + matrix.getRow(0));
		System.out.println("first column:\n" + matrix.getColumn(0));
		System.out.println("End.");
	}

}
