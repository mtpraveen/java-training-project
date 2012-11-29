package commands;

import server.ClientThreadAtServer;
import server.Server;

public abstract class AbstractCommand implements ICommand {
	private ClientThreadAtServer clientThreadAtServer;
	private Server server;
	private long timestamp;
	private String declaration; 

	
	
	/**
	 * @return the declaration
	 */
	public String getDeclaration() {
		return declaration;
	}

	/**
	 * @param declaration the declaration to set
	 */
	public void setDeclaration(String declaration) {
		this.declaration = declaration;
	}

	public ClientThreadAtServer getClientThread() {
		return clientThreadAtServer;
	}

	public void setClientThread(ClientThreadAtServer clientThreadAtServer) {
		this.clientThreadAtServer = clientThreadAtServer;
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
