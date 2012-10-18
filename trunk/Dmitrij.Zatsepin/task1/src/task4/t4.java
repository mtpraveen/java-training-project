package task4;

import java.io.*;

/**
 * @author Dmitrij Zatsepin
 *
 */

public class t4 {

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
	
	public static int FindIndex(int temp,int row)
	{
		for (int i = temp;i<n;i++)
			if (matrix[row][i] > 0)
				return i;
		return -1;
	}

	public static void SummElem() {
		for (int i=0;i<n;i++)
		{
			System.out.print("Row : " + i + "\t");
			int cordinate2 = -1;
			int cordinate1 = FindIndex(0, i);
			if (cordinate1 != -1)
				cordinate2 = FindIndex(cordinate1+1, i);
			if (cordinate2 != -1)
			{
				double sum = 0;
				for (int j = cordinate1 + 1;j < cordinate2; j++)
					sum += matrix[i][j];
				System.out.println(sum);
			}
			else
				System.out.println("-");
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
		SummElem();
	}
}
