package motor.depot.clientserver.server.scenario.admin;

import motor.depot.clientserver.server.ClientThread;
import motor.depot.clientserver.server.scenario.AbstractScenario;

public class LoadDataFromCsvScenario extends AbstractScenario {

	public LoadDataFromCsvScenario(ClientThread thread) {
		super(thread);
	}

	@Override
	public void run() {
		str("Enter valid filepath:");
		String path = readString();
		String tmpPath = readFile(path);
		if(tmpPath.equals(""))
		{
			str("File not found");
			waitForInput();
			return;
		}
		str("File received : " + tmpPath);
	}

}
