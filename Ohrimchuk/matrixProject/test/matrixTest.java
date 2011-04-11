import junit.framework.Assert;
import org.junit.Test;

/**
 * @author Valery Ohrimchuk
 * April 2011
 */
public class matrixTest {

	public double[][] createRandomArray(int row, int col) {
		double[][] array = new double[row][col];

		for (int j = 0; j < row; j++)
			for (int i = 0; i < col; i++)
				array[j][i] = 100 * Math.random();
		return array;
	}

	/**
	 * Test method for {@link Matrix#Matrix(double[][])}.
	 */
	@Test
	public final void testMatrixDoubleArrayArray() {
		for (int test = 0; test < 10; test++) {
			double[][] array = createRandomArray((int) (100 * Math.random()),
					(int) (100 * Math.random()));

			Matrix m = new Matrix(array);
			for (int j = 0; j < m.getRow(); j++)
				for (int i = 0; i < m.getCol(); i++)
					Assert.assertEquals(array[j][i], m.getElement(j, i));
		}
	}

	/**
	 * Test method for {@link Matrix#Matrix(Matrix)}.
	 */
	@Test
	public final void testMatrixMatrix() {
		for (int test = 0; test < 10; test++) {
			double[][] array = createRandomArray((int) (100 * Math.random()),
					(int) (100 * Math.random()));

			Matrix m = new Matrix(array);
			Matrix m2 = new Matrix(m);
			for (int j = 0; j < m.getRow(); j++)
				for (int i = 0; i < m.getCol(); i++)
					Assert
							.assertEquals(m2.getElement(j, i), m.getElement(j,
									i));
		}
	}

	/**
	 * Test method for {@link Matrix#getRow()}.
	 */
	@Test
	public final void testGetSetRow() {
		Matrix m = new Matrix();
		for (int test = 0; test < 100; test++) {
			int row = (int) (100 * Math.random());
			m.setRow(row);
			Assert.assertEquals(row, m.getRow());
		}
	}

	/**
	 * Test method for {@link Matrix#getCol()}.
	 */
	@Test
	public final void testGetSetCol() {
		Matrix m = new Matrix();
		for (int test = 0; test < 100; test++) {
			int col = (int) (100 * Math.random());
			m.setCol(col);
			Assert.assertEquals(col, m.getCol());
		}
	}

	/**
	 * Test method for {@link Matrix#getContent()}.
	 */
	@Test
	public final void testGetContent() {
		for (int test = 0; test < 10; test++) {
			double[][] array = createRandomArray((int) (100 * Math.random()),
					(int) (100 * Math.random()));

			Matrix m = new Matrix(array);
			double[][] newArray = m.getContent();
			for (int j = 0; j < m.getRow(); j++)
				for (int i = 0; i < m.getCol(); i++)
					Assert.assertEquals(array[j][i], newArray[j][i]);
		}
	}

	/**
	 * Test method for {@link Matrix#multNumber(double)}.
	 */
	@Test
	public final void testMultNumber() {
		for (int test = 0; test < 10; test++) {
			double[][] array = createRandomArray((int) (100 * Math.random()),
					(int) (100 * Math.random()));

			Matrix m = new Matrix(array);
			double number = Math.random();
			m.multNumber(number);
			for (int j = 0; j < m.getRow(); j++)
				for (int i = 0; i < m.getCol(); i++)
					Assert.assertEquals(array[j][i] * number, m
							.getElement(j, i));
		}
	}

	/**
	 * Test method for {@link Matrix#getRowArray(int)}.
	 */
	@Test
	public final void testGetRowArray() {
		for (int test = 0; test < 10; test++) {
			double[][] array = createRandomArray((int) (100 * Math.random()),
					(int) (100 * Math.random()));

			Matrix m = new Matrix(array);
			int row = (int) (m.getRow() * Math.random());
			double[] rowArray = m.getRowArray(row);
			for (int i = 0; i < m.getCol(); i++)
				Assert.assertEquals(rowArray[i], m.getElement(row, i));
		}
	}

}
