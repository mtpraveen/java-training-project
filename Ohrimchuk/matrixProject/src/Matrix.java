/**
 * 
 * @author Valery Ohrimchuk
 * April 2011
 */
public class Matrix {
	private int row, col;
	private double[][] content;

	/**
	 * Constructor without parameters
	 * Set row = 0 and col = 0
	 */
	public Matrix() {
		this.row = 0;
		this.col = 0;
	}

	/**
	 * Constructor with parameters
	 * @param row
	 * @param col
	 * Set this.row = row and this.col = col
	 */
	public Matrix(int row, int col) {
		this.row = row;
		this.col = col;
		this.content = new double[this.getRow()][this.getCol()];
	}

	/**
	 * Constructor using array content[][]
	 * @param double[][]
	 */
	public Matrix(double[][] content) {
		this.setContent(content);
	}

	/**
	 * Copy constructor
	 * @param Matrix
	 */
	public Matrix(Matrix matrix) {
		this.setContent(matrix.getContent());
	}

	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @param row
	 *            the row to set
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * @return the col
	 */
	public int getCol() {
		return col;
	}

	/**
	 * @param col
	 *            the col to set
	 */
	public void setCol(int col) {
		this.col = col;
	}

	/**
	 * 
	 * @param j
	 * @param i
	 * @param element
	 */
	public void setElement(int j, int i,double element) {
		this.content[j][i] = element;
	}
	
	/**
	 * 
	 * @param j
	 * @param i
	 * @return
	 */
	public double getElement(int j, int i)
	{
		return this.content[j][i];
	}
	

	/**
	 * @return the content
	 */
	public double[][] getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(double[][] content) {
		this.row = content.length;
		this.col = content[0].length;
		this.content = new double[this.getRow()][this.getCol()];
		for (int j = 0; j < this.getRow(); j++) {
			for (int i = 0; i < this.getCol(); i++)
				this.content[j][i] = content[j][i];
		}
	}

	/**
	 * @return String this Matrix
	 */
	public String toString() {
		String result = "Matrix [" + this.getRow() + "][" + this.getCol()
				+ "]\n";
		for (int j = 0; j < this.getRow(); j++) {
			for (int i = 0; i < this.getCol(); i++)
				result += " " + this.content[j][i];
			result += "\n";
		}
		return (result);
	}

	/**
	 * <b>Multiplication</b> Matrix on the number
	 * 
	 * @param double number
	 */
	public void multNumber(double number) {
		/*
		 * for(double[] row : this.content) for(double elem : row) elem *=
		 * number;
		 */for (int j = 0; j < this.getRow(); j++)
			for (int i = 0; i < this.getCol(); i++)
				this.content[j][i] *= number;

	}

	/**
	 * <b>Multiplication</b> Matrix on the Matrix
	 * 
	 * @param matrix
	 */
	public Matrix multMatrix(Matrix matrix) throws ArithmeticException {

		if (this.getCol() == matrix.getRow()) {
			Matrix result = new Matrix(this.getRow(), matrix.getCol());
			for (int j = 0; j < this.getRow(); j++)
				for (int i = 0; i < matrix.getCol(); i++)
					for (int k = 0; k < this.getCol(); k++)
						result.content[j][i] += this.content[j][k]
								* matrix.content[k][i];
			return result;

		} else {
			throw new ArithmeticException(
					"It is ipmossible to mupliplicate these two matrix\n");
		}
	}

	/**
	 * Export array form Matrix where this.row = parameter row
	 * @param row
	 * @return row from matrix
	 */
	public double[] getRowArray(int row) {
		return (this.content[row]);
	}

	/**
	 * 
	 * @param col
	 * @return column from matrix
	 */
	public double[] getColArray(int col) {
		double[] resultCol = new double[this.getRow()];
		for (int j = 0; j < this.getRow(); j++)
			resultCol[j] = this.content[j][col];
		return (resultCol);
	}

}
