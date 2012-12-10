package epam.course.commands;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import epam.course.server.ClientThread;
import epam.course.server.Server;

public interface ICommand {
	
	void sendRequestToServer(DataOutputStream out) throws IOException;
	
	void processResponseFromServer(DataInputStream in) throws IOException;

	void processClientRequest(DataInputStream in) throws IOException;

	void sendResponseToClient(DataOutputStream out) throws IOException;

	void setClientThread(ClientThread clientThread);

	void setServer(Server server);
	
	void setTimestamp(long timestamp);
}
