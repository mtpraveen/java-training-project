package commands;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class AddUserCommand extends AbstractCommand {
	private String login;
	private String password;
	private boolean isAdded;

	/**
	 * @param login
	 *            the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void sendRequestToServer(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.ADDUSER.name());
		out.writeUTF(login);
		out.writeUTF(password);
	}

	@Override
	public void processResponseFromServer(DataInputStream in)
			throws IOException {
		System.out.println(in.readUTF());
	}

	@Override
	public void processClientRequest(DataInputStream in) throws IOException {
		login = in.readUTF();
		password = in.readUTF();
		isAdded = this.getServer().getHospital().addUser(login, password);
	}

	@Override
	public void sendResponseToClient(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.ADDUSER.name());
		if (isAdded) {
			out.writeUTF("User added.");
		} else {
			out.writeUTF("Such login already exists.");
		}
	}

	@Override
	public void clearClientRequest(DataInputStream in) throws IOException {
		in.readUTF();
		in.readUTF();
	}

	public AddUserCommand() {
		super.setDeclaration("Adding a user to the database");
	}

	@Override
	public void setParametrs(DataOutputStream serverOutputStream,
			BufferedReader consoleInputStream) throws IOException {
		System.out.println("Enter login:");
		this.setLogin(consoleInputStream.readLine());
		System.out.println("Enter password:");
		this.setPassword(consoleInputStream.readLine());
	}

}
