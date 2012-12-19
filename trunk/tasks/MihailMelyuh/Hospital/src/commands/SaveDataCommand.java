package commands;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class SaveDataCommand extends AbstractCommand {

	@Override
	public void sendRequestToServer(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.SAVEDATA.name());
	}

	@Override
	public void processResponseFromServer(DataInputStream in)
			throws IOException {
		System.out.println(in.readUTF());
	}

	@Override
	public void processClientRequest(DataInputStream in) throws IOException {
		try {
			this.getServer().setDataLoading(true);
			this.getServer().waitProcessTermination();
			this.getServer().getHospital().saveData("HospitalBD.zip");
		} catch (IOException e1) {
			e1.printStackTrace();
			System.out.println("Save Errror.");
		}
		finally{
			this.getServer().setDataLoading(false);
		}
	}

	@Override
	public void sendResponseToClient(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.SAVEDATA.name());
		out.writeUTF("Data is saved.");
	}

	@Override
	public void clearClientRequest(DataInputStream in) throws IOException {
		
	}

	public SaveDataCommand() {
		super.setDeclaration("Save current data in the database");
	}

	@Override
	public void setParametrs(DataOutputStream serverOutputStream,BufferedReader consoleInputStream)  throws IOException {
		
	}

}
