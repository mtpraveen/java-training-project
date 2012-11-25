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
		str(thread.getString("LoadDataFromCsvScenario.Enter_valid_filepath")); //$NON-NLS-1$
		String path = readString();
		String tmpPath = readFile(path);
		if(tmpPath.equals("")) //$NON-NLS-1$
		{
			str(thread.getString("LoadDataFromCsvScenario.File_not_found")); //$NON-NLS-1$
			waitForInput();
			return;
		}
		ZippedCsvLoader csvLoader = new ZippedCsvLoader();
		int recordsAdded = csvLoader.appendFromZippedCsv(tmpPath, MotorDepot.getInstance());
		str(thread.getString("LoadDataFromCsvScenario.records_added") +  recordsAdded); //$NON-NLS-1$
		waitForInput();
		File tmp = new File(tmpPath);
		tmp.delete();
	}

}
