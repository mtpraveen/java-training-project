package com.epam.library.commands;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.epam.library.server.ClientThread;
import com.epam.library.server.Server;

public class OrderTheBookCommand extends AbstractCommand {

	@Override
	public void sendRequestToServer(DataOutputStream in) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void processClientRequest(DataInputStream out) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void processResponseFromServer(DataInputStream out)
			throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendResponseToClient(DataOutputStream in) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setClientThread(ClientThread clientThread) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setServer(Server server) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setTimestamp(long timestamp) {
		// TODO Auto-generated method stub

	}

}
