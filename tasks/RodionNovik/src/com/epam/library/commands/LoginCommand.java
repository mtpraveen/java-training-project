package com.epam.library.commands;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Date;

import com.epam.library.client.Client;

public class LoginCommand extends AbstractCommand {
	private String login;
	private String password;

	@Override
	public void sendRequestToServer(DataOutputStream in) throws IOException {
		in.writeUTF(Commands.LOGIN.name());
		in.writeUTF(login);
		in.writeUTF(password);

	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void processClientRequest(DataInputStream out) throws IOException {
		login = out.readUTF();
		password = out.readUTF();
		if (getServer().isReader(login, password)
				|| getServer().isAdmin(login, password)) {
			getClientThread().setName(login);
			getClientThread().setStatus(true);
			getServer().setTimeOfEntry(login,new Date());
		}
	}

	@Override
	public void processResponseFromServer(DataInputStream in) throws IOException {
		String answer = Client.getRb().getString(in.readUTF());
		System.out.println(answer);
	}

	@Override
	public void sendResponseToClient(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.LOGIN.name());
		if(getClientThread().isSignedIn()){
			out.writeUTF("SignedInSeccessfully");
		}
		else{
			out.writeUTF("SignedInNotSeccessfullyTryAgain");
		}
		
	}

}
