package motor.depot.clientserver.server.scenario.admin;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import motor.depot.clientserver.server.ClientThread;
import motor.depot.clientserver.server.Server;
import motor.depot.clientserver.server.scenario.AbstractScenario;

public class ShutdownServerScenario extends AbstractScenario{

	public ShutdownServerScenario(ClientThread thread) {
		super(thread);
	}

	@Override
	public void run() {
		Server.getInstance().closeAll("Server will be closed in 1 sec",1);
	}
}
