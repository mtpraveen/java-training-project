package epam.course.commands;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ResourceBundle;

import epam.course.client.Client;
import epam.course.server.ClientThread;

public class SendMessageCommand extends AbstractCommand {

	private static final String PREFIX = "%1$s (%2$s) at %3$tH:%3$tM:%3$tS > %4$s";
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	//1
	@Override
	public void sendRequestToServer(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.MESSAGE.name());
		out.writeUTF(message);
	}

	//4
	@Override
	public void processResponseFromServer(DataInputStream in)
			throws IOException {
		System.out.println(in.readUTF());
	}

	//2
	@Override
	public void processClientRequest(DataInputStream in) throws IOException {
		message = in.readUTF();
		ClientThread clientThread = getClientThread();
		System.out.println(generateMessageText(clientThread));
		getServer().sendMessageToClients(this, clientThread);
	}
	
	private String generateMessageText(ClientThread clientThread) {
		return String.format(PREFIX, clientThread.getClientName(),
				clientThread.getAddress(), getTimestamp(), message);
	}

	//3
	@Override
	public void sendResponseToClient(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.MESSAGE.name());
		ResourceBundle resourseBundle =
				ResourceBundle.getBundle("messages", getClientThread().getLocale());
		if (message.equalsIgnoreCase(Client.Greeting))
			out.writeUTF(new String(resourseBundle.getString("not_logged_yet")));
		else
			out.writeUTF(message);

	}

}
