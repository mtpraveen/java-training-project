package epam.course.commands;

import java.util.ResourceBundle;

import epam.course.server.ClientThread;
import epam.course.server.Server;

public abstract class AbstractCommand implements ICommand {

	private ClientThread clientThread;
	private Server server;
	private long timestamp;
	
	
	
	public ClientThread getClientThread() {
		return clientThread;
	}

	public void setClientThread(ClientThread clientThread) {
		this.clientThread = clientThread;
	}

	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

}
