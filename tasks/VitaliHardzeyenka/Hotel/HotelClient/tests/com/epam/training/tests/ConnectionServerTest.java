package com.epam.training.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Test;

public class ConnectionServerTest {

	@Test
	public void testServerCreate() {
		ServerSocket server = null;
		try {
			server = new ServerSocket(1234);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			Socket client = server.accept();
			System.out.println(client.getInetAddress());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
