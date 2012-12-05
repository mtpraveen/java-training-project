package com.epam.library.commands;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.epam.library.models.Reader;

public class AddUserCommand extends AbstractCommand {
	private String typeOfUser;
	private String login;
	private String password;

	private String fio;
	private String dateOfBirth;
	private String address;

	private boolean added;

	@Override
	public void sendRequestToServer(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.ADDUSER.name());
		out.writeUTF(typeOfUser);
		out.writeUTF(login);
		out.writeUTF(password);
		out.writeUTF(fio);
		out.writeUTF(dateOfBirth.toString());
		out.writeUTF(address);
	}

	public void setParams(String typeOfUser, String login, String password,
			String fio, String dateOfBirth, String address) {
		this.typeOfUser = typeOfUser;
		this.login = login;
		this.password = password;
		this.fio = fio;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
	}

	@Override
	public void processClientRequest(DataInputStream in) throws IOException {
		this.typeOfUser = in.readUTF();
		this.login = in.readUTF();
		this.password = in.readUTF();
		this.fio = in.readUTF();
		String tempDate = in.readUTF();
		this.address = in.readUTF();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date dateOfBirth = new Date();
		try {
			dateOfBirth = dateFormat.parse(tempDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (getServer().isAdmin(getClientThread().getName())) {
			added = getServer().addUser(typeOfUser,
					new Reader(fio, dateOfBirth, address), login, password);
		}
	}

	@Override
	public void processResponseFromServer(DataInputStream in)
			throws IOException {
		System.out.println(in.readUTF());

	}

	@Override
	public void sendResponseToClient(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.ADDUSER.name());
		if (added) {
			out.writeUTF("User added seccessfylly");
		} else
			out.writeUTF("Error, you must have administrators rights");
	}
}
