package commands;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class LoadDataCommand extends AbstractCommand {

	@Override
	public void sendRequestToServer(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.LOADDATA.name());
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
			this.getServer().getHospital().loadData("HospitalBD.zip");
		} catch (IOException e1) {
			System.out.println("Load Errror. Continue with an empty database.");
			this.getServer().getHospital().removeAllData();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			this.getServer().setDataLoading(false);
		}
	}

	@Override
	public void sendResponseToClient(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.LOADDATA.name());
		out.writeUTF("Data is loaded.");
	}

	@Override
	public void clearClientRequest(DataInputStream in) throws IOException {
		
	}

	public LoadDataCommand() {
		super.setDeclaration("Download the data from the database into the system");
	}

}
