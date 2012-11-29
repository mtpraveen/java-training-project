package commands;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import server.ClientThreadAtServer;

public class RemoveUserCommand extends AbstractCommand {
	private String login;
	private boolean isRemove;

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

	@Override
	public void sendRequestToServer(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.REMOVEUSER.name());
		out.writeUTF(login);
	}

	@Override
	public void processResponseFromServer(DataInputStream in)
			throws IOException {
		System.out.println(in.readUTF());
	}

	@Override
	public void processClientRequest(DataInputStream in) throws IOException {
		login = in.readUTF();
		this.getServer().setDataLoading(true);
		this.getServer().waitProcessTermination();
		isRemove = this.getServer().getHospital().removeUser(login);
		if (isRemove) {
			for (ClientThreadAtServer t : this.getServer().getThreads()) {
				ExitCommand cmd=new ExitCommand();
				if (t.getClientName().equals(login)) {
					t.addCommand(cmd);
				}
			}

		}
		this.getServer().setDataLoading(false);
	}

	@Override
	public void sendResponseToClient(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.REMOVEUSER.name());
		if (isRemove) {
			out.writeUTF("User is removed!");
		} else {
			out.writeUTF("Login not found!");
		}
	}

	@Override
	public void clearClientRequest(DataInputStream in) throws IOException {
		in.readUTF();
	}

	public RemoveUserCommand() {
		super.setDeclaration("Removing a user by username");
	}

}
