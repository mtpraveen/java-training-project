package commands;

import hospital.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ChangePasswordCommand extends AbstractCommand{
	private String password;
	private boolean isChanged;
	
	
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void sendRequestToServer(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.CHANGEPASSWORD.name());
		out.writeUTF(password);
	}

	@Override
	public void processResponseFromServer(DataInputStream in)
			throws IOException {
		System.out.println(in.readUTF());
	}

	@Override
	public void processClientRequest(DataInputStream in) throws IOException {
		password=in.readUTF();
		String login= this.getClientThread().getClientName();
		isChanged=false;
		ArrayList<User> users=this.getServer().getHospital().getUsers();
		for (int i=0;i<users.size();i++){
			if(login.equals(users.get(i).getLogin())){
				users.get(i).setPassword(password);
				isChanged=true;
				break;
			}
		}
	}

	@Override
	public void sendResponseToClient(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.CHANGEPASSWORD.name());
		if (isChanged){
			out.writeUTF("Password is changed!");
		} else {
			out.writeUTF("Changed password error!");
		}
	}

	@Override
	public void clearClientRequest(DataInputStream in) throws IOException {
		in.readUTF();
	}

	public ChangePasswordCommand() {
		super.setDeclaration("Changing your current password");
	}

}
