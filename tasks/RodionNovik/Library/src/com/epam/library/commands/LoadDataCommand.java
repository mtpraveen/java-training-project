package com.epam.library.commands;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;

public class LoadDataCommand extends AbstractCommand {
	private String path;
	private boolean seccessful;

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public void sendRequestToServer(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.LOADTHEDATA.name());
		out.writeUTF(path);
	}

	@Override
	public void processClientRequest(DataInputStream in) throws IOException {
		this.path = in.readUTF();
		if(getClientThread().isSignedIn() && getServer().isAdmin(getClientThread().getName())){
			getServer().loadCatalogueFromFile(path);
			this.seccessful = true;
		}

	}

	@Override
	public void processResponseFromServer(DataInputStream in)
			throws IOException {
		System.out.println(in.readUTF());
	}

	@Override
	public void sendResponseToClient(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.LOADTHEDATA.name());
		if(new File(path).exists() && this.seccessful){
			out.writeUTF("DataLoaded");
		}
		else out.writeUTF("FileNotExistsOrYouAren'tAdmin");

	}
}
