package com.epam.training.serverworkerstest;

import org.junit.Test;

import com.epam.training.logic.ServerWorker;

public class ServerWorkerTest {

	@Test
	public void testServerWorker() {
		
		ServerWorker serverWorker = new ServerWorker();
		serverWorker.run();
	}

}
