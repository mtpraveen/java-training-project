package com.epam.training.logic;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.epam.threads.ServerThread;

public class Runner {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
//		ServerManager serverManager = new ServerManager();
//		Server server = Server.getInstance();
//		System.out.println("Initialized: " + server.getServerSocket().getInetAddress() + " " + server.getServerSocket().getLocalPort());
		
//		ServerSocket serverSocket = new ServerSocket(1234);
//		System.out.println("Initialized: " + serverSocket.getInetAddress() + " " + serverSocket.getLocalPort());
//		
//		while (true) {
//			System.out.println("now new client will be connect");
//			Socket clientSocket = serverSocket.accept();
//			System.out.println("client>");
//			
//			clientSocket.close();
//			Socket clientSocket = serverManager.acceptClientConnection(server.getServerSocket());
//			System.out.println("new client>");
			
//			Callable<Void> sender = new ServerThread(clientSocket);
//			ExecutorService executor = Executors.newCachedThreadPool(); // create executor and thread
//	        Future<Void> future = executor.submit((Callable<Void>) sender); // add task in the run queue
//
//	        // Wait while task is done.
//	        while(!future.isDone()) {
//	            try {
//	                Thread.sleep(100);
//	            } catch (InterruptedException ex) {
//	                // excetion
//	            }
//	        }
//
//	        executor.shutdown(); // close executor
//	        // Try to get result from future object.
//	        try {
////	            return future.get(); // get task resultS
//	        } catch (InterruptedException ex) {
//	            ex.printStackTrace();
////	            return null;
//	        } catch (ExecutionException ex) {
//	            // exception
////	            return false;
//	        }
		
		
		

//		}
		
	}

}
