package epam.course.commands;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import epam.course.server.ClientThread;

public class ExitCommand extends AbstractCommand {

	@Override
	public void sendRequestToServer(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.EXIT.name());
		
	}

	@Override
	public void processResponseFromServer(DataInputStream in)
			throws IOException {
		System.out.println("Exiting");
		System.exit(0);

		
	}

	@Override
	public void processClientRequest(DataInputStream in) throws IOException {
		SendMessageCommand msg = new SendMessageCommand();
		ClientThread clientThread = getClientThread();
		msg.setMessage(clientThread.getClientName()+" has quit");
		msg.setClientThread(clientThread);
		//getServer().sendMessageToClients(msg,clientThread);
		getServer().removeClient(clientThread);

		
	}

	@Override
	public void sendResponseToClient(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.EXIT.name());
		
	}

	
	

}
