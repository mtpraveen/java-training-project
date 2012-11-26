package com.epam.library.commands;


import com.epam.library.server.ClientThread;
import com.epam.library.server.Server;

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
