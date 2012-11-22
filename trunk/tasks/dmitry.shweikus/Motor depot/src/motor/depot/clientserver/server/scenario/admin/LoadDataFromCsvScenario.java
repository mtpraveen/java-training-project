package motor.depot.clientserver.server.scenario.admin;

import motor.depot.clientserver.server.ClientThread;
import motor.depot.clientserver.server.scenario.AbstractScenario;

public class LoadDataFromCsvScenario extends AbstractScenario {

	public LoadDataFromCsvScenario(ClientThread thread) {
		super(thread);
	}

	@Override
	public void run() {
		String tempPath = readFile("c:\\hello.txt");
		str("get -" + tempPath + "-");
	}

}
