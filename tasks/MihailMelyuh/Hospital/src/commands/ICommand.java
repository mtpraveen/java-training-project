package commands;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import server.ClientThreadAtServer;
import server.Server;

public interface ICommand {
	
	void sendRequestToServer(DataOutputStream out) throws IOException;

	void processResponseFromServer(DataInputStream in) throws IOException;

	void processClientRequest(DataInputStream in) throws IOException;

	void sendResponseToClient(DataOutputStream out) throws IOException;
	
	void setParametrs(DataOutputStream serverOutputStream,BufferedReader consoleInputStream) throws IOException;

	void clearClientRequest(DataInputStream in) throws IOException;
	
	void setClientThread(ClientThreadAtServer clientThreadAtServer);

	void setServer(Server server);
	
	void setTimestamp(long timestamp);
	
}
