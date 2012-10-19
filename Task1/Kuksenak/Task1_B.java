package com.epam.training;
import java.io.*;

public class Task1_B {

	//Sort	
	static void sort(int MyMatrix[][]){
		
		for(int a = 0;a < MyMatrix.length; a++){
			for(int j = 0;j < MyMatrix.length; j++){
				for(int i = 0;i < MyMatrix.length-1; i++){
					if (MyMatrix[i][j]>MyMatrix[i+1][j]){
						int bufer;
						bufer=MyMatrix[i][j];
						MyMatrix[i][j]=MyMatrix[i+1][j];
						MyMatrix[i+1][j]=bufer;
						
					}
				}
			}
		}
		System.out.println("");
		for (int a=0; a<MyMatrix.length; a++) {
		    for (int b=0; b<MyMatrix.length; b++) {
		        System.out.print(MyMatrix[a][b]+" ");
		    }    
		    System.out.println("");
		}
	}
	//Shift to Right
	static void shiftRight(int MyMatrix[][]){
				
		System.out.println("Введите количество позиций для сдвига:");
			try{
				int size = MyMatrix.length;
				int positions;
				String inputStr;
				BufferedReader Reader = new BufferedReader (new InputStreamReader (System.in));
				inputStr = Reader.readLine();
				positions = Integer.parseInt(inputStr);
				
				for (int i = 0; i < positions; i++) {
					int temp = MyMatrix[size][size - 1];
					for (int j = size - 1; j > 0; j--) {
						MyMatrix[j][i] = MyMatrix[j-1][i];
					}
					MyMatrix[0][0] = temp;
				}
			  }catch (Exception e){System.out.println("Извините временно не работаем!");}
			  
			  for (int a=0; a<MyMatrix.length; a++) {
				    for (int b=0; b<MyMatrix.length; b++) {
				        System.out.print(MyMatrix[a][b]+" ");
				    }    
				    System.out.println("");
				}
	}
	//Increasing the maximum number of elements
	static void maxInc(int MyMatrix[][]){
		int a_min=0,a_max=0,b_min=0,b_max=0;
		for(int i = 0;i < MyMatrix.length; i++){
			int a1=0,b1=0,a2=0,b2=0;
			for(int j = 0;j < MyMatrix.length-1; j++){
				if(MyMatrix[i][j]<MyMatrix[i][j+1]){a1=a1+1;}
				else {a1=0;}
				
				if(a1>a_max){a_max=a1;}
				
				if(MyMatrix[i][j]>MyMatrix[i][j+1]){a2=a2+1;}
				else {a2=0;}
				
				if(a2>a_min){a_min=a2;}
				
				if(MyMatrix[j][i]<MyMatrix[j+1][i]){b1=b1+1;}
				else{b1=0;}
				
				if(b1>b_max){b_max = b1;}
				
				if(MyMatrix[j][i]>MyMatrix[j+1][i]){b2=b2+1;}
				else {b2=0;}
				
				if(b2>b_min){b_min=b2;}
			}
			
		}
		System.out.println("Макс.кол-во возрастающих по гор-ли: "+(a_max+1));
		System.out.println("Макс.кол-во убывающих по гор-ли: "+(a_min+1));
		System.out.println("Макс.кол-во возрастающих по верт: "+(b_max+1));
		System.out.println("Макс.кол-во убывающих по верт: "+(b_min+1));
	}
	
	//main
	public static void main(String args []){

		int myMatrix[][];
		int size,choice;
		String inputStr;
		
		BufferedReader Reader = new BufferedReader (new InputStreamReader (System.in));    
		System.out.println("Укажите размерность матрицы n x n:");
		
		try{
			inputStr = Reader.readLine();
			size = Integer.parseInt(inputStr);
			myMatrix = new int [size][size];
			
			for(int i = 0;i < myMatrix.length; i++){
				for(int j = 0;j < myMatrix.length; j++){
					myMatrix[i][j]=(int)(-myMatrix.length+Math.random()*2*myMatrix.length);
					
				}
			}
			
			
			for (int a=0; a<myMatrix.length; a++) {
			    for (int b=0; b<myMatrix.length; b++) {
			        System.out.print(myMatrix[a][b]+" ");
			    }    
			    System.out.println("");
			}
			
		
		System.out.println("Выберите операцию над матрицей: \n 1-сортировка\n 2-сдвиг вправо\n 3-поиск возрастаний и убываний");
		inputStr = Reader.readLine();
		choice = Integer.parseInt(inputStr);
			switch(choice){
			case 1:{sort(myMatrix);break;}
			case 2:{shiftRight(myMatrix);break;}
			case 3:{maxInc(myMatrix);break;}
			}
		
		}catch (Exception e){System.out.println("Вы ввели символ вместо числа!");}
		
		
	}

}
