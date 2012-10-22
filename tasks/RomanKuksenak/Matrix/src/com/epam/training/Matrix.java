package com.epam.training;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Matrix {

	//Create matrix
	 void createMatrix(){
		int myMatrix[][];
		int size;
		String inputStr;
		
		BufferedReader Reader = new BufferedReader (new InputStreamReader (System.in));    
		System.out.println("Укажите размерность матрицы n x n:");
		
		try{
			inputStr = Reader.readLine();
			size = Integer.parseInt(inputStr);
			myMatrix = new int [size][size];
			
			for(int i = 0;i < size; i++){
				for(int j = 0;j < size; j++){
					myMatrix[i][j]=(int)(-size+Math.random()*2*size);
					
				}
			}
			
			
			
		output(myMatrix,size);	
		outMenu(myMatrix,size);
	
		
			
		}catch (Exception e){System.out.println("Вы ввели символ вместо числа!");} 
	}
	//Output menu
	 void outMenu(int myMatrix[][],int size) throws IOException{
		BufferedReader Reader = new BufferedReader (new InputStreamReader (System.in));
		String inputStr;
		int choice;
		
		System.out.println("Выберите операцию над матрицей:\n 0-создать новую матрицу\n 1-сортировка\n 2-сдвиг вправо\n 3-поиск возрастаний и убываний\n 5-транспонировать матрицу\n 6-выход");
		inputStr = Reader.readLine();
		choice = Integer.parseInt(inputStr);
			switch(choice){
			case 0:{createMatrix();break;}
			case 1:{sort(myMatrix,size);break;}
			case 2:{shiftRight(myMatrix,size);break;}
			case 3:{maxInc(myMatrix,size);break;}
			case 5:{transp(myMatrix,size);break;}
			case 6:{System.exit(0);break;}
			}
	}
	//Output matrix
	 void output(int myMatrix[][],int size){
		for (int a=0; a<size; a++) {
		    for (int b=0; b<size; b++) {
		    	if (myMatrix[a][b]<0){
		        System.out.print(myMatrix[a][b]+" ");} else {
		        	System.out.print(" "+myMatrix[a][b]+" ");	
		        }
		    }    
		    System.out.println("");
		}
	}
	//1.Sort	
	 void sort(int myMatrix[][],int size) throws IOException{
		
		for(int a = 0;a < size; a++){
			for(int j = 0;j < size; j++){
				for(int i = 0;i < size-1; i++){
					if (myMatrix[i][j]>myMatrix[i+1][j]){
						int bufer;
						bufer=myMatrix[i][j];
						myMatrix[i][j]=myMatrix[i+1][j];
						myMatrix[i+1][j]=bufer;
						
					}
				}
			}
		}
		System.out.println("");
		output(myMatrix,size);
		outMenu(myMatrix,size);
		
	}
	//2.Shift to Right
	 void shiftRight(int myMatrix[][],int size) throws IOException{
				
		System.out.println("Введите количество позиций для сдвига:");
			try{
				int positions;
				String inputStr;
				BufferedReader Reader = new BufferedReader (new InputStreamReader (System.in));
				inputStr = Reader.readLine();
				positions = Integer.parseInt(inputStr);
				
				for (int i = 0; i < positions; i++) {
					int temp = myMatrix[size][size - 1];
					for (int j = size - 1; j > 0; j--) {
						myMatrix[j][i] = myMatrix[j-1][i];
					}
					myMatrix[0][0] = temp;
				}
			  }catch (Exception e){System.out.println("Извините временно не работаем!");outMenu(myMatrix,size);}
			  
			  for (int a=0; a<myMatrix.length; a++) {
				    for (int b=0; b<myMatrix.length; b++) {
				        System.out.print(myMatrix[a][b]+" ");
				    }    
				    System.out.println("");
				}
	}
	//3.Increasing the maximum and minimum number of elements
	 void maxInc(int myMatrix[][],int size) throws IOException{
		int a_min=0,a_max=0,b_min=0,b_max=0;
		for(int i = 0;i < size; i++){
			int a1=0,b1=0,a2=0,b2=0;
			for(int j = 0;j < size-1; j++){
				if(myMatrix[i][j]<myMatrix[i][j+1]){a1=a1+1;}
				else {a1=0;}
				
				if(a1>a_max){a_max=a1;}
				
				if(myMatrix[i][j]>myMatrix[i][j+1]){a2=a2+1;}
				else {a2=0;}
				
				if(a2>a_min){a_min=a2;}
				
				if(myMatrix[j][i]<myMatrix[j+1][i]){b1=b1+1;}
				else{b1=0;}
				
				if(b1>b_max){b_max = b1;}
				
				if(myMatrix[j][i]>myMatrix[j+1][i]){b2=b2+1;}
				else {b2=0;}
				
				if(b2>b_min){b_min=b2;}
			}
			
		}
		output(myMatrix,size);
		System.out.println("Макс.кол-во возрастающих по гор-ли: "+(a_max+1));
		System.out.println("Макс.кол-во убывающих по гор-ли: "+(a_min+1));
		System.out.println("Макс.кол-во возрастающих по верт: "+(b_max+1));
		System.out.println("Макс.кол-во убывающих по верт: "+(b_min+1));
		outMenu(myMatrix,size);
	}
	//5.Transponirovanie
	 void transp(int myMatrix[][],int size) throws IOException
		{
			int buffer[][] = new int [size][size];
			for (int i=0; i<size; i++)
			{
				for (int j=0; j<myMatrix[i].length; j++)
				{
					buffer[j][i]=myMatrix[i][j];
				}
			}
			for (int i=0; i<size; i++)
			{
				for (int j=0; j<myMatrix[i].length; j++)
				{
					myMatrix[i][j]=buffer[i][j];
				}
			}
			output(myMatrix,size);
			outMenu(myMatrix,size);
		} 
	
}
