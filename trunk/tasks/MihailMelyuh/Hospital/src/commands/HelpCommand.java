package commands;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class HelpCommand extends AbstractCommand {
	private String information;

	@Override
	public void sendRequestToServer(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.HELP.name());
	}

	@Override
	public void processResponseFromServer(DataInputStream in)
			throws IOException {
		System.out.println(in.readUTF());
	}

	@Override
	public void processClientRequest(DataInputStream in) throws IOException {
		information = "";
		String FORMAT = "%s\n";
		String FORMATNAME = "%-15.15s";
		information = information.concat(String.format(FORMATNAME, "Commands"));
		information = information.concat(String.format(FORMAT, "Declaration"));
		information = information.concat(String.format(FORMATNAME,
				(Commands.EXIT.name())));
		information = information.concat(String.format(FORMAT,
				((AbstractCommand) Commands.EXIT.getCommandInstance())
						.getDeclaration()));
		information = information.concat(String.format(FORMATNAME,
				(Commands.ADDUSER.name())));
		information = information.concat(String.format(FORMAT,
				((AbstractCommand) Commands.ADDUSER.getCommandInstance())
						.getDeclaration()));
		information = information.concat(String.format(FORMATNAME,
				(Commands.ADDDATA.name())));
		information = information.concat(String.format(FORMAT,
				((AbstractCommand) Commands.ADDDATA.getCommandInstance())
						.getDeclaration()));
		information = information.concat(String.format(FORMATNAME,
				(Commands.CHANGEPASSWORD.name())));
		information = information.concat(String
				.format(FORMAT, ((AbstractCommand) Commands.CHANGEPASSWORD
						.getCommandInstance()).getDeclaration()));
		information = information.concat(String.format(FORMATNAME,
				(Commands.CSVREPORT.name())));
		information = information.concat(String.format(FORMAT,
				((AbstractCommand) Commands.CSVREPORT.getCommandInstance())
						.getDeclaration()));
		information = information.concat(String.format(FORMATNAME,
				(Commands.LOADDATA.name())));
		information = information.concat(String.format(FORMAT,
				((AbstractCommand) Commands.LOADDATA.getCommandInstance())
						.getDeclaration()));
		information = information.concat(String.format(FORMATNAME,
				(Commands.REMOVEUSER.name())));
		information = information.concat(String.format(FORMAT,
				((AbstractCommand) Commands.REMOVEUSER.getCommandInstance())
						.getDeclaration()));
		information = information.concat(String.format(FORMATNAME,
				(Commands.REPORT.name())));
		information = information.concat(String.format(FORMAT,
				((AbstractCommand) Commands.REPORT.getCommandInstance())
						.getDeclaration()));
		information = information.concat(String.format(FORMATNAME,
				(Commands.SAVEDATA.name())));
		information = information.concat(String.format(FORMAT,
				((AbstractCommand) Commands.SAVEDATA.getCommandInstance())
						.getDeclaration()));
		information = information.concat(String.format(FORMATNAME,
				(Commands.SESSIONSTAT.name())));
		information = information.concat(String.format(FORMAT,
				((AbstractCommand) Commands.SESSIONSTAT.getCommandInstance())
						.getDeclaration()));
		information = information.concat(String.format(FORMATNAME,
				(Commands.SHUTDOWN.name())));
		information = information.concat(String.format(FORMAT,
				((AbstractCommand) Commands.SHUTDOWN.getCommandInstance())
						.getDeclaration()));
		information = information.concat(String.format(FORMATNAME,
				(Commands.APPOINTASSIGNMENT.name())));
		information = information.concat(String.format(FORMAT,
				((AbstractCommand) Commands.APPOINTASSIGNMENT.getCommandInstance())
						.getDeclaration()));
		information = information.concat(String.format(FORMATNAME,
				(Commands.APPOINTDOCTOR.name())));
		information = information.concat(String.format(FORMAT,
				((AbstractCommand) Commands.APPOINTDOCTOR.getCommandInstance())
						.getDeclaration()));
		information = information.concat(String.format(FORMATNAME,
				(Commands.HELP.name())));
		information = information.concat(String.format(FORMAT,
				((AbstractCommand) Commands.HELP.getCommandInstance())
						.getDeclaration()));
		information = information.concat(String.format(FORMATNAME,
				(Commands.REMOVEPATIENT.name())));
		information = information.concat(String.format(FORMAT,
				((AbstractCommand) Commands.REMOVEPATIENT.getCommandInstance())
						.getDeclaration()));
	}

	@Override
	public void sendResponseToClient(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.HELP.name());
		out.writeUTF(information);
	}

	@Override
	public void clearClientRequest(DataInputStream in) throws IOException {

	}

	public HelpCommand() {
		super.setDeclaration("Help");
	}

	@Override
	public void setParametrs(DataOutputStream serverOutputStream,
			BufferedReader consoleInputStream) throws IOException {
		// TODO Auto-generated method stub
		
	}

}
