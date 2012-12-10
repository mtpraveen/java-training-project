package epam.course.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Locale;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import epam.course.commands.AbstractCommand;
import epam.course.commands.Commands;
import epam.course.commands.SendMessageCommand;
import epam.course.domain.Customer;

public class ClientThread extends Thread {

	private Customer customer;
	private Locale locale=new Locale("", "");
	private int localeNumber=0;
	
	
	public int getLocaleNumber() {
		return localeNumber;
	}

	public void setLocaleNumber(int localeNumber) {
		this.localeNumber = localeNumber;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	private String name;
	private Socket socket;
	private Server server;
	private DataInputStream clientInputStream;
	private DataOutputStream clientOutputStream;

	private BlockingQueue<AbstractCommand> commands = new LinkedBlockingQueue<AbstractCommand>();
	private Thread commandsThread;
	private String address;

	public ClientThread(String name, Socket socket, Server server) {
		this.name = name;
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
			e.printStackTrace();// deal with exception
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
					clientCommand.setClientThread(ClientThread.this);
					clientCommand.setServer(server);
					clientCommand.setTimestamp(System.currentTimeMillis());
					clientCommand.processClientRequest(clientInputStream);
					// addCommand(clientCommand);

				} catch (IOException e) {
					// socket is closed, nothing to listen
					break;
				}
			}
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

	public String getClientName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

}
