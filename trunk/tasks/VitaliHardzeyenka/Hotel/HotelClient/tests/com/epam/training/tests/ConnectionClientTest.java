package com.epam.training.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.Socket;

import org.junit.Test;

import com.epam.training.logic.Connecter;
import com.epam.training.logic.Runner;

public class ConnectionClientTest {

	@Test
	public void testConnection() {
		Connecter connecter = new Connecter();
		connecter.run();
	}

}
