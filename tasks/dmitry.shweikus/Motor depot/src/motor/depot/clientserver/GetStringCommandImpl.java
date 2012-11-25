/**
 * 
 */
package motor.depot.clientserver;

import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.apache.log4j.Logger;

/**
 * @author dima
 * 
 */
public class GetStringCommandImpl extends AbstractClientServerCommandImpl {
	boolean isPassword;
	private final static Logger LOGGER = Logger
			.getLogger(GetStringCommandImpl.class);

	/**
	 * @param command
	 */
	public GetStringCommandImpl(ClientServerCommand command) {
		super(command);
	}

	DataOutputStream toServer = null;

	private class WaitTextThread implements Runnable {
		@Override
		public void run() {
			String text;
			Console console = System.console();
			if (console == null) {
				Scanner scanner = new Scanner(System.in);
				text = scanner.nextLine();
			} else if (isPassword) {
				text = new String(console.readPassword());
			} else {
				text = console.readLine();
			}
			try {
				toServer.writeUTF(text);
			} catch (IOException e) {
				LOGGER.error("IOException by sendig text message to client", e);
			}
		}
	}

	private void sendFile(String fileName) {
		File file = new File(fileName);
		boolean fileSended = false;
		if (file.exists())
			if (file.isFile()) {
				DataInputStream dataInputStream = null;
				try {
					toServer.writeUTF("" + file.length());
					LOGGER.debug("Start sending to client file '" + fileName
							+ "'");
					dataInputStream = new DataInputStream(new FileInputStream(
							file));
					byte[] buf = new byte[4096];
					while (true) {
						int nLength = dataInputStream.read(buf);
						if (nLength <= 0) {
							break;
						}
						LOGGER.debug("Send to client next file part");
						toServer.write(buf, 0, nLength);
					}
					LOGGER.debug("File sended");
					fileSended = true;
				} catch (FileNotFoundException e) {
					//
				} catch (IOException e) {
					LOGGER.error("IOException by reading sended file", e);
				} finally {
					if (dataInputStream != null)
						try {
							dataInputStream.close();
						} catch (IOException e) {
						}
				}
			}
		if (!fileSended)
			try {
				toServer.writeUTF("-1");
			} catch (IOException e) {
				LOGGER.error("IOException by sendig file size to client", e);
			}
	}

	@Override
	public void processDataOnClient(DataInputStream fromServer,
			DataOutputStream toServer) {
		try {
			this.toServer = toServer;
			int iKind = Integer.parseInt(fromServer.readUTF());
			CLIENT_CONTENT_KIND kind = CLIENT_CONTENT_KIND.TEXT;
			try {
				kind = CLIENT_CONTENT_KIND.values()[iKind];
			} catch (Exception e) {
			}
			//LOGGER.debug(kind.toString() + "  " + iKind);
			if (kind == CLIENT_CONTENT_KIND.FILE) {
				sendFile(fromServer.readUTF());
			} else {
				isPassword = kind == CLIENT_CONTENT_KIND.PASSWORD;
				Thread thread = new Thread(new WaitTextThread());
				thread.setDaemon(true);
				thread.start();
			}
		} catch (IOException e) {
		}
	}

	@Override
	public void sendToClient(DataOutputStream toClient) {
		try {
			sendToClient(toClient, CLIENT_CONTENT_KIND.TEXT, "");
		} catch (IOException e) {
		}
	}

	public void sendToClient(DataOutputStream toClient,
			CLIENT_CONTENT_KIND content_KIND, String filepath)
			throws IOException {
		super.sendToClient(toClient);

		toClient.writeUTF(String.valueOf(content_KIND.ordinal()));
		if (content_KIND == CLIENT_CONTENT_KIND.FILE) {
			toClient.writeUTF(filepath);
		}
	}
}