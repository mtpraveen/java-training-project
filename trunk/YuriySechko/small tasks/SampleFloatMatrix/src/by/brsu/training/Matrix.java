package by.brsu.training;
/**
 * 
 */

/** 
 * @author yura
 *
 */
public class Matrix {
	
	float[][] elements;
	int height;
	int width;

	
	/**
	 *creates matrix (height x width) and fill it random numbers
	 */
	public Matrix(int height, int width) {
		super();
		this.width = width;
		this.height = height;
		elements = new float[height][width];
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				elements[i][j] = (float)Math.random() * height * width;
			}
		}
	}

	
	@Override
	public String toString() {
		String result = "";
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				result += elements[i][j] + "  ";
			}
			result += "\n";
		}
		return result;
	}
	
	
	/**
	 *returns element with coordinates yPos, xPos
	 */
	public float getElement(int yPos, int xPos) {
		return elements[yPos][xPos];
	}	
	
	
	/**
	 *set element value with position yPos, xPos to newValue
	 */
	public float setElement(int yPos, int xPos, float newValue) {
		elements[yPos][xPos] = newValue;
		return newValue;
	}	
	
	
	/**
	 *returns row with number rowNumber
	 */
	public float[] getRow(int rowNumber) {
		return elements[rowNumber].clone();
	}
	
	
	/**
	 *returns column with number columnNumber
	 */
	public float[] getColumn(int columnNumber) {
		float[] resultRow = new float[height];
		for(int i = 0; i < height; i++) {
			resultRow[i] = elements[i][columnNumber];
		}
		return resultRow;
	}	

	
	/**
	 *multiply matrix on number 
	 */
	public Matrix multiply(int multiplier) {
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				elements[i][j] *= multiplier;
			}
		}
		return this; 
	}
	
	
	/**
	 *multiply matrix on matrix
	 */
	public Matrix multiply(Matrix multiplier) {
		if ( this.width != multiplier.height )
			return null;
		float[][] tempElements = new float[height][multiplier.width];
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < multiplier.width; j++) {
				float sum = 0;
				for(int k = 0; k < width; k++) {
					sum += elements[i][k] * multiplier.elements[k][j];
				}
				tempElements[i][j] = sum;
			}
		}
		width = multiplier.width;
		elements = tempElements;
		return this; 
	}
	
	
	/**
	 *adding with matrix
	 */
	public Matrix add(Matrix item) {
		if ( this.width != item.width || this.height != item.height )
			return null;
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				elements[i][j] += item.elements[i][j];
			}
		}
		return this; 
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Matrix a = new Matrix(2, 2);
		Matrix b = new Matrix(2, 3);
		Matrix c = new Matrix(2, 2);
		Matrix d = new Matrix(1, 1);
		
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
				
		System.out.println( a.add(c) );
		System.out.println( a.multiply(d) );
		System.out.println(a.multiply(2));
		System.out.println( a.multiply(b) );
	}

}

