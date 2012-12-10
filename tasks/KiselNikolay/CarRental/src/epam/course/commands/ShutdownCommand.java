package epam.course.commands;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ShutdownCommand extends AbstractCommand {
	private int delay;
	private String message;

	@Override
	public void sendRequestToServer(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.SHUTDOWN.name());
		out.writeInt(10);
		out.flush();
	}

	@Override
	public void processResponseFromServer(DataInputStream in)
			throws IOException {
		System.out.println(in.readUTF());
		System.exit(0);
	}

	@Override
	public void processClientRequest(final DataInputStream out)
			throws IOException {
		delay = out.readInt();
		message = "Shutdown of the server in " + delay + " secs requested by "
		+ getClientThread().getClientName() + ". You will quit.";
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				getServer().shutdown(ShutdownCommand.this);
			}

		}).start();

	}

	@Override
	public void sendResponseToClient(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.SHUTDOWN.name());
		out.writeUTF(message);
		out.flush();
		getClientThread().close();
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
