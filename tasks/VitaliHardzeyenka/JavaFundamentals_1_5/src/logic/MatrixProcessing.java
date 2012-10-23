package logic;

import consoleInputOutput.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import org.apache.commons.lang3.ArrayUtils;

/**
 * Main logic. Tasks from 1 to 5, option B.
 * @author Gordeenko
 *
 */
public class MatrixProcessing {

	public void run() {
		System.out.println("Type the matrix dimension..."); // console message outputting
		InputOutput inputOutput = InputOutput.getInstanse(); // create an object
		int dimension = Math.abs(inputOutput.readInt()); // dimension must be a positive number
		
		if (dimension == 0) {
			dimension = 1;
		}
		
		int[][] matrix = initializeMatrix(dimension); // create the matrix				
		printMatrix(matrix);
				
		// Task 1
		System.out.println("Type the column number for swapping...");
		int columnNumber = inputOutput.readInt();
		int[][] matrixTaskOne = sortAscendingOrder(matrix, columnNumber);
		printMatrix(matrixTaskOne);
		
		// Task 2
		System.out.println("Type the count of displasement...");
		int displacement = inputOutput.readInt();
		System.out.println("Type the direction...");
		String direction = inputOutput.readString();
		int[][] matrixTaskTwo = cyclicDisplacement(matrix, displacement, direction);
		printMatrix(matrixTaskTwo);
		
		// Task 3
		System.out.println("Type the property...");
		String property = inputOutput.readString();
		System.out.println(Arrays.toString(printMaxCountIncreasingDecreasingElements(matrix, property)));
		
		// Task 4
		System.out.println(findSumBetweenTwoPositive(matrix));
		
		// Task 5
		printMatrix(transpose(matrix));
		
		
	}
	
