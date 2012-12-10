package epam.course.commands;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ResourceBundle;

import epam.course.client.Client;
import epam.course.server.ClientThread;

public class RegistrationCommand extends AbstractCommand {

	private String name;
	private String surname;
	private String numberPassp;
	private String login;
	private String password;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getNumberPassp() {
		return numberPassp;
	}

	public void setNumberPassp(String numberPassp) {
		this.numberPassp = numberPassp;
	}

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

	@Override
	public void sendRequestToServer(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.REGISTRATION.name());
		out.writeUTF(name);
		out.writeUTF(surname);
		out.writeUTF(numberPassp);
		out.writeUTF(login);
		out.writeUTF(password);
		
	}

	@Override
	public void processResponseFromServer(DataInputStream in)
			throws IOException {
		System.out.println(in.readUTF());
		Client.logged=true;
	}

	@Override
	public void processClientRequest(DataInputStream in) throws IOException {
		name=in.readUTF();
		surname=in.readUTF();
		numberPassp=in.readUTF();
		login=in.readUTF();
		password = in.readUTF();
		ClientThread clientThread = getClientThread();
		getServer().registrationCustomer(this, clientThread);
	}

	@Override
	public void sendResponseToClient(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.LOGIN.name());
		ResourceBundle resourseBundle =
				ResourceBundle.getBundle("messages", getClientThread().getLocale());
		String str=resourseBundle.getString("registration");
		out.writeUTF(str);
				
	}

}
