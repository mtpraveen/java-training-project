package com.epam.training.logic;

import java.io.IOException;

public class Runner {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		ServerWorker serverWorker = new ServerWorker();
		serverWorker.run();
	}

}
