package commands;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class AppointDoctorCommand extends AbstractCommand{
	private int patientID;
	private int doctorID;
	private String message;
	
	
	public AppointDoctorCommand() {
		super.setDeclaration("Appoint doctor to patient");
	}
	
	@Override
	public void sendRequestToServer(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.APPOINTDOCTOR.name());
		out.writeInt(patientID);
		out.writeInt(doctorID);
	}

	@Override
	public void processResponseFromServer(DataInputStream in)
			throws IOException {
		System.out.println(in.readUTF());
	}

	@Override
	public void processClientRequest(DataInputStream in) throws IOException {
		patientID=in.readInt();
		doctorID=in.readInt();
		this.getServer().setDataLoading(true);
		this.getServer().waitProcessTermination();
		message=this.getServer().getHospital().appointDoctor(patientID, doctorID);
		this.getServer().setDataLoading(false);
	}

	@Override
	public void sendResponseToClient(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.APPOINTDOCTOR.name());
		out.writeUTF(message);
	}

	@Override
	public void clearClientRequest(DataInputStream in) throws IOException {
		in.readInt();
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

	/**
	 * @return the doctorID
	 */
	public int getDoctorID() {
		return doctorID;
	}

	/**
	 * @param doctorID the doctorID to set
	 */
	public void setDoctorID(int doctorID) {
		this.doctorID = doctorID;
	}

}
