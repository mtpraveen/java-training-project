package com.epam.library.commands;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.epam.library.client.Client;

public class ChangeThePasswordCommand extends AbstractCommand {
	private String password;
	private String login;
	private boolean isChanged;

	@Override
	public void sendRequestToServer(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.CHANGETHEPASSWORD.name());
		out.writeUTF(password);
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void processClientRequest(DataInputStream in) throws IOException {
		this.login = getClientThread().getName();
		this.password = in.readUTF();		
		isChanged = getServer().changeUsersPassword(login, password);
		
	}

	@Override
	public void processResponseFromServer(DataInputStream in)
			throws IOException {
		String answer = Client.getRb().getString(in.readUTF());
		System.out.println(answer);
		
	}

	@Override
	public void sendResponseToClient(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.CHANGETHEPASSWORD.name());
		if(isChanged){
			out.writeUTF("PasswordChanged");
		}
		else{
			out.writeUTF("PasswordNotChanged");
		}
	}

	
}
