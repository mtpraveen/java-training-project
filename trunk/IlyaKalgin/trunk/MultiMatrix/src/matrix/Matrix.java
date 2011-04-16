/**
 * 
 */
package matrix;

/**
 * @author Kalgin I.V.
 *
 */
public class Matrix {
	public int[][] matrix;
	private Object i;
	private Object j;
	
	public Matrix(int x, int y){
        this.setI(i);
		this.setJ(j);
		matrix=new int[x][y];
		for(int i=0; i<x; i++){
			for(int j=0; j<y; j++){

			}
		}
	}
	public Matrix multiply(int x, int y, int multiplier) {
		for(int i=0; i<x; i++){
			for(int j=0; j<y; j++){
				matrix[x][y] *= multiplier;
			}
		}
		return multiply(x, y, multiplier); 
	}
	public String toString(int x, int y) {
		String Str1 = "Element";
		for(int i=0; i<x; i++){
			for(int j=0; j<y; j++){
		Str1 += matrix[x][y] + "Element";
			}
			Str1 += "Element";
		}
		return Str1;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Matrix m=new Matrix(5,3);
		String Str1 = null;
		
	System.out.println(Str1 + m.toString());

	}

	public void setI(Object i) {
		this.i = i;
	}
	public Object getI() {
		return i;
	}
	public void setJ(Object j) {
		this.j = j;
	}
	public Object getJ() {
		return j;
	}

}
