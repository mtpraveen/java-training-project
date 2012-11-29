package commands;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import position.Positions;

public class AppointAssignmentCommand extends AbstractCommand{
	private int patientID;
	private String text;
	private String position;
	private int personID;
	private String message;
	
	
	public AppointAssignmentCommand() {
		super.setDeclaration("Appoint assignment to patient");
	}


	@Override
	public void sendRequestToServer(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.APPOINTASSIGNMENT.name());
		out.writeInt(patientID);
		out.writeUTF(text);
		out.writeUTF(position);
		out.writeInt(personID);
	}


	@Override
	public void processResponseFromServer(DataInputStream in)
			throws IOException {
		System.out.println(in.readUTF());
	}


	@Override
	public void processClientRequest(DataInputStream in) throws IOException {
		patientID=in.readInt();
		text=in.readUTF();
		position=in.readUTF();
		personID=in.readInt();
		this.getServer().setDataLoading(true);
		this.getServer().waitProcessTermination();
		message=this.getServer().getHospital().appointAssignment(patientID, text, Positions.valueOf(position), personID);
		this.getServer().setDataLoading(false);
	}


	@Override
	public void sendResponseToClient(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.APPOINTASSIGNMENT.name());
		out.writeUTF(message);
	}


	@Override
	public void clearClientRequest(DataInputStream in) throws IOException {
		in.readInt();
		in.readUTF();
		in.readUTF();
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
	 * @return the text
	 */
	public String getText() {
		return text;
	}


	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}


	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}


	/**
	 * @param position the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}


	/**
	 * @return the personID
	 */
	public int getPersonID() {
		return personID;
	}


	/**
	 * @param personID the personID to set
	 */
	public void setPersonID(int personID) {
		this.personID = personID;
	}
	
}
