package epam.course.client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import epam.course.commands.Commands;
import epam.course.commands.ICommand;
import epam.course.commands.LoginCommand;
import epam.course.commands.OrderCommand;
import epam.course.commands.RegistrationCommand;
import epam.course.commands.SendMessageCommand;
import epam.course.commands.ShowOrdersCommand;

public class Client {
	private DataInputStream serverInputStream;
	private DataOutputStream serverOutputStream;
	private BufferedReader consoleInputStream = new BufferedReader(
			new InputStreamReader(System.in));

	private Socket socket;
	private String ip = "127.0.0.1";
	private int port = 1234;

	public static boolean logged = false;
	public static final String Greeting = "greeting";

	public Client(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	public void run() throws IOException {
		try {
			InetAddress address = InetAddress.getByName(ip);
			socket = new Socket(address, port);
			serverInputStream = new DataInputStream(socket.getInputStream());
			serverOutputStream = new DataOutputStream(socket.getOutputStream());
			Thread consoleThread = new Thread(new Runnable() {

				@Override
				public void run() {

					while (true) {
						String clientCommand;
						try {
							String wholeCommand = consoleInputStream.readLine();
							String[] commands = wholeCommand.split(" ");
							clientCommand = commands[0];
							// clientCommand = consoleInputStream.readLine();
							ICommand command;
							if (clientCommand
									.equalsIgnoreCase("changelanguage")) {
								command = Commands.CHANGELANGUAGE
										.getCommandInstance();
								command.sendRequestToServer(serverOutputStream);
							} else if (clientCommand.equalsIgnoreCase("login")) {
								command = (LoginCommand) Commands.LOGIN
										.getCommandInstance();
								((LoginCommand) command).setLogin(commands[1]);
								((LoginCommand) command)
										.setPassword(commands[2]);
								command.sendRequestToServer(serverOutputStream);
							} else if (clientCommand
									.equalsIgnoreCase("registration")) {
								if (commands.length == 6) {
									command = (RegistrationCommand) Commands.REGISTRATION
											.getCommandInstance();
									((RegistrationCommand) command)
											.setName(commands[1]);
									((RegistrationCommand) command)
											.setSurname(commands[2]);
									((RegistrationCommand) command)
											.setNumberPassp(commands[3]);
									((RegistrationCommand) command)
											.setLogin(commands[4]);
									((RegistrationCommand) command)
											.setPassword(commands[5]);
									command.sendRequestToServer(serverOutputStream);
								}
							} else if (!logged) {
								command = Commands.MESSAGE.getCommandInstance();
								((SendMessageCommand) command)
										.setMessage(Greeting);
								command.sendRequestToServer(serverOutputStream);
							} else if (clientCommand.equalsIgnoreCase("order")) {
								if (commands.length == 4) {
									command = (OrderCommand) Commands.ORDER
											.getCommandInstance();
									((OrderCommand) command)
											.setCarNumber(commands[1]);
									((OrderCommand) command)
											.setBeginDate(commands[2]);
									((OrderCommand) command)
											.setEndDate(commands[3]);
									command.sendRequestToServer(serverOutputStream);
								}
							} else if (clientCommand
									.equalsIgnoreCase("ShowOrders")) {
								if (commands.length == 4) {
									command = (ShowOrdersCommand) Commands.SHOWORDERS
											.getCommandInstance();
									((ShowOrdersCommand) command)
											.setDate(commands[1]);
									((ShowOrdersCommand) command)
											.setType(commands[2]);
									((ShowOrdersCommand) command)
											.setFileName(commands[3]);
									command.sendRequestToServer(serverOutputStream);
								}
							} else if (clientCommand
									.equalsIgnoreCase("shutdown")) {
								command = Commands.SHUTDOWN
										.getCommandInstance();
								command.sendRequestToServer(serverOutputStream);
								break;
							} else if (clientCommand.equalsIgnoreCase("exit")) {
								command = Commands.EXIT.getCommandInstance();
								command.sendRequestToServer(serverOutputStream);
								break;
							} else if (clientCommand
									.equalsIgnoreCase("showcars")) {
								command = Commands.SHOWCARS
										.getCommandInstance();
								command.sendRequestToServer(serverOutputStream);
							} else {
								command = Commands.MESSAGE.getCommandInstance();
								((SendMessageCommand) command)
										.setMessage(wholeCommand);
								command.sendRequestToServer(serverOutputStream);
							}
						} catch (IOException e) {
							e.printStackTrace();
						}

					}

				}

			});
			consoleThread.setDaemon(true);
			consoleThread.start();

			while (true) {
				synchronized (socket) {
					if (socket.isClosed()) {
						close();
						break;
					}

					String commandName = serverInputStream.readUTF();
					Commands cmd = Commands.valueOf(commandName);
					ICommand serverCommand = cmd.getCommandInstance();
					serverCommand.processResponseFromServer(serverInputStream);

				}
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} finally {
			close();

		}

	}

	public static void main(String[] args) throws IOException {
		String ip = "127.0.0.1";
		int port = 1234;
		new Client(ip, port).run();
	}

	private synchronized void close() throws IOException {
		if (serverOutputStream != null) {
			serverOutputStream.close();
		}
		if (serverInputStream != null) {
			serverInputStream.close();
		}
		if (consoleInputStream != null) {
			consoleInputStream.close();
		}
		if (socket != null && !socket.isClosed()) {
			socket.close();
		}
		System.exit(0);
	}

}
