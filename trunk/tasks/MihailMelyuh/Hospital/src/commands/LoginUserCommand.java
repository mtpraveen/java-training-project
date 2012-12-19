package commands;

import hospital.AccessRights;
import hospital.User;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class LoginUserCommand extends AbstractCommand {
	private String login;
	private String password;
	private boolean isLogin;

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login
	 *            the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the isLogin
	 */
	public boolean isLogin() {
		return isLogin;
	}

	/**
	 * @param isLogin
	 *            the isLogin to set
	 */
	public void setIsLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}

	@Override
	public void sendRequestToServer(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.LOGIN.name());
		out.writeUTF(login);
		out.writeUTF(password);
	}

	@Override
	public void processResponseFromServer(DataInputStream in)
			throws IOException {
		isLogin = in.readBoolean();
	}

	@Override
	public void processClientRequest(DataInputStream in) throws IOException {
		isLogin = false;
		login = in.readUTF();
		password = in.readUTF();
		AccessRights rights = AccessRights.Unknown;
		ArrayList<User> users = this.getServer().getHospital().getUsers();
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getPassword().equals(password)
					&& users.get(i).getLogin().equals(login)) {
				isLogin = true;
				rights = users.get(i).getAccessRights();
				break;
			}
		}
		if (isLogin) {
			this.getServer().getClients().add(login);
			this.getClientThread().setClientName(login);
			this.getServer().userIsLogin(login);
			this.getClientThread().setAccessRights(rights);
		}
	}

	@Override
	public void sendResponseToClient(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.LOGIN.name());
		out.writeBoolean(isLogin);
	}

	@Override
	public void clearClientRequest(DataInputStream in) throws IOException {
		in.readUTF();
		in.readUTF();
	}

	public LoginUserCommand() {
		super.setDeclaration("Log in");
	}

	@Override
	public void setParametrs(DataOutputStream serverOutputStream,
			BufferedReader consoleInputStream) throws IOException {
		System.out.println("Input login:");
		String login = consoleInputStream.readLine();
		System.out.println("Input password:");
		String password = consoleInputStream.readLine();
		this.setLogin(login);
		this.setPassword(password);
	}

}
