package commands;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class RemovePatientCommand extends AbstractCommand {
	private int patientID;
	private String message;
	
	public RemovePatientCommand(){
		super.setDeclaration("Remove patient by ID");
	}
	
	@Override
	public void sendRequestToServer(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.REMOVEPATIENT.name());
		out.writeInt(patientID);
	}

	@Override
	public void processResponseFromServer(DataInputStream in)
			throws IOException {
		System.out.println(in.readUTF());
	}

	@Override
	public void processClientRequest(DataInputStream in) throws IOException {
		patientID=in.readInt();
		this.getServer().setDataLoading(true);
		this.getServer().waitProcessTermination();
		message=this.getServer().getHospital().removePatient(patientID);
		this.getServer().setDataLoading(false);
	}

	@Override
	public void sendResponseToClient(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.REMOVEPATIENT.name());
		out.writeUTF(message);
	}

	@Override
	public void clearClientRequest(DataInputStream in) throws IOException {
		in.readInt();
	}

	/**
	 * @return the patientID
	 */
	public int getPatientID() {
		return patientID;
	}

	/**
	 * @param patientID the patientID to set
	 */
	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}

}
