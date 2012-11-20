package com.epam.training.serverworkerstest;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.Test;

public class ConnectionTest {

	@Test
	public void testConnection() {
		try {
			Socket socket = new Socket(InetAddress.getLocalHost(), 1234);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
