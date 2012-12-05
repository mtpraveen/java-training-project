package com.epam.library.commands;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ShutDownCommand extends AbstractCommand {
	private String message;
	private int delay;

	@Override
	public void sendRequestToServer(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.SHUTDOWN.name());
		out.writeInt(10);
		out.flush();

	}

	@Override
	public void processClientRequest(DataInputStream out) throws IOException {
		delay = out.readInt();
		message="Shut down of the server in "+delay+" secs requested by "+getClientThread().getName()+". You will quit.";
		new Thread(new Runnable() {

			@Override
			public void run() {
					getServer().shutdown(ShutDownCommand.this);
			}

		}).start();

	}

	@Override
	public void processResponseFromServer(DataInputStream in)
			throws IOException {
		System.out.println(in.readUTF());
		System.exit(0);

	}

	@Override
	public void sendResponseToClient(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.SHUTDOWN.name());
		out.writeUTF(message);
		out.flush();
		getClientThread().close();

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

}
