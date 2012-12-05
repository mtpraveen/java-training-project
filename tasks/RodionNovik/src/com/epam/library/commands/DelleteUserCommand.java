package com.epam.library.commands;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.epam.library.models.Reader;

public class DelleteUserCommand extends AbstractCommand {
	private String login;
	private String fio;
	private String address;
	private String dateOfBirth;
	private boolean deleted;

	public void setParams(String login, String fio, String dateOfBirth,
			String address) {
		this.login = login;
		this.fio = fio;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
	}

	@Override
	public void sendRequestToServer(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.DELLETEUSER.name());
		out.writeUTF(login);
		out.writeUTF(fio);
		out.writeUTF(address);
		out.writeUTF(dateOfBirth);
	}

	@Override
	public void processClientRequest(DataInputStream in) throws IOException {
		this.login = in.readUTF();
		this.fio = in.readUTF();
		this.address = in.readUTF();
		this.dateOfBirth = in.readUTF();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date dateOfBirth = new Date();
		try {
			dateOfBirth = df.parse(this.dateOfBirth);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (getServer().isAdmin((getClientThread().getName()))) {
			deleted = getServer().removeReader(login,
					new Reader(fio, dateOfBirth, address));
		}

	}

	@Override
	public void processResponseFromServer(DataInputStream in)
			throws IOException {
		System.out.println(in.readUTF());

	}

	@Override
	public void sendResponseToClient(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.DELLETEUSER.name());
		if(deleted){
			out.writeUTF("UserDeleted");
		}
		else out.writeUTF("ErrorYouMustHaveAdministratorsRights");
	}

}
