package com.epam.matrix;

public class Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Matrix matrix = new Matrix(Matrix.dimensionInput());
		matrix.randomFill();
		
		System.out.println("Source matrix:");
		matrix.print();
		
		//taskOne(matrix, 2);
		//taskTwo(matrix, 2);
		//taskThree(matrix);
		//taskFour(matrix);
		//taskFive(matrix);
	}

	/** Methods implement tasks */
	
	private static void taskOne(Matrix matrix, int col) {
		System.out.println("Sorted matrix (" + col + " column):");
		matrix.sort(col-1);
		matrix.print();
	}
	private static void taskTwo(Matrix matrix, int pos) {
		System.out.println("Shifted matrix (" + pos + " column):");
		matrix.shift(pos);
		matrix.print();
	}
	private static void taskThree(Matrix matrix) {
		System.out.print("Number of increasing numbers is ");
		System.out.println(matrix.numberOfIncreasingElements());
	}
	private static void taskFour(Matrix matrix) {
		System.out.print("The sum of elements is ");
		System.out.println(matrix.sumOfElements());
	}
	private static void taskFive(Matrix matrix) {
		System.out.println("Transposed matrix:");
		matrix.transpose();
		matrix.print();
	}
}
