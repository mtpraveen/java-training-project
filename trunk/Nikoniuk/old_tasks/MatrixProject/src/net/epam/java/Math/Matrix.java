/**
 * The code is licensed under GPL v.3.
 */
package net.epam.java.Math;

import java.util.Random;

/**
 * <p>
 * The class <code>Matrix</code> contains methods for performing basic
 * operations, such as:
 * </p>
 * <ul>
 * <li>multiplication by matrix or number
 * <li>getting column, row or element by it's index
 * </ul>
 * 
 * @author Alexander Nikoniuk
 * @see java.lang.Math
 * 
 */
public final class Matrix {

	/**
	 * Allocates memory for Matrix object's data
	 * 
	 * @param rowsCount
	 *            Number of rows in the Matrix object
	 * 
	 * @param colsCount
	 *            Number of columns in the Matrix object
	 */
	public Matrix(int rowsCount, int colsCount) {
		data = new double[rowsCount][colsCount];
		this.rowsCount = rowsCount;
		this.colsCount = colsCount;
	}

	/**
	 * Allocates memory for Matrix object's data with random numbers, if it is
	 * necessary
	 * 
	 * @param rowsCount
	 *            Number of rows in the Matrix object
	 * 
	 * @param colsCount
	 *            Number of columns in the Matrix object
	 * @param random
	 *            Fill the Matrix object with random numbers
	 */
	public Matrix(int rowsCount, int colsCount, boolean random) {
		this(rowsCount, colsCount);

		if (random) {
			Random rnd = new Random();
			for (int i = 0; i < rowsCount; i++) {
				for (int j = 0; j < colsCount; j++) {
					data[i][j] = rnd.nextInt(10);
				}
			}
		}
	}

	/**
	 * 
	 * @see java.lang.Object#toString() TODO add string format when appending
	 *      data to result
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < rowsCount; i++) {
			for (int j = 0; j < colsCount; j++) {
				result.append(data[i][j] + " ");
			}
			result.append("\n");
		}
		return result.toString();
	}

	/**
	 * Array representation of Matrix data
	 * 
	 * @return Matrix data array
	 */

	public double[][] toArray() {
		return data;
	}

	/**
	 * Constructs a Matrix.IncompatibleSizes exception with the specified detail
	 * message.
	 * 
	 * @param message
	 *            The detail message
	 */
	@SuppressWarnings("serial")
	public static class IncompatibleSizes extends Exception {
		public IncompatibleSizes(String s) {
			super(s);
		}
	}

	/**
	 * Enumeration of Matrix dimensions.
	 */
	public static enum Dimensions {
		ROWS, COLS
	}

	/**
	 * Getter for data element
	 * 
	 * @param i
	 *            Row index
	 * @param j
	 *            Column index
	 * @return Data element at position specified by parameters.
	 */
	public double get(int i, int j) {
		return data[i][j];
	}

	/**
	 * Setter for data element
	 * 
	 * @param i
	 *            Row index
	 * @param j
	 *            Column index
	 * @param value
	 *            Value to set to the element
	 */
	public void set(int i, int j, double value) {
		data[i][j] = value;
	}

	/**
	 * Get Matrix column by it's index
	 * 
	 * @param col
	 *            Column index
	 * @return Matrix column
	 */
	public Matrix getColumn(int col) {
		Matrix result = new Matrix(rowsCount, 1);
		for (int i = 0; i < rowsCount; i++) {
			result.set(i, 0, data[i][col]);
		}

		return result;
	}

	/**
	 * Get Matrix row by it's index
	 * 
	 * @param row
	 *            Row index
	 * @return Matrix row
	 */
	public Matrix getRow(int row) {
		Matrix result = new Matrix(1, colsCount);
		for (int j = 0; j < colsCount; j++) {
			result.set(0, j, data[row][j]);
		}

		return result;
	}

	/**
	 * Multiplies current Matrix by the matrix specified in the parameter
	 * 
	 * @param matrix
	 *            Multiplies current Matrix by the matrix specified in the
	 *            matrix parameter
	 * 
	 * @throws IncompatibleSizes
	 *             If <i>number of rows</i> of the current Matrix is not equal
	 *             to <i>number of columns</i> of the matrix parameter(unable to
	 *             multiply such matrixes)
	 * @return result of multiplying by matrix.
	 */
	public Matrix multiply(Matrix matrix) throws IncompatibleSizes {
		if (colsCount != matrix.rowsCount)
			throw new IncompatibleSizes("Matrix " + rowsCount + "x" + colsCount
					+ " can't be multiplied by Matrix " + matrix.rowsCount
					+ "x" + matrix.colsCount);

		Matrix result = new Matrix(rowsCount, matrix.colsCount);

		for (int i = 0; i < result.rowsCount; i++) {
			for (int j = 0; j < result.colsCount; j++) {
				for (int k = 0; k < rowsCount; k++)
					result.data[i][j] += data[i][k] * matrix.data[k][j];
			}
		}

		return result;
	}

	/**
	 * Multiplies current Matrix by the given numerical value
	 * 
	 * @param number
	 *            Multiplies current Matrix by the number specified in the
	 *            parameter
	 * @return result of multiplying on matrix.
	 */
	public Matrix multiply(double number) {
		Matrix result = new Matrix(rowsCount, colsCount);

		for (int i = 0; i < result.rowsCount; i++) {
			for (int j = 0; j < result.colsCount; j++) {
				result.data[i][j] = data[i][j] * number;
			}
		}

		return result;
	}

	/**
	 * Returns the size of the Matrix on dimension
	 * 
	 * @param dimension
	 *            {@link Dimensions}
	 * 
	 * @return size of the Matrix on dimension.
	 */
	public int size(Dimensions dimension) {
		switch (dimension) {
		case ROWS:
			return rowsCount;
		case COLS:
			return colsCount;
		default:
			return 0;
		}
	}

	// ============ privates ============

	/* The value is used for matrix data storage. */
	private final double[][] data;

	/* Count of rows. */
	private final int rowsCount;

	/* Count of columns. */
	private final int colsCount;

	/**
	 * Program entry point
	 * 
	 * @param args
	 *            Program command line arguments
	 */

	public static void main(String[] args) {
		Matrix m1 = new Matrix(2, 3);
		try {
			m1.set(0, 0, 1);
			m1.set(0, 1, 5);
			m1.set(1, 0, 3);
			m1.set(1, 2, 2);
			m1.set(2, 0, 3); // error!
		} catch (IndexOutOfBoundsException e) {
			System.err.println("m1 IndexOutOfBounds:" + e.getMessage()
					+ "\nindexes can't be greater than "
					+ (m1.size(Matrix.Dimensions.ROWS) - 1) + " x "
					+ (m1.size(Matrix.Dimensions.COLS) - 1) + "\n");
		}

		System.out.println("printing m1");
		System.out.println(m1);

		System.out.println("printing first row of m1");
		System.out.println(m1.getRow(0));

		System.out.println("printing second column of m1");
		System.out.println(m1.getColumn(1));

		System.out.println("printing m1 * 0.5");
		System.out.println(m1.multiply(0.5));

		Matrix m2 = new Matrix(3, 4, true);
		System.out.println("printing m2");
		System.out.println(m2);

		try {
			System.out.println("printing m1 * m2");
			System.out.println(m1.multiply(m2));

			System.out.println("trying to print m2 * m1");
			System.out.println(m2.multiply(m1)); // exception!
		} catch (Matrix.IncompatibleSizes e) {
			System.err.println("m2 * m1: " + e.getMessage());
		}

	}
}