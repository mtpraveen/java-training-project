package task1;

import java.io.*;
import java.util.*;

/**
 * @author Dmitrij Zatsepin
 *
 */

public class t1 {

	/**
	 * @param args
	 * @throws IOException 
	 */

	public static int n;
	public static double matrix[][];
	public static double copymatrix[][];

	
	public static double my_rand() {
		return -n + Math.random()*2*n;
	}
	
	public static void initMatrix() {
		matrix = new double [n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				matrix[i][j] = Math.round(10*my_rand())/10.0;
			}
		}
	}
	
	public static void printMatrix() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(matrix[i][j] + "\t");
			}
		System.out.println();
		}
	}
	
	public static void ChangeRows(int row1, int row2, double matrix[][])
	{
		for (int i = 0;i<n;i++)
		{
			double tmp = matrix[row1][i];
			matrix[row1][i] = matrix[row2][i];
			matrix[row2][i] = tmp;
		}
	}
	public static void ChangeCols(int col1, int col2, double matrix[][])
	{
		for (int i = 0;i<n;i++)
		{
			double tmp = matrix[i][col1];
			matrix[i][col1] = matrix[i][col2];
			matrix[i][col2] = tmp;
		}
	}
	
	public static void sort_row(int row) {
		for (int i = 0;i<n-1;i++) {
			for(int j = 0;j<n-i-1;j++) {
			if (matrix[row][j] > matrix[row][j+1])
					ChangeCols(j, j+1, matrix);
			}
		}
		System.out.println("The array is sorted by " + row + " row");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(matrix[i][j] + "\t");
			}
		System.out.println();
		}
	}
	
	public static void sort_col(int col) {
		for (int i = 0;i<n-1;i++) {
			for(int j = 0;j<n-i-1;j++) {
			if (matrix[col][j] > matrix[col][j+1])
					ChangeRows(j, j+1, matrix);
			}
		}
		System.out.println("The array is sorted by " + col + " column");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(matrix[i][j] + "\t");
			}
		System.out.println();
		}
	}
	
	public static void main(String[] args) throws IOException {
		System.out.print("Enter the dimension of the matrix (n) and click <Enter>: ");
		LineNumberReader reader = new LineNumberReader(new InputStreamReader(System.in));
			String l1 = reader.readLine();
			n = Integer.parseInt(l1);
		System.out.println();
		System.out.println("-----------------------------");
		
		initMatrix();
		printMatrix();
		System.out.println("-----------------------------");
		System.out.println();
		double copymatrix[][] = new double [n][n];
		System.arraycopy(matrix, 0, copymatrix, 0, copymatrix.length);
		sort_row((new Random()).nextInt(n));
		System.out.println("-----------------------------");
		System.out.println();
		sort_col((new Random()).nextInt(n));
		System.out.println("-----------------------------");
		System.out.println();
	}
}
