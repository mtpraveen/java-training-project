package com.epam.demo01b;

import java.io.IOException;
import java.util.Random;

public class epam01b
{

	/**
	 * @param args
	 */
	public static int n;
	public static double m[][];
	public static void PrintMatrix(String title, double matrix[][])
	{
		/*String s = Arrays.deepToString(matrix);
		s = s.replace("], [", "],\r\n [");
		if (!title.equals(""))
			System.out.println(title + " : ");
		System.out.println(s);*/
		if (!title.equals(""))
			System.out.println(title + " : ");
		for (int i = 0;i<n;i++)
		{
			System.out.print("[");
			for (int j = 0;j<n;j++)
			{
				if (j>0)
					System.out.print(",");
				String s = String.valueOf(matrix[i][j]);
				while (s.length() < 6)//StringBuffer needed =)
					s = " " + s;
				System.out.print(s);
				
			}
			System.out.println("]");
		}	
	}
	public static void ExchangeRows(int row1, int row2, double matrix[][])
	{
		for (int i = 0;i<n;i++)
		{
			double tmp = matrix[row1][i];
			matrix[row1][i] = matrix[row2][i];
			matrix[row2][i] = tmp;
		}
	}
	public static void ExchangeCols(int col1, int col2, double matrix[][])
	{
		for (int i = 0;i<n;i++)
		{
			double tmp = matrix[i][col1];
			matrix[i][col1] = matrix[i][col2];
			matrix[i][col2] = tmp;
		}
	}
	public static Double frandom()
	{
		return -n + Math.random()*2*n;
	}
	public static void Fill()
	{
		m = new double[n][n];
		for (int i = 0;i<n;i++)
			for(int j = 0;j<n;j++)
				m[i][j] = Math.round(10*frandom())/10.0;
		PrintMatrix("Source matrix", m);
	}
	public static double[][] DeepClone(double[][] Matrix)
	{
		//.clone() is't deep =(
		double[][]res = new double [n][n];
		for(int i = 0;i<n;i++)
			for(int j = 0;j<n;j++)
				res[i][j] = Matrix[i][j];
		return res;
	}
	public static void task1_rows(int row)
	{
		double matrix[][] = DeepClone(m);
		for (int i = 0;i<n-1;i++)
			for(int ii = 0;ii<n-i-1;ii++)
			{
				if (matrix[row][ii] > matrix[row][ii+1])
					ExchangeCols(ii, ii+1, matrix);
			}
		PrintMatrix("Sorted by " + row + " row", matrix);
	}
	public static void task1_cols(int col)
	{
		double matrix[][] = DeepClone(m);
		for (int i = 0;i<n-1;i++)
			for(int ii = 0;ii<n-i-1;ii++)
			{
				if (matrix[ii][col] > matrix[ii+1][col])
					ExchangeRows(ii, ii+1, matrix);
			}
		PrintMatrix("Sorted by " + col + " column", matrix);
	}
	public static void task2(int count)
	{
		double matrix[][] = new double [n][n];
		for (int i = 0;i<n;i++)
		{
			int idx1 = i;
			int idx2 = (i + count) % n;
			for (int j = 0;j < n;j++)
				matrix[idx2][j] = m[idx1][j];
		}
		PrintMatrix("Shift by " + count + " row(s) down", matrix);
	}
	public static int EvalGrowingSequnceLen(int row,int offset)
	{
		int res = 1;
		while ((offset + res < n) && (m[row][offset + res - 1] < m[row][offset + res]))
			res++;
		return res;
	}
	public static void task3()
	{
		System.out.println("Task 3");
		int MaxLen = 0;
		int MaxLenRow = -1; 
		for (int i = 0;i<n;i++)
		{
			int offset = 0;
			while (offset < n)
			{
				int Len = EvalGrowingSequnceLen(i, offset);
				if (Len > MaxLen)
				{
					MaxLen = Len;
					MaxLenRow = i;
				}
				MaxLen = Math.max(MaxLen, Len);
				offset += Len;
			}
		}
		if (MaxLen < 2)
			System.out.println("Growing sequnce not found");
		else
			System.out.println("Max growing sequnce lenght = " + MaxLen + " (in row " + MaxLenRow + ")");
	}
	public static int FindFirstPositiveNumberIndex(int offset,int row)
	{
		for (int i = offset;i<n;i++)
			if (m[row][i] > 0)
				return i;
		return -1;
	}
	
	public static void task4()
	{
		System.out.println("Task 4");
		for (int i=0;i<n;i++)
		{
			System.out.print(i + " : ");
			int idx2 = -1;
			int idx1 = FindFirstPositiveNumberIndex(0, i);
			if (idx1 != -1)
				idx2 = FindFirstPositiveNumberIndex(idx1+1, i);
			if (idx2 != -1)
			{
				double sum = 0;
				for (int j = idx1 + 1;j<idx2;j++)
					sum += m[i][j];
				System.out.println(sum);
			}
			else
				System.out.println("-");
		}
	}
	private static void task5()
	{
		double [][]matrix = DeepClone(m);
		for (int i = 0;i<n;i++)
			for (int j = 0;j<i;j++)
			{
				double tmp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = tmp;
			}
		PrintMatrix("Transposed matrix", matrix);
	}
	public static void main(String[] args)
	{
		boolean debug = true;
		debug = false;
		//ќпредел€ем параметры
		if (!debug || (Math.random() < 0.1)) //зачем нам дл€ тестировани€ каждый раз вводить число =)
			try
			{
				System.out.print("N = ");
				byte []b = new byte [100];
				int len = System.in.read(b);
				String s = new String(b,0,len);
				s = s.replace("\r", "");
				s = s.replace("\n", "");
				s = s.replace(" ", "");
				n = Integer.parseInt(s);
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		else
		{
			n = 5;
			System.out.println("N = " + n);
		}
		//заполн€ем сучайными данными
		if (n < 2)
			System.out.print("–азмерность < 1");
		else
		{
			Fill();
			System.out.println();
			task1_rows((new Random()).nextInt(n));
			System.out.println();
			task1_cols((new Random()).nextInt(n));
			System.out.println();
			task2((new Random()).nextInt(n-1));
			System.out.println();
			task3();
			System.out.println();
			task4();
			System.out.println();
			task5();
			System.out.println();
		}
	}

}
