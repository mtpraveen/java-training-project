package task_1;

import java.util.Scanner;
import java.util.Random;

public class Array {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Enter n: ");
		Scanner scan = new Scanner(System.in);
		final int n = scan.nextInt();
		scan.close();
		Random rand = new Random(n);
		final int a[][] = new int[n][n];
		for (int i = 0; i < n * n; i++) {
			a[i / n][i % n] = rand.nextInt();
		}

	}

}
