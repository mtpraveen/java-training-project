package com.epam.library.commands;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class SendMessageCommand extends AbstractCommand {
	private String message;

	@Override
	public void sendRequestToServer(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.MESSAGE.name());
		out.writeUTF(message);
	}

	@Override
	public void processClientRequest(DataInputStream in) throws IOException {
		message = in.readUTF();
		getServer().sendMessageToClients(this);
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public void processResponseFromServer(DataInputStream in)
			throws IOException {
		System.out.println(in.readUTF());

	}

	@Override
	public void sendResponseToClient(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.MESSAGE.name());
	}

}
