package com.epam.lab1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Labs {
	private BufferedReader bReader;
	private Matrix matrix;
	
	public Labs() {
		bReader = new BufferedReader(new InputStreamReader(System.in));
		matrix = new Matrix();
		matrix.SetMatrix();
		matrix.PrintMatrix();
	}
	
	public void Task1SortMatrix() {
		int k = 0;
		String order = null; 
		System.out.println("Сортировка матрицы по элементам k-ой строки.");
		try {
			System.out.println("Введите k:");
			k = Integer.parseInt(bReader.readLine());
			System.out.println("Введите raw, если вы хотите отсортировать по строкам. Если другой символ - матрица будет отсортирована по столбцам: ");
			order = bReader.readLine();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (order.equals("raw")) {
			matrix.Sort(k, true);
		} else {
			matrix.Sort(k, false);
		}
		matrix.PrintMatrix();
	}
	
	public void Task2ShiftMatrix() {
		int k = 0;
		String order = null;
		System.out.println("Циклический сдвиг заданной матрицы на k позиций вправо (влево, вверх, вниз)..");
		try {
			System.out.println("Введите k (со знаком +, если нужно сдвинуть вперёд и -, если назад):");
			k = Integer.parseInt(bReader.readLine());
			System.out.println("Введите raw, если вы хотите сдвигать по строкам. Если другой символ - матрица будет отсортирована по столбцам: ");
			order = bReader.readLine();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (order.equals("raw")) {
			matrix.Rearrangement(k, true);
		} else {
			matrix.Rearrangement(k, false);
		}
		matrix.PrintMatrix();
	}
	
	public void Task5TranspMatrix() {
		matrix.TransposeMatrix();
		matrix.PrintMatrix();
	}
}
