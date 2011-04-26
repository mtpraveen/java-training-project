package brsu.Java.homework;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
/**
 * @author alex
 *
 */
public class Matrix {
	/**
	 * @param mas
	 * @param Line
	 * @param Column
	 */
	private int mas[][];
	private int Line;
	private int Column;
	/**
	 * @return the line
	 */
	public int getLine() {
		return Line;
	}
	public Matrix Myltiplay(int count){
		for (int i=0;i<getColumn();i++)
			for(int j=0;j<getLine();j++)
				mas[i][j]*=count;
		return this;
	}
	public Matrix Myltiplay(Matrix matr2){
		if (this.getLine()==matr2.getColumn())
		{
			int matrNew[][]=new int[this.getColumn()][matr2.getLine()];
			for (int masI=0;masI<this.getColumn();masI++)
				for(int masJ=0;masJ<matr2.getLine();masJ++)
					for(int i=0;i<this.getLine();i++)
						matrNew[masI][masJ]+=this.mas[masI][i]*matr2.mas[i][masJ];
			this.setMas(matrNew);
			return this;
		}
		else
		{
			System.out.println("Error Myltiplay!");
			return this;
		}
		}
	/**
	 * @param line the line to set
	 */
	public void setLine(int line) {
		Line = line;
	}
	/**
	 * @return the column
	 */
	public int getColumn() {
		return Column;
	}
	/**
	 * @param column the column to set
	 */
	public void setColumn(int column) {
		Column = column;
	}

	/**
	 * @param mas
	 */
	public Matrix(int[][] mas) {
		super();
		this.setMas(mas);
	}
	public Matrix(int[] mas) {
		super();
		this.setMas(mas);
	}
	public Matrix(int i,int j)  throws Exception{
		super();
		this.setMas(i,j);
	}

	public void setMas(int mas[][]){
		this.setColumn(mas.length);
		this.setLine(mas[0].length);
		this.mas = mas;
	}
	public void setMas(int mas[]){
		this.setColumn(1);
		this.setLine(mas.length);
		this.mas=new int[Column][Line];
		this.mas[0] = mas;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String str;
		str= "Matrix \n Column=" + Column + ", Line=" + Line + ", mas:";
		str+="\n";
		if (Line!=0)
		{
			for (int i=0; i<getColumn(); i++) {
				for (int j=0; j<Line; j++) {
			      str+=mas[i][j] +"\t";
			   }
			   str+="\n";
			}   
		}else
			str+="The massiv is empty";
		return str;
	}
	public int[][] getMas() {
		return mas;
	}
	public void setMas(int i,int j)  throws Exception {
		this.setColumn(i);
		this.setLine(j);
		int mass[][]=new int[i][j];
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		for (int mi=0;mi<i;mi++){
			System.out.println("Ввидете элемент строки "+mi);
			for(int mj=0;mj<j;mj++)
			{
				mass[mi][mj]= Integer.parseInt(r.readLine());
			}
		}
		this.mas = mass;
	}
	/**
	 * @param mas
	 */
	public int getIJ(int i,int j) {
		if (i<=getColumn() && j<=getLine())
			return mas[i-1][j-1];
		else
			return 0;//Здесь могло быть ваше исключение:)
	}
	public void setIJ(int i,int j,int count) {
		if (i<=Column && j<=Line)
			mas[i-1][j-1]=count;
		else
			System.out.print("ЕРРОР");
	}
	public int[] getLine(int number){
	return mas[number-1];
	}
	public int[] getColumn(int number){
		int mass[]=new int[getColumn()];
		for(int i=0;i<getColumn();i++)
			mass[i]=getIJ(i+1,number+1);
		return mass;
		}
	public static void main(String[] args) throws Exception {
	   	int l[][]={{1,2},{3,4},{5,6}};
	   	Matrix b=new Matrix(l);
	   	int l1[][]={{7,8,9},{10,11,12}};
	   	Matrix b1=new Matrix(l1);
	   	b.Myltiplay(b1);
	   	System.out.println(b.toString());
	   	b.setIJ(1,1,10);
	   	int l2[]=b.getColumn(1);
	   	Matrix b2=new Matrix(l2);
	   	b2.Myltiplay(10);
    	System.out.print(b2.toString());
	}

}
