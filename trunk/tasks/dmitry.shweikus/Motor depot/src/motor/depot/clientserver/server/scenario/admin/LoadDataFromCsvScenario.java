package motor.depot.clientserver.server.scenario.admin;

import java.io.File;

import motor.depot.clientserver.server.ClientThread;
import motor.depot.clientserver.server.scenario.AbstractScenario;
import motor.depot.model.MotorDepot;
import motor.depot.storages.csv.ZippedCsvLoader;

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
		ZippedCsvLoader csvLoader = new ZippedCsvLoader();
		int recordsAdded = csvLoader.appendFromZippedCsv(tmpPath, MotorDepot.getInstance());
		str("records added : " +  recordsAdded);
		waitForInput();
		File tmp = new File(tmpPath);
		tmp.delete();
	}

}
