package com.epam.library.commands;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class GetStatisticCommand extends AbstractCommand {
	private String prefix="Name\tDate\t\t\t\tDuration";
	private String statistic;

	@Override
	public void sendRequestToServer(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.GETSTATISTIC.name());

	}

	@Override
	public void processClientRequest(DataInputStream in) throws IOException {
		this.statistic = getServer().getStatisticAboutCurrentSession();

	}

	@Override
	public void processResponseFromServer(DataInputStream in)
			throws IOException {
		System.out.println(prefix);
		System.out.println(in.readUTF());

	}

	@Override
	public void sendResponseToClient(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.GETSTATISTIC.name());
		out.writeUTF(statistic);
	}
}
