package commands;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import server.ClientThreadAtServer;

public class SendMessageCommand extends AbstractCommand {
	private String message;

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public void sendRequestToServer(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.MESSAGE.name());
		out.writeUTF(message);

	}

	@Override
	public void processResponseFromServer(DataInputStream out)
			throws IOException {
		System.out.println(out.readUTF());

	}

	@Override
	public void processClientRequest(DataInputStream in) throws IOException {
		message = in.readUTF();
		message = "Unknown command";
	}

	private String generateMessageText(ClientThreadAtServer clientThreadAtServer) {
		return message;
	}

	@Override
	public void sendResponseToClient(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.MESSAGE.name());
		out.writeUTF(generateMessageText(getClientThread()));

	}

	@Override
	public void clearClientRequest(DataInputStream in) throws IOException {
		in.readUTF();
	}

	public SendMessageCommand() {
		super.setDeclaration("Sending a unknown message to the system");
	}

	@Override
	public void setParametrs(DataOutputStream serverOutputStream,
			BufferedReader consoleInputStream) throws IOException {
		this.setMessage("Unknown command.");
	}

}
