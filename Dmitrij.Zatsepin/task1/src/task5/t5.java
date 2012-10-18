package task5;

import java.io.*;

/**
 * @author Dmitrij Zatsepin
 *
 */

public class t5 {

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
	
	public static void transp() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < i; j++) {
				double temporary = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = temporary;
			}
		}
		System.out.println("Transposed matrix: ");
		printMatrix();
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
		transp();
	}
}
