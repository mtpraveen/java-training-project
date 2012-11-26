package com.epam.library.client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import com.epam.library.commands.ChangeThePasswordCommand;
import com.epam.library.commands.Commands;
import com.epam.library.commands.ICommand;
import com.epam.library.commands.LoadDataCommand;
import com.epam.library.commands.LoginCommand;

public class Client {
	private DataInputStream serverInputStream;
	private DataOutputStream serverOutputStream;
	private BufferedReader consoleInputStream = new BufferedReader(
			new InputStreamReader(System.in));
	private Socket socket;

	private String ip = "127.0.0.1";
	private int port = 1234;

	public Client(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	public void signIn() {
		Scanner scan = new Scanner(System.in);
		// Console console = System.console();
		System.out.print("Please enter your login and password:\nLogin: ");
		String login = scan.nextLine();
		// String login = console.readLine();
		System.out.print("Password: ");
		String password = scan.nextLine();
		// String password = console.readPassword().toString();
		ICommand command;
		command = Commands.LOGIN.getCommandInstance();
		((LoginCommand) command).setLogin(login);
		((LoginCommand) command).setPassword(password);
		try {
			command.sendRequestToServer(serverOutputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
					try {
						signIn();
						while (true) {
							Thread.sleep(1000);
							printMenu();
							String clientCommand = consoleInputStream
									.readLine();
							int menuPoint = Integer.parseInt(clientCommand);
							ICommand command;
							Scanner scan = new Scanner(System.in);
							switch (menuPoint) {
							case 1:
								command = Commands.ADDUSER.getCommandInstance();
								command.sendRequestToServer(serverOutputStream);
								break;
							case 2:
								command = Commands.CHANGETHELANGUAGE
										.getCommandInstance();
								command.sendRequestToServer(serverOutputStream);
								break;
							case 3:
								command = Commands.CHANGETHEPASSWORD
										.getCommandInstance();
								System.out
										.println("Please, enter your new password");
								/*
								 * Console console = System.console(); String
								 * newPassword = console.readLine();
								 */

								String newPassword = scan.nextLine();
								((ChangeThePasswordCommand) command)
										.setPassword(newPassword);
								command.sendRequestToServer(serverOutputStream);
								break;
							case 4:
								command = Commands.DELLETEUSER
										.getCommandInstance();
								command.sendRequestToServer(serverOutputStream);
								break;
							case 5:
								command = Commands.ENDTHESESSION
										.getCommandInstance();
								command.sendRequestToServer(serverOutputStream);
								break;
							case 6:
								command = Commands.GETREPORT
										.getCommandInstance();
								command.sendRequestToServer(serverOutputStream);
								break;
							case 7:
								command = Commands.GETREPORTINFILE
										.getCommandInstance();
								command.sendRequestToServer(serverOutputStream);
								break;
							case 8:
								command = Commands.GETSTATISTIC
										.getCommandInstance();
								command.sendRequestToServer(serverOutputStream);
								break;
							case 9:
								command = Commands.LOADTHEDATA
										.getCommandInstance();
								System.out.print("Enter the absolute path: ");
								// Scanner scann = new Scanner(System.in);

								String path = scan.nextLine();
								((LoadDataCommand) command).setPath(path);
								command.sendRequestToServer(serverOutputStream);
								break;
							case 10:
								command = Commands.ORDERTHEBOOK
										.getCommandInstance();
								command.sendRequestToServer(serverOutputStream);

								break;
							case 11:
								command = Commands.SHOUTDOWN
										.getCommandInstance();
								command.sendRequestToServer(serverOutputStream);
								break;
							case 12:
								command = Commands.WRITEINBLACKLIST
										.getCommandInstance();
								command.sendRequestToServer(serverOutputStream);
								break;
							case 13:
								signIn();
								break;
							default:
								System.out.println("Invalid value!");
								break;

							}
						}
					} catch (IOException | InterruptedException e) {
						e.printStackTrace();// here we should handle it properly
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

	private void close() throws IOException {
		if (serverInputStream != null)
			serverInputStream.close();
		if (serverOutputStream != null)
			serverOutputStream.close();
		if (consoleInputStream != null)
			consoleInputStream.close();
		if (!socket.isClosed())
			socket.close();
	}

	public void printMenu() {
		System.out
				.println("1 - Add user\n2 - Change the language\n3 - Change the password\n4 - Remove user\n5 - End the session\n6 - Get report\n7 - Get report in file\n8 - Get statistic\n9 - Load data\n10 - Order the book\n11 - Shoutdown\n12 - Write in black list\n13 - Sign in");
	}

	public static void main(String[] args) throws IOException {
		String ip = "127.0.0.1";
		int port = 1234;
		new Client(ip, port).run();
	}

}
