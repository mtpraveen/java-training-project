package task3;

import java.io.*;

/**
 * @author Dmitrij Zatsepin
 *
 */

public class t3 {

	/**
	 * @param args
	 * @throws IOException 
	 */

	public static int n;
	public static double matrix[][];
	
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
	
	public static void Elements() {
		int Elem = 0;
		int TempElem = -1;
		for (int i = 0; i < n; i++)
		{
			int qw = 0;
			while (qw < n)
			{
			int Len = sted(i, qw);
				if (Len > Elem)
				{
					Elem = Len;
					TempElem = i;
				}
				Elem = Math.max(TempElem, Len);
				qw += Len;
			}
		}
		if (Elem < 2)
			System.out.println("No consecutive increasing elements in the matrix!");
		else
			System.out.println("The largest number of the entries increasing: " + Elem + " (in row - " + TempElem + ")");
	}
	
	public static int sted (int row,int qw)
	{
		int result = 1;
		while ((qw + result < n) && (matrix[row][qw + result - 1] < matrix[row][qw + result]))
			result++;
		return result;
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
		Elements();
	}
}
