package server;

import hospital.AccessRights;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import commands.AbstractCommand;
import commands.Commands;
import commands.SendMessageCommand;
import server.ClientThreadAtServer;
import server.Server;

public class ClientThreadAtServer extends Thread {
	private String name;
	private AccessRights accessRights;
	private Socket socket;
	private Server server;
	private boolean isProcessed;
	private DataInputStream clientInputStream;
	private DataOutputStream clientOutputStream;

	private BlockingQueue<AbstractCommand> commands = new LinkedBlockingQueue<AbstractCommand>();
	private Thread commandsThread;
	private String address;

	public ClientThreadAtServer(String name, AccessRights accessRights,
			Socket socket, Server server) throws Exception {
		this.name = name;
		this.accessRights = accessRights;
		this.socket = socket;
		this.address = socket.getInetAddress().toString();
		this.server = server;
		try {
			this.clientInputStream = new DataInputStream(
					socket.getInputStream());
			this.clientOutputStream = new DataOutputStream(
					socket.getOutputStream());
			startCommandListener();
		} catch (IOException e) {
			Server.getLogger().error("Can't create new client thread.", e);
			throw new Exception("Can't create new client thread.");
		}
	}

	private void startCommandListener() {
		commandsThread = new Thread(new ClientCommandsListener());
		commandsThread.setDaemon(true);
		commandsThread.start();
	}

	public synchronized void close() {
		if (!socket.isClosed()) {
			commandsThread.interrupt();
			closeResources();
		}
		this.interrupt();
	}

	public synchronized void addCommand(AbstractCommand cmd) {
		commands.add(cmd);
	}

	private class ClientCommandsListener implements Runnable {

		@Override
		public void run() {
			String commandName;
			while (true) {
				try {
					commandName = clientInputStream.readUTF();
					Commands cmd = Commands.valueOf(commandName);
					AbstractCommand clientCommand = (AbstractCommand) cmd
							.getCommandInstance();
					if (!server.isDataLoading()) {
						setProcessed(true);
						switch (accessRights.name()) {
						case "Client":
							if (!server.getClientCommands().contains(
									commandName)) {
								notAllowed(clientCommand, "You have no rights!");
							} else {
								allowed(clientCommand);
							}
							setProcessed(false);
							break;
						case "Unknown":
							if (!commandName.equals(Commands.LOGIN.name())) {
								notAllowed(clientCommand, "You have no rights!");
							} else {
								allowed(clientCommand);
							}
							setProcessed(false);
							break;
						default:
							allowed(clientCommand);
							setProcessed(false);
						}
					} else {
						notAllowed(clientCommand,
								"Server is unavailable. Work is underway.");
					}
				} catch (IOException e) {
					Server.getLogger().error(e);
					// socket is closed, nothing to listen
					break;
				}
			}
		}

		/**
		 * @param clientCommand
		 * @throws IOException
		 */
		private void allowed(AbstractCommand clientCommand) throws IOException {
			clientCommand.setClientThread(ClientThreadAtServer.this);
			clientCommand.setServer(server);
			clientCommand.setTimestamp(System.currentTimeMillis());
			clientCommand.processClientRequest(clientInputStream);
			clientCommand.getClientThread().addCommand(clientCommand);
		}

		/**
		 * @param clientCommand
		 * @throws IOException
		 */
		private void notAllowed(AbstractCommand clientCommand, String message)
				throws IOException {
			String commandName;
			Commands cmd;
			clientCommand.clearClientRequest(clientInputStream);
			commandName = Commands.MESSAGE.name();
			cmd = Commands.valueOf(commandName);
			clientCommand = (AbstractCommand) cmd.getCommandInstance();
			clientCommand.setClientThread(ClientThreadAtServer.this);
			clientCommand.setServer(server);
			clientCommand.setTimestamp(System.currentTimeMillis());
			((SendMessageCommand) clientCommand).setMessage(message);
			clientCommand.getClientThread().addCommand(clientCommand);
		}

	}

	@Override
	public void run() {
		try {
			while (true) {
				AbstractCommand cmd = commands.poll();
				if (socket.isClosed() || interrupted()) {
					break;
				}
				if (cmd != null) {
					cmd.sendResponseToClient(clientOutputStream);
				}
			}
		} catch (Exception e) {
			Server.getLogger().error(name, e);
			System.out
					.println("Client thred for user: " + name + " is closed.");
		} finally {
			closeResources();
		}
	}

	private synchronized void closeResources() {
		try {
			if (clientInputStream != null) {
				clientInputStream.close();
			}
			if (clientOutputStream != null) {
				clientOutputStream.close();
			}
			if (!socket.isClosed()) {
				socket.close();
			}
		} catch (IOException ignored) {
			Server.getLogger().error("Thread not responding", ignored);
			System.out.println("Thread for user " + name
					+ " not responding. Try restart server.");
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientThreadAtServer other = (ClientThreadAtServer) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public String getClientName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setClientName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	/**
	 * @return the accessRights
	 */
	public AccessRights getAccessRights() {
		return accessRights;
	}

	/**
	 * @param accessRights
	 *            the accessRights to set
	 */
	public void setAccessRights(AccessRights accessRights) {
		this.accessRights = accessRights;
	}

	/**
	 * @return the isProcessed
	 */
	public synchronized boolean isProcessed() {
		return isProcessed;
	}

	/**
	 * @param isProcessed
	 *            the isProcessed to set
	 */
	public synchronized void setProcessed(boolean isProcessed) {
		this.isProcessed = isProcessed;
	}

}