	/**
	 * Create the two-dimensional matrix of int 
	 * and fill it with random values between -dimension and dimension.   
	 * @param dimension - size of columns and rows of matrix
	 * @return - two-dimensional matrix of int
	 */
	private int[][] initializeMatrix(int dimension) {
		int[][] matrix = new int[dimension][dimension]; // memory for matrix
		
		Random random = new Random();
		
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) 
			{
				matrix[i][j] = - dimension + random.nextInt(2 * dimension); // random between -dimension and dimension
			}
		}
		
		return matrix;
	}
	
	/**
	 * Task 1.
	 * Sort rows in ascending order of the specified column.
	 * @param matrix - input matrix
	 * @param columnNumber - specifird column.
	 * @return
	 */
	private int[][] sortAscendingOrder(int[][] matrix, int columnNumber) {
		if (columnNumber < matrix[0].length) {		
			int[][] resultMatrix = matrix.clone(); // clone the original matrix
			
			int min = resultMatrix[0][columnNumber];
			int minStringNumber = 0;
			int buffer = 0;
			
			for (int i = 0; i < resultMatrix.length; i++) {				
				min = resultMatrix[i][columnNumber];
				
				// find new minimal value
				for (int j = i; j < resultMatrix.length; j++) {
					if (min > resultMatrix[j][columnNumber]) {
						min = resultMatrix[j][columnNumber];
						minStringNumber = j;
					}					
				}
				
				System.out.println(min + " " + minStringNumber + " " + i);
				
				// swap strings
				for (int k = 0; k < resultMatrix[i].length; k++) {
					buffer = resultMatrix[i][k];
					resultMatrix[i][k] = resultMatrix[minStringNumber][k];
					resultMatrix[minStringNumber][k] = buffer;
				}
			}
			
			return resultMatrix;
		} else {
			System.out.println("The column number if out of matrix bound.");
			return null;
		}
	}
	
	/**
	 * Task 2.
	 * Cyclic displacement of matrix for number positions in right/left/top/bottom direction.
	 * @param matrix - input matrix
	 * @param displacementCount - count of cycles.
	 * @return
	 */
	private int[][] cyclicDisplacement(int[][] matrix, int displacementCount, String direction) {
		int[][] resultMatrix = matrix.clone();
		int[] bufferLine;
		
		for (int i = 0; i < displacementCount; i++) { // count of cycles
			switch (direction) {
				case "right":
					bufferLine = new int[resultMatrix.length]; // create buffer for one cycle copy
					
					// copy the last columnn to buffer
					for (int j = 0; j < resultMatrix.length; j++) {
						bufferLine[j] = resultMatrix[j][resultMatrix[0].length - 1];
					}
					
					// Shift matrix to the one position right.
					for (int j = resultMatrix[0].length - 1; j > 0; j--) { // for columns
						for (int k = 0; k < resultMatrix.length; k++) { // for rows
							resultMatrix[k][j] = resultMatrix[k][j - 1];
						}
					}
					
					// Copy last right column (from buffer) to the first left column.
					for (int j = 0, k = 0; j < resultMatrix.length; j++, k++) {
						resultMatrix[j][0] = bufferLine[k];
					}					
					break;
				case "left":
					break;
				case "top":
					break;
				case "bottom":
					break;
				default:
					System.out.println("Incorrect direction!");
					break;
			}
		}
		
		return resultMatrix;
	}
	
	/**
	 * Task 3
	 * Return the largest sequence of increasing/decreasing elements.
	 * @param matrix - input matrix
	 * @param property - increasing/decreasing choose.
	 * @return - one-dimension matrix of max sequence.
	 */
	private int[] printMaxCountIncreasingDecreasingElements(int[][] matrix, String property) {
		ArrayList<Integer> sequence = new ArrayList<>(); // current sequence
		ArrayList<Integer> maxSequence = new ArrayList<>(); // largest sequence
		
		switch(property) {
			case "increasing":
				int previousElement = matrix[0][0];
				
				for (int[] row : matrix) { // for every row in matrix
					for (int element : row) { // for every element in row
						if (element > previousElement) {
							sequence.add(previousElement);
							previousElement = element;
						} else {
							// add the last element of sequence to ArrayList
							sequence.add(previousElement);
							previousElement = element;
							
							if (sequence.size() > maxSequence.size()) {
								maxSequence.clear();
								maxSequence = sequence;
							}
							sequence.clear();
						}
					}
				}
				break;
			case "decreasing":
				break;
			default:
				System.out.println("Incorrect typed property!");
				break;
		}
		
		return ArrayUtils.toPrimitive(maxSequence.toArray((new Integer[maxSequence.size()])));
	}
	
	/**
	 * Task 4
	 * Find sum between first two positive numbers if current row of matrix.
	 * @param matrix - input  matrix
	 * @return - sum of founded elements.
	 */
	private int findSumBetweenTwoPositive(int[][] matrix) {
		int sum = 0, rowSum = 0;
		boolean condition = false; // true - current element is between two first positive numbers; else - false
						
		for (int[] row : matrix) {
			condition = false;
			rowSum = 0;
						
			label: for (int element : row) {
				if ((element > 0) && (condition == false)) { // find first positive number
					condition = true;
					continue;
				}
				
				if ((element > 0) && (condition == true)) { // find second positive number
					condition = false;
					break label; // not need to calculate sum of elements in current row after second positive number
				}
				
				if (condition == true) {
					rowSum += element;
				}
			}
			
			if (condition == false) {
				sum += rowSum;
			}
		}
						
		return sum;
	}
	
	/**
	 * Task 5
	 * Transpose the input matrix.
	 * @param matrix - input matrix
	 * @return
	 */
	private int[][] transpose(int inputMatrix[][]) {
		int[][] matrix = inputMatrix.clone();
		int[][] resulMatrix = new int[matrix[0].length][matrix.length];
		
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix.length; j++) {
            	resulMatrix[i][j] = matrix[j][i];
            }
        }
        return resulMatrix;
	}

	
	/**
	 * Print matrix on the console.
	 * @param matrix
	 */
	private void printMatrix(int[][] matrix) {
		for (int[] row : matrix) {
			System.out.println(Arrays.toString(row));
		}
	}		
}
