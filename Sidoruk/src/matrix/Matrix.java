package matrix;
import java.util.Arrays;
import java.util.Scanner;

public class Matrix {

	private long[][] matrix;
	private int rows;
	private int columns;

	public void setMatrix() {

		Scanner scaner = new Scanner(System.in);

		System.out.print("Enter number of rows ");
		rows = scaner.nextInt();

		System.out.print("Enter number of columns ");
		columns = scaner.nextInt();

		matrix = new long[rows][columns];

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.print("M[" + i + "]" + "[" + j + "]=");
				matrix[i][j] = scaner.nextLong();
			}
		}
	}
	
	public int getRows()
	{
		return rows;
	}

	public int getColumns()
	{
		return columns;
	}
	
	public void showMatrix() {
		for (int i = 0; i < getRows(); i++) {
			for (int j = 0; j < getColumns(); j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

	public long[] getRow(int index) {
		if (index < getRows()) {
			return matrix[index];
		} 
		return null;
	}
	
	public long[] getColumn(int index) {
		if (index < getColumns() )
		{
			long[] tmp = new long[ getColumns() + 1 ];
			for( int i = 0; i <= getColumns(); i++ )
			{
				tmp[i] = matrix[i][index];
			}
			return tmp;
		}
		return null;
	}

	public long[][] multiply(Matrix mtr) {
		if( getColumns() == mtr.getRows() )
		{
			long[][] tmp = new long[getRows()][mtr.getColumns()];
			for( int i = 0; i < getRows(); i++ )
			{
				for( int j = 0; j < mtr.getColumns(); j++ )
				{
					tmp[i][j] = multiplyRowAndColumn(getRow(i), mtr.getColumn(j)); 
				}
			}
			return tmp;
		}
		return null;
	}

	private long multiplyRowAndColumn(long[] row, long[] column)
	{
		long tmp = 0;
		for( int i = 0; i <= getRows(); i++ )
		{
			tmp += row[i] * column[i];
		}
		return tmp;
	}
	
	public void setMatrix( long[][] mtr)
	{
		for(int i = 0; i < mtr.length; i++)
		{
			matrix[i] = new long[mtr[i].length]; 
			matrix[i] = Arrays.copyOf(mtr[i], mtr[i].length);
		}
	}
	
	public static void main(String[] args) {
		Matrix matrix1 = new Matrix();
		matrix1.setMatrix();
		matrix1.showMatrix();
		
		Matrix matrix2 = new Matrix();
		matrix2.setMatrix();
		matrix2.showMatrix();
		
		Matrix matrix3 = new Matrix();
		matrix3.setMatrix(matrix1.multiply(matrix2));
		matrix3.showMatrix();
	}
}
