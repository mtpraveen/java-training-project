package task2;

import java.io.*;
import java.util.*;

/**
 * @author Dmitrij Zatsepin
 *
 */

public class t2 {

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
	
	public static void cyclicShift() {
		double mass[][] = new double [n][n];
		int counter=((new Random()).nextInt(n-1));
		int cordinate1;
		int cordinate2;
		for (int i = 0; i < n; i++) {
			cordinate1 = i;
			cordinate2 = (i+counter)%n;
			for (int j = 0; j < n; j++) {
				mass[cordinate2][j]=matrix[cordinate1][j];
			}		
		}
		System.out.println("—двиг на " + counter + " строкa(у)");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(mass[i][j] + "\t");
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
		cyclicShift();
	}
}
