package com.epam.library.commands;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.epam.library.client.Client;

public class EndTheSessionCommand extends AbstractCommand {

	@Override
	public void sendRequestToServer(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.ENDTHESESSION.name());
	}

	@Override
	public void processClientRequest(DataInputStream in) throws IOException {
		getServer().removeClient(getClientThread());
	}

	@Override
	public void processResponseFromServer(DataInputStream in)
			throws IOException {
		String answer = Client.getRb().getString(in.readUTF());
		System.out.println(answer);
		System.exit(0);
		
	}

	@Override
	public void sendResponseToClient(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.ENDTHESESSION.name());
		out.writeUTF("GoodBue");

	}
}
