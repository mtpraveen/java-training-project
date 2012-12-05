package com.epam.library.commands;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.epam.library.client.Client;

public class GetReportInFile extends AbstractCommand {
	private String path;

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public void sendRequestToServer(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.GETREPORTINFILE.name());
		out.writeUTF(path);
	}

	@Override
	public void processClientRequest(DataInputStream in) throws IOException {
		this.path = in.readUTF();
		getServer().saveReportInFile(path);

	}

	@Override
	public void processResponseFromServer(DataInputStream in)
			throws IOException {
		String answer = Client.getRb().getString(in.readUTF());
		System.out.println(answer + path);

	}

	@Override
	public void sendResponseToClient(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.GETREPORTINFILE.name());
		out.writeUTF("ReportSavedIn");

	}
}
