package com.epam.library.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.epam.library.commands.AbstractCommand;
import com.epam.library.commands.Commands;

public class ClientThread extends Thread {
	private String name;
	private boolean status;
	private Server server;
	private Socket socket;
	private DataInputStream clientInputStream;
	private DataOutputStream clientOutputStream;

	private BlockingQueue<AbstractCommand> commands = new LinkedBlockingQueue<AbstractCommand>();
	private Thread commandsThread;
	private String address;

	public ClientThread(String name, Socket socket, Server server) {
		this.name = name;
		this.status = false;
		this.socket = socket;
		this.server = server;
		address = socket.getInetAddress().toString();
		try {
			this.clientInputStream = new DataInputStream(
					socket.getInputStream());
			this.clientOutputStream = new DataOutputStream(
					socket.getOutputStream());
			startCommandListener();
		} catch (IOException e) {
			e.printStackTrace();// deal with exception
		}
	}
	
	public void setStatus(boolean status){
		this.status = status;
	}

	private void startCommandListener() {
		commandsThread = new Thread(new ClientCommandsListener());
		commandsThread.setDaemon(true);
		commandsThread.start();
	}
	

	public boolean isSignedIn() {
		return status;
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
					clientCommand.setClientThread(ClientThread.this);
					clientCommand.setServer(server);
					clientCommand.setTimestamp(System.currentTimeMillis());
					clientCommand.processClientRequest(clientInputStream);
					addCommand(clientCommand);
				} catch (IOException e) {
					// socket is closed, nothing to listen
					break;
				}
			}
		}

	}

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
			System.out.println(name);
			e.printStackTrace();
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
		ClientThread other = (ClientThread) obj;
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

	public String getAddress() {
		return address;
	}
}
