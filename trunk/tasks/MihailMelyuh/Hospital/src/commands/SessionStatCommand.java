package commands;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import server.ClientThreadAtServer;

public class SessionStatCommand extends AbstractCommand {
	private static final String PREFIX = "Client: %1$s; Adress:  %2$s \n";
	private String message;
	
	@Override
	public void sendRequestToServer(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.SESSIONSTAT.name());
	}

	@Override
	public void processResponseFromServer(DataInputStream in)
			throws IOException {
		System.out.println(in.readUTF());
	}

	@Override
	public void processClientRequest(DataInputStream in) throws IOException {
		message="";
		for(ClientThreadAtServer thread: this.getServer().getThreads()){
			message+=generateMessageText(thread);
		}
	}

	@Override
	public void sendResponseToClient(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.SESSIONSTAT.name());
		out.writeUTF(message);
	}
	
	private String generateMessageText(ClientThreadAtServer clientThreadAtServer) {
		return String.format(PREFIX,clientThreadAtServer.getClientName(),clientThreadAtServer.getAddress());
	}

	@Override
	public void clearClientRequest(DataInputStream in) throws IOException {
		
	}

	public SessionStatCommand() {
		super.setDeclaration("Getting information about the open sessions");
	}
	
}
