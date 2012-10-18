package com.epam.lab1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import javax.sound.midi.Track;

public class Matrix {
	
	private int n = 0;
	private double[][] array;
	
	private BufferedReader bReader;
	
	public Matrix() {
		n = 1;
		array = new double[n][n];
		CompleteMatrix();
		
		bReader = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public void SetMatrix() {
		System.out.println("Введите размерность матрицы: ");
		try {
			n = Integer.parseInt(bReader.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
		array = new double[n][n];
		CompleteMatrix();
	}
	
	public void Rearrangement (int shift, boolean isRow) {
		double[][] rearrangedArr = new double[4][4];
		if (!isRow) {
			TransposeMatrix();
		}
		for (int i = 0; i < n; i++) {
			rearrangedArr[i] = array[i];
		}
		int j = 0;
		for (int i = 0; i < n; i++) {
			j = i + shift;
			if (j >= n) {
				j -= n;
			} else if (j < 0) {
				j += n;
			}
			array[j] = rearrangedArr[i];
		}
		if (!isRow) {
			TransposeMatrix();
		}
	}
	
	public void Sort (int k, boolean isRaw) {
		double[] arr = Arrays.copyOf(array[k-1], n);
		double temp;
		if (!isRaw) {
			TransposeMatrix();
		}
	    for(int i = n-1 ; i >= 0 ; i--){
	        for(int j = 0 ; j < i ; j++){
	            if( arr[j] > arr[j+1] ) {
	            	arr = DoubleSwap(arr,j,j+1);
	            	array = DoubleArrSwap(array,j,j+1);
	            }	             
	        }
	    }
	    if (!isRaw) {
	    	TransposeMatrix();
		}
	}
	
	public void TransposeMatrix() {
		double[][] transpArr = new double[n][n];
		for (int i = 0; i<n; i++) {
			for (int j=0; j<n; j++) {
				transpArr[i][j] = array[j][i];
			}
		}
		array = transpArr;
	}

	public void PrintMatrix() {
		System.out.println("Матрица: ");
		for (double[] i : array) {
			System.out.println(Arrays.toString(i));
		}
	}
	
	private double[]  DoubleSwap(double[] arr, int i, int j) {
		double temp;
		temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
		return arr;
	}
	
	private double[][]  DoubleArrSwap(double[][] arr, int i, int j) {
		double[] temp;
		temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
		return arr;
	}
	
	private void CompleteMatrix() {
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++) {
				array[i][j] = (Math.random() - 0.5) * 2 * n;
			}
	}	
}
