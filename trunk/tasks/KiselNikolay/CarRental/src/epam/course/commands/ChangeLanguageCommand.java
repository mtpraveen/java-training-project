package epam.course.commands;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ResourceBundle;

import epam.course.server.ClientThread;

public class ChangeLanguageCommand extends AbstractCommand {

	@Override
	public void sendRequestToServer(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.CHANGELANGUAGE.name());

	}

	@Override
	public void processResponseFromServer(DataInputStream in)
			throws IOException {
		System.out.println(in.readUTF());

	}

	@Override
	public void processClientRequest(DataInputStream in) throws IOException {
		ClientThread clientThread = getClientThread();
		getServer().changeLanguage(this, clientThread);

	}

	@Override
	public void sendResponseToClient(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.CHANGELANGUAGE.name());
		ResourceBundle resourseBundle = ResourceBundle.getBundle("messages",
				getClientThread().getLocale());
		String str = resourseBundle.getString("change_language");
		out.writeUTF(str);

	}

}
