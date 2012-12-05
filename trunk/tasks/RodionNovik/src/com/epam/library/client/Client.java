package com.epam.library.client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

import com.epam.library.commands.AddUserCommand;
import com.epam.library.commands.ChangeThePasswordCommand;
import com.epam.library.commands.Commands;
import com.epam.library.commands.DelleteUserCommand;
import com.epam.library.commands.GetReportInFile;
import com.epam.library.commands.ICommand;
import com.epam.library.commands.LoadDataCommand;
import com.epam.library.commands.LoginCommand;
import com.epam.library.commands.OrderTheBookCommand;
import com.epam.library.commands.ReturnTheBookCommand;
import com.epam.library.commands.WriteInBlackListCommand;

public class Client {
	private DataInputStream serverInputStream;
	private DataOutputStream serverOutputStream;
	private BufferedReader consoleInputStream = new BufferedReader(
			new InputStreamReader(System.in));
	private Socket socket;
	private static Locale currentLocale = new Locale("en", "US");
	private static ResourceBundle rb = ResourceBundle.getBundle("text",
			currentLocale);
	private String ip = "127.0.0.1";
	private int port = 1234;

	public Client(String ip, int port) {
		this.ip = ip;
		this.port = port;
		currentLocale = new Locale("en", "US");
		ResourceBundle rb = ResourceBundle.getBundle("text", currentLocale);
	}

	public static ResourceBundle getRb() {
		return rb;
	}

	public void signIn() {
		Scanner scan = new Scanner(System.in);
		// Console console = System.console();
		System.out.print(rb.getString("PleaseEnterYourLoginAndPassword") + ":\n" +rb.getString("Login") +": ");
		String login = scan.nextLine();
		// String login = console.readLine();
		System.out.print(rb.getString("Password")+": ");
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

	// public static ResourceBundle getRb() {
	// return rb;
	// }

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
							Thread.sleep(300);
							printMenu();
							String clientCommand = consoleInputStream
									.readLine();
							int menuPoint = Integer.parseInt(clientCommand);
							ICommand command;
							Scanner scan = new Scanner(System.in);
							switch (menuPoint) {
							case 1:
								command = Commands.ADDUSER.getCommandInstance();
								System.out.print(rb.getString("TypeOfUser")+" (Reader,Admin): ");
								String type = scan.nextLine();
								System.out.print(rb.getString("Login")+": ");
								String login = scan.nextLine();
								System.out.print(rb.getString("StartPassword")+": ");
								String password = scan.nextLine();
								System.out.print(rb.getString("NameLastName")+": ");
								String nameLastName = scan.nextLine();
								System.out.print(rb.getString("Address")+": ");
								String address = scan.nextLine();
								System.out
										.print("Date of Birth (yyyy-MM-dd): ");
								// SimpleDateFormat dateFormat = new
								// SimpleDateFormat(
								// "yyyy-MM-dd");
								// Date dateOfBirth = new Date();
								// try {
								// dateOfBirth = dateFormat.parse(scan
								// .nextLine());
								// } catch (ParseException e) {
								// // TODO Auto-generated catch block
								// e.printStackTrace();
								// }
								String dateOfBirth = scan.nextLine();
								((AddUserCommand) command).setParams(type,
										login, password, nameLastName,
										dateOfBirth, address);
								command.sendRequestToServer(serverOutputStream);
								break;
							case 2:
								System.out.println(Client.getRb().getString("LanguageMenu"));
								int choice = scan.nextInt();
								if (choice == 2) {
									currentLocale = new Locale("ru", "RU");
									Client.setRb(ResourceBundle.getBundle(
											"text", currentLocale));
								} else {
									currentLocale = new Locale("en", "US");
									ResourceBundle rb = ResourceBundle
											.getBundle("text", currentLocale);
									Client.setRb(ResourceBundle.getBundle(
											"text", currentLocale));
								}
								break;
							case 3:
								command = Commands.CHANGETHEPASSWORD
										.getCommandInstance();
								
								System.out
										.println(Client.getRb().getString("PleaseEnterYourNewPassword")+": ");
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
								System.out.print(Client.getRb().getString("Login")+": ");
								login = scan.nextLine();
								System.out.print(Client.getRb().getString("NameLastName")+": ");
								nameLastName = scan.nextLine();
								System.out.print(Client.getRb().getString("Address")+": ");
								address = scan.nextLine();
								System.out
										.print(Client.getRb().getString("DateOfBirth(yyyy-MM-dd)")+": ");
								dateOfBirth = scan.nextLine();

								((DelleteUserCommand) command).setParams(login,
										nameLastName, dateOfBirth, address);
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
								System.out.print(Client.getRb().getString("EnterTheDirectory")+": ");
								String path = scan.nextLine();
								((GetReportInFile) command).setPath(path
										+ "/report.txt");
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
								System.out.print(Client.getRb().getString("EnterTheAbsolutePath")+": ");
								// Scanner scann = new Scanner(System.in);

								path = scan.nextLine();
								((LoadDataCommand) command).setPath(path);
								command.sendRequestToServer(serverOutputStream);
								break;
							case 10:
								command = Commands.ORDERTHEBOOK
										.getCommandInstance();
								System.out.print(Client.getRb().getString("EnterNameOfTheBook")+": ");
								String name = scan.nextLine();
								System.out.print(Client.getRb().getString("EnterTheAuthor")+": ");
								String author = scan.nextLine();
								System.out.print(Client.getRb().getString("EnterYearOfProduction")+": ");
								int yearOfProduction = scan.nextInt();
								((OrderTheBookCommand) command).setParams(name,
										author, yearOfProduction);
								command.sendRequestToServer(serverOutputStream);

								break;
							case 11:
								command = Commands.SHUTDOWN
										.getCommandInstance();
								command.sendRequestToServer(serverOutputStream);
								break;
							case 12:
								command = Commands.WRITEINBLACKLIST
										.getCommandInstance();
								System.out.print(Client.getRb().getString("EnterTheLogin")+": ");
								login = scan.nextLine();
								((WriteInBlackListCommand) command)
										.setLogin(login);
								command.sendRequestToServer(serverOutputStream);
								break;
							case 13:
								command = Commands.RETURNTHEBOOK
										.getCommandInstance();
								System.out.print(Client.getRb().getString("EnterNameOfTheBook")+": ");
								name = scan.nextLine();
								System.out.print(Client.getRb().getString("EnterTheAuthor")+": ");
								author = scan.nextLine();
								System.out.print(Client.getRb().getString("EnterYearOfProduction")+": ");
								yearOfProduction = scan.nextInt();
								((ReturnTheBookCommand) command).setParams(
										name, author, yearOfProduction);
								command.sendRequestToServer(serverOutputStream);
								break;
							case 14:
								signIn();
								break;
							default:
								System.out.println(rb.getString("InvalidValue")+"!");
								break;

							}
						}
					} catch (IOException | InterruptedException e) {
						e.printStackTrace();
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
	// if added
					
						String commandName = serverInputStream.readUTF();
						Commands cmd = Commands.valueOf(commandName);
						ICommand serverCommand = cmd.getCommandInstance();
						serverCommand
								.processResponseFromServer(serverInputStream);
					
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

	public void printMenu() throws UnsupportedEncodingException {
		String st = rb.getString("menu");
		System.out.print(st);

	}

	public static void setRb(ResourceBundle rb) {
		Client.rb = rb;
	}

	public static void main(String[] args) throws IOException {
		String ip = "127.0.0.1";
		int port = 1234;
		new Client(ip, port).run();
	}

}
