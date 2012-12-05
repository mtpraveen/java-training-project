package com.epam.library.commands;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.epam.library.server.ClientThread;
import com.epam.library.server.Server;

public interface ICommand {
		void sendRequestToServer(DataOutputStream in) throws IOException;

		void processClientRequest(DataInputStream out) throws IOException;

		void processResponseFromServer(DataInputStream out) throws IOException;

		void sendResponseToClient(DataOutputStream in) throws IOException;

		void setClientThread(ClientThread clientThread);

		void setServer(Server server);

		void setTimestamp(long timestamp);
	}


