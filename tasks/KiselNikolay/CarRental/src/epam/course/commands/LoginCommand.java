package epam.course.commands;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ResourceBundle;

import epam.course.client.Client;
import epam.course.server.ClientThread;

public class LoginCommand extends AbstractCommand {

	private String login;
	private String password;
	private boolean logged;

	private static final String PREFIX = "%1$s (%2$s) at %3$tH:%3$tM:%3$tS > %4$s";

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLogged() {
		return logged;
	}

	public void setLogged(boolean logged) {
		this.logged = logged;
	}

	// 1
	@Override
	public void sendRequestToServer(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.LOGIN.name());
		out.writeUTF(login);
		out.writeUTF(password);
	}

	// 4
	@Override
	public void processResponseFromServer(DataInputStream in)
			throws IOException {
		System.out.println(in.readUTF());
		Client.logged=true;
		
	}

	// 2
	@Override
	public void processClientRequest(DataInputStream in) throws IOException {
		login = in.readUTF();
		password = in.readUTF();
		ClientThread clientThread = getClientThread();
//		System.out.println("Client with login: " + login + " and password "
//				+ password);
		getServer().loginCustomer(this, clientThread);
	}

	private String generateMessageText(ClientThread clientThread) {
		return String.format(PREFIX, clientThread.getClientName(),
				clientThread.getAddress(), getTimestamp(), login);
	}

	// 3
	@Override
	public void sendResponseToClient(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.LOGIN.name());
		ResourceBundle resourseBundle =
				ResourceBundle.getBundle("messages", getClientThread().getLocale());
		if (isLogged()) {
			out.writeUTF("Welcome " + getClientThread().getCustomer().getName()
					+ " " + getClientThread().getCustomer().getSurname());
		}
		else {
			String str=resourseBundle.getString("login_err");
			out.writeUTF(str);
		}

	}
}
