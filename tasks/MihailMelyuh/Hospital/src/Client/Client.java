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
				System.out.println("Input login:");
				String login = consoleInputStream.readLine();
				System.out.println("Input password:");
				String password = consoleInputStream.readLine();
				ICommand command = Commands.LOGIN.getCommandInstance();
				((LoginUserCommand) command).setLogin(login);
				((LoginUserCommand) command).setPassword(password);
				command.sendRequestToServer(serverOutputStream);
				String commandName = serverInputStream.readUTF();
				Commands cmd = Commands.valueOf(commandName);
				ICommand serverCommand = cmd.getCommandInstance();
				serverCommand.processResponseFromServer(serverInputStream);
				if (((LoginUserCommand) serverCommand).isLogin()) {
					System.out.println("Connection is successful");
					break;
				} else {
					System.out.println("Such user dosn't exist!");
				}
			}
			Thread consoleThread = new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						while (true) {
							String clientCommand = consoleInputStream
									.readLine();
							ICommand command;
							if (clientCommand.equalsIgnoreCase("shutdown")) {
								command = Commands.SHUTDOWN
										.getCommandInstance();
								command.sendRequestToServer(serverOutputStream);
								break;
							} else if (clientCommand.equalsIgnoreCase("exit")) {
								command = Commands.EXIT.getCommandInstance();
								command.sendRequestToServer(serverOutputStream);
								break;
							} else if (clientCommand
									.equalsIgnoreCase("loaddata")) {
								command = Commands.LOADDATA
										.getCommandInstance();
								command.sendRequestToServer(serverOutputStream);
							} else if (clientCommand
									.equalsIgnoreCase("savedata")) {
								command = Commands.SAVEDATA
										.getCommandInstance();
								command.sendRequestToServer(serverOutputStream);
							} else if (clientCommand
									.equalsIgnoreCase("sessionstat")) {
								command = Commands.SESSIONSTAT
										.getCommandInstance();
								command.sendRequestToServer(serverOutputStream);
							} else if (clientCommand
									.equalsIgnoreCase("csvreport")) {
								command = Commands.CSVREPORT
										.getCommandInstance();
								System.out.println("Input filePath:");
								((CsvReportCommand) command)
										.setFilePath(consoleInputStream
												.readLine());
								command.sendRequestToServer(serverOutputStream);
							} else if (clientCommand
									.equalsIgnoreCase("adduser")) {
								command = Commands.ADDUSER.getCommandInstance();
								System.out.println("Enter login:");
								((AddUserCommand) command)
										.setLogin(consoleInputStream.readLine());
								System.out.println("Enter password:");
								((AddUserCommand) command)
										.setPassword(consoleInputStream
												.readLine());
								command.sendRequestToServer(serverOutputStream);
							} else if (clientCommand
									.equalsIgnoreCase("removeuser")) {
								command = Commands.REMOVEUSER
										.getCommandInstance();
								System.out.println("Enter login:");
								((RemoveUserCommand) command)
										.setLogin(consoleInputStream.readLine());
								command.sendRequestToServer(serverOutputStream);
							} else if (clientCommand
									.equalsIgnoreCase("adddata")) {
								command = Commands.ADDDATA.getCommandInstance();
								System.out.println("Enter filepath:");
								((AddDataCommand) command)
										.setFilePath(consoleInputStream
												.readLine());
								command.sendRequestToServer(serverOutputStream);
							} else if (clientCommand
									.equalsIgnoreCase("changepassword")) {
								command = Commands.CHANGEPASSWORD
										.getCommandInstance();
								System.out.println("Enter new password:");
								((ChangePasswordCommand) command)
										.setPassword(consoleInputStream
												.readLine());
								command.sendRequestToServer(serverOutputStream);
							} else if (clientCommand.equalsIgnoreCase("report")) {
								command = Commands.REPORT.getCommandInstance();
								command.sendRequestToServer(serverOutputStream);
							} else if (clientCommand.equalsIgnoreCase("help")) {
								command = Commands.HELP.getCommandInstance();
								command.sendRequestToServer(serverOutputStream);
							} else if (clientCommand
									.equalsIgnoreCase("appointdoctor")) {
								command = Commands.APPOINTDOCTOR
										.getCommandInstance();
								boolean correct = false;
								while (!correct) {
									try {
										System.out.println("Enter patientID:");
										((AppointDoctorCommand) command).setPatientID(Integer
												.valueOf(consoleInputStream
														.readLine()));
										System.out.println("Enter doctorID:");
										((AppointDoctorCommand) command).setDoctorID(Integer
												.valueOf(consoleInputStream
														.readLine()));
										correct = true;
									} catch (NumberFormatException e) {
										correct = false;
										System.out
												.println("Incorrect number format. Try again.");
									}
								}
								command.sendRequestToServer(serverOutputStream);
							} else if (clientCommand
									.equalsIgnoreCase("appointassignment")) {
								command = Commands.APPOINTASSIGNMENT
										.getCommandInstance();
								boolean correct = false;
								while (!correct) {
									try {
										System.out.println("Enter patientID:");
										((AppointAssignmentCommand) command).setPatientID(Integer
												.valueOf(consoleInputStream
														.readLine()));
										System.out.println("Enter assignment:");
										((AppointAssignmentCommand) command)
												.setText(consoleInputStream
														.readLine());
										System.out
												.println("Enter performer(Nurse,Doctor):");
										((AppointAssignmentCommand) command)
												.setPosition(Positions.valueOf(
														consoleInputStream
																.readLine())
														.name());
										System.out
												.println("Enter performerID:");
										((AppointAssignmentCommand) command).setPersonID(Integer
												.valueOf(consoleInputStream
														.readLine()));
										correct = true;
									} catch (IllegalArgumentException e1) {
										correct = false;
										System.out
												.println("Incorrect number format. Try again.");
									}
								}
								command.sendRequestToServer(serverOutputStream);
							} else if (clientCommand
									.equalsIgnoreCase("removepatient")) {
								command = Commands.REMOVEPATIENT
										.getCommandInstance();
								boolean correct = false;
								while (!correct) {
									try {
										System.out.println("Enter patientID:");
										((RemovePatientCommand) command).setPatientID(Integer
												.valueOf(consoleInputStream
														.readLine()));
										correct = true;
									} catch (NumberFormatException e) {
										correct = false;
										System.out
												.println("Incorrect number format. Try again.");
									}
								}
								command.sendRequestToServer(serverOutputStream);
							} else {
								command = Commands.MESSAGE.getCommandInstance();
								((SendMessageCommand) command)
										.setMessage(clientCommand);
								command.sendRequestToServer(serverOutputStream);
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

	public static void main(String[] args) throws Exception{
		String ip = args.length >= 1 ? args[0] : "192.168.56.1";
		int port = 1111;
		try {
			new Client(ip, port).run();
		}
		catch (IOException e){
			Client.LOGGER.error("Error establishing a connection",e);
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
