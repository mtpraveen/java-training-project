package com.epam.training.logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ServerConnection {

	private Socket socket;
	private BufferedReader bufferedReader;
	private PrintStream printStream;
	
	public ServerConnection(Socket socket) {
		this.socket = socket;
		try {
			this.printStream = new PrintStream(socket.getOutputStream());
			this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Socket getSocket() {
		return socket;
	}
	
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
	public BufferedReader getBufferedReader() {
		return bufferedReader;
	}
	
	public void setBufferedReader(BufferedReader bufferedReader) {
		this.bufferedReader = bufferedReader;
	}
	
	public PrintStream getPrintStream() {
		return printStream;
	}
	
	public void setPrintStream(PrintStream printStream) {
		this.printStream = printStream;
	}

}
