package motor.depot.clientserver.server.scenario;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import motor.depot.clientserver.server.ClientThread;

public class SimpleEchoScenario extends AbstractScenario {

	public SimpleEchoScenario(ClientThread thread) {
		super(thread);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		str("Enter string");
		String s = readString();
		str("You wrote : " + s);
	}

}
