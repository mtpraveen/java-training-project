package Client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import position.Positions;

import commands.*;

public class Client {
	private static final Logger LOGGER = Logger.getLogger(Client.class);
	private DataInputStream serverInputStream;
	private DataOutputStream serverOutputStream;
	private BufferedReader consoleInputStream = new BufferedReader(
			new InputStreamReader(System.in));
	private Socket socket;

	private String ip;
	private int port;

	public Client(String ip, int port) {
		PropertyConfigurator.configure("log4j.properties");
		this.ip = ip;
		this.port = port;
	}

	public void run() throws IOException {

		try {
			InetAddress address = InetAddress.getByName(ip);
			socket = new Socket(address, port);
			serverInputStream = new DataInputStream(socket.getInputStream());
			serverOutputStream = new DataOutputStream(socket.getOutputStream());
			while (true) {
				ICommand command = Commands.LOGIN.getCommandInstance();
				command.setParametrs(serverOutputStream, consoleInputStream);
				command.sendRequestToServer(serverOutputStream);
				String commandName = serverInputStream.readUTF();
				Commands cmd = Commands.valueOf(commandName);
				ICommand serverCommand = cmd.getCommandInstance();
				serverCommand.processResponseFromServer(serverInputStream);
				if (((LoginUserCommand) serverCommand).isLogin()) {
					System.out.println("Connection is successful");
					break;
				} else {
					System.out.println("Such login or password dosn't exist!");
				}
			}
			Thread consoleThread = new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						while (true) {
							String clientCommand = consoleInputStream
									.readLine();
							ICommand sendCommand;
							boolean findCommandName=false;
							for (Commands command:Commands.values()){
								if (clientCommand.equalsIgnoreCase(command.name())){
									sendCommand=command.getCommandInstance();
									sendCommand.setParametrs(serverOutputStream,
											consoleInputStream);
									sendCommand.sendRequestToServer(serverOutputStream);
									findCommandName=true;
									break;
								}
							}
							if(!findCommandName){
								sendCommand = Commands.MESSAGE.getCommandInstance();
								sendCommand.setParametrs(serverOutputStream, consoleInputStream);
								sendCommand.sendRequestToServer(serverOutputStream);
							}
						}
					} catch (IOException e) {
						Client.LOGGER.error("Error sending instructions.");
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
			Client.LOGGER.error(e);
		} finally {
			close();

		}

	}

	public static void main(String[] args) throws Exception {
		String ip = args.length >= 1 ? args[0] : "192.168.56.1";
		int port = 1111;
		try {
			new Client(ip, port).run();
		} catch (IOException e) {
			Client.LOGGER.error("Error establishing a connection", e);
			throw new Exception("Error establishing a connection");
		}

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

	/**
	 * @return the logger
	 */
	public static Logger getLogger() {
		return LOGGER;
	}

}
