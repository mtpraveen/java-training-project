package commands;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class AddDataCommand extends AbstractCommand {
	private String filePath;

	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath
	 *            the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public void sendRequestToServer(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.ADDDATA.name());
		out.writeUTF(filePath);
	}

	@Override
	public void processResponseFromServer(DataInputStream in)
			throws IOException {
		System.out.println(in.readUTF());
	}

	@Override
	public void processClientRequest(DataInputStream in) throws IOException {
		filePath = in.readUTF();
		try {
			this.getServer().setDataLoading(true);
			this.getServer().waitProcessTermination();
			this.getServer().getHospital().addCsvData(filePath);
		} catch (IOException e1) {
			e1.printStackTrace();
			System.out.println("CSV load Errror.");
		} finally {
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
		in.readUTF();
	}

	public AddDataCommand() {
		super.setDeclaration("Adding data from Csv file");
	}

}
