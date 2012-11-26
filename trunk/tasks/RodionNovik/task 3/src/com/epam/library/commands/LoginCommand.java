package com.epam.library.commands;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

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
		}
	}

	@Override
	public void processResponseFromServer(DataInputStream in) throws IOException {
		System.out.println(in.readUTF());
	}

	@Override
	public void sendResponseToClient(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.LOGIN.name());
		if(getClientThread().isSignedIn()){
			out.writeUTF("Signed in seccessfully");
		}
		else{
			out.writeUTF("Signed in not seccessfully, try again");
		}
		
	}

}
