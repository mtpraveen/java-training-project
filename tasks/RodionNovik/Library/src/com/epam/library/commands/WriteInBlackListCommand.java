package com.epam.library.commands;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class WriteInBlackListCommand extends AbstractCommand {
	private String login;
	private boolean added;

	public void setLogin(String login) {
		this.login = login;
	}

	@Override
	public void processClientRequest(DataInputStream in) throws IOException {
		this.login = in.readUTF();
		if(getServer().isAdmin(getClientThread().getName())){
			added = getServer().addToBlackList(login);
		}

	}

	@Override
	public void processResponseFromServer(DataInputStream in)
			throws IOException {
		System.out.println(in.readUTF());

	}

	@Override
	public void sendResponseToClient(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.WRITEINBLACKLIST.name());
		if(added){
			out.writeUTF("Added successfully");
		}
		else out.writeUTF("User not exist or you don't have administrator rights");

	}


	@Override
	public void sendRequestToServer(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.WRITEINBLACKLIST.name());
		out.writeUTF(login);

	}

}
