package motor.depot.clientserver.server.scenario;

import java.io.IOException;

import motor.depot.clientserver.server.ClientThread;

public class SimpleEchoScenario extends AbstractScenario {

	public SimpleEchoScenario(ClientThread thread) {
		super(thread);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() throws IOException {
		str(thread.getString("SimpleEchoScenario.Enter_String")); //$NON-NLS-1$
		String s = readString();
		str(thread.getString("SimpleEchoScenario.You_wrote") + s); //$NON-NLS-1$
	}

}
