package commands;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import server.ClientThreadAtServer;

public class ExitCommand extends AbstractCommand {

	@Override
	public void sendRequestToServer(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.EXIT.name());
	}

	@Override
	public void processResponseFromServer(DataInputStream in) throws IOException {
		System.out.println("Exiting");
		System.exit(0);
	}

	@Override
	public void processClientRequest(DataInputStream in)  throws IOException{
		SendMessageCommand msg = new SendMessageCommand();
		ClientThreadAtServer clientThreadAtServer = getClientThread();
		msg.setMessage(clientThreadAtServer.getClientName()+" has quit");
		msg.setClientThread(clientThreadAtServer);
		getServer().sendMessageToClients(msg,clientThreadAtServer);
		getServer().removeClient(clientThreadAtServer);
	}

	@Override
	public void sendResponseToClient(DataOutputStream out)  throws IOException{
		out.writeUTF(Commands.EXIT.name());
	}

	@Override
	public void clearClientRequest(DataInputStream in) throws IOException {
		
	}

	public ExitCommand() {
		super.setDeclaration("Finish work");
	}

	@Override
	public void setParametrs(DataOutputStream serverOutputStream,BufferedReader consoleInputStream)  throws IOException {
		
	}
}
