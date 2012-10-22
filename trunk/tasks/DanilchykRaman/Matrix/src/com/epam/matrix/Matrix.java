package com.epam.matrix;
import java.util.*;

public class Matrix {

	private int[][] matrix;
	private final int dimension;
	
	/** Constructor */
	public Matrix(int dimension) {
		this.matrix = new int[dimension][dimension];
		this.dimension = dimension;
	}
	/** Fill matrix with random integer numbers */
	public void randomFill() {
		Random rand = new Random();
		for (int i = 0; i < this.dimension; i++)
			for (int j = 0; j < this.dimension; j++)
				this.matrix[i][j] = rand.nextInt() % (this.dimension+1);
	}
	/** Print matrix */
	public void print() {
		for (int i = 0; i < this.dimension; i++) {
			System.out.print("[ ");
			for (int j = 0; j < this.dimension; j++) {
				if (this.matrix[i][j] >= 0 && this.matrix[i][j] < 10)
					System.out.print("  " + this.matrix[i][j] + " ");
				else if (this.matrix[i][j] > -10)
					System.out.print(" " + this.matrix[i][j] + " ");
				else
					System.out.print(this.matrix[i][j] + " ");
			}
			System.out.println("]");
		}
	}
	/** Returns dimension of the matrix */
	public int getDimension() {
		return this.dimension;
	}
	/** Guides through the steps for enter dimension */
	public static int dimensionInput() {
		Scanner s = new Scanner(System.in);
		
		System.out.print("Enter the dimension of the matrix: ");
		int dimension;
		if (s.hasNextInt())
			dimension = s.nextInt();
		else {
			System.out.println("Error: an incorect number...");
			s.close();
			return 0;
		}
		
		s.close();
		return dimension;
	}
	
	/** task 1: sorts by a column number */
	public void sort(int col) {
		for (int i = 0; i < this.dimension-1; i++) {
			int iMin = i;
			for (int j = i+1; j < this.dimension; j++) {
				if (this.matrix[j][col] < this.matrix[iMin][col])
					iMin = j;
			}
			
			if (iMin != i)
				for (int j = 0; j < this.dimension; j++) {
					int tmp = this.matrix[i][j];
					this.matrix[i][j] = this.matrix[iMin][j];
					this.matrix[iMin][j] = tmp;
				}
		}
	}
	/** task 2: shifts matrix in the position */
	public void shift(int position) {
		while (position != 0)
		{
			int[] tmp = new int[this.dimension]; 
			
			for (int i = 0; i < this.dimension; i++)
				tmp[i] = matrix[i][this.dimension-1];
			
			for (int j = this.dimension-2; j >= 0; j--)
				for (int i = 0; i < this.dimension; i++)
					this.matrix[i][j+1] = this.matrix[i][j];
			
			for (int i = 0; i < this.dimension; i++)
				this.matrix[i][0] = tmp[i];
			
			position--;
		}
	}
	/** task 3: returns the number of increasing elements of matrix  */
	public int numberOfIncreasingElements() {
		int maxNum = 0;
		
		for (int i = 0; i < dimension; i++) {
			int num = 0;
			for (int j = 0; j < dimension-1; j++) {
				if (matrix[i][j+1] > matrix[i][j]) {
					num++;
					if (num > maxNum) 
						maxNum = num;
				} else
					num = 0;
			}
		}
		maxNum++;
		
		return maxNum;
	}
	/** task 4: returns the sum of elements between two plus elements in each row */
	public int sumOfElements() {
		int sum = 0;		
		
		for (int i = 0; i < dimension; i++) {
			int left = -1;
			int right = -1;
			for (int j = 0; j < dimension; j++) {
				if (matrix[i][j] > 0)
					if (left < 0)
						left = j;
					else {
						right = j;
						break;
					}
			}
			if (left > 0 && right > 0)
				for (int k = left+1; k < right; k++)
					sum += matrix[i][k];
		}
		
		return sum;
	}
	/** task 5: transposes matrix */
	public void transpose() {
		for (int i = 0; i < dimension; i++) {
			for (int j = i; j < dimension; j++) {
				int tmp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = tmp;
			}
		}
	}
}
