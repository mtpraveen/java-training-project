package motor.depot.clientserver.server.scenario.admin;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import motor.depot.clientserver.server.ClientThread;
import motor.depot.clientserver.server.Server;
import motor.depot.clientserver.server.scenario.AbstractScenario;

public class ShutdownServerScenario extends AbstractScenario{

	public ShutdownServerScenario(ClientThread thread) {
		super(thread);
	}

	@Override
	public void run() throws IOException {
		Server.getInstance().closeAll(thread.getString("ShutdownServerScenario.Server_will_be_closed_in_1_sec"),1); //$NON-NLS-1$
	}
}
