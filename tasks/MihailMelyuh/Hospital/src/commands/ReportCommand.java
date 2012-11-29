package commands;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;


public class ReportCommand extends AbstractCommand{
	@Override
	public void sendRequestToServer(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.REPORT.name());
	}

	@Override
	public void processResponseFromServer(DataInputStream in)
			throws IOException {
		System.out.println(in.readUTF());
	}

	@Override
	public void processClientRequest(DataInputStream in) throws IOException {
		
	}

	@Override
	public void sendResponseToClient(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.REPORT.name());
		out.writeUTF(this.getServer().getHospital().report());
	}

	@Override
	public void clearClientRequest(DataInputStream in) throws IOException {
		
	}

	public ReportCommand() {
		super.setDeclaration("Report output to the screen");
	}

}
