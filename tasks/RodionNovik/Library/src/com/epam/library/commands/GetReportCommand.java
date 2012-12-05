package com.epam.library.commands;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class GetReportCommand extends AbstractCommand {
	private String report;

	@Override
	public void sendRequestToServer(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.GETREPORT.name());

	}

	@Override
	public void processClientRequest(DataInputStream in) throws IOException {
		report = getServer().getReport();

	}

	@Override
	public void processResponseFromServer(DataInputStream in)
			throws IOException {
		System.out.println(in.readUTF());

	}

	@Override
	public void sendResponseToClient(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.GETREPORT.name());
		out.writeUTF(report);

	}
}
