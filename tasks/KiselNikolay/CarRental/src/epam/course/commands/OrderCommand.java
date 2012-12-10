package epam.course.commands;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import epam.course.server.ClientThread;

public class OrderCommand extends AbstractCommand {

	private String carNumber;
	private String beginDate;
	private String endDate;

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Override
	public void sendRequestToServer(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.ORDER.name());
		out.writeUTF(carNumber);
		out.writeUTF(beginDate);
		out.writeUTF(endDate);

	}

	@Override
	public void processResponseFromServer(DataInputStream in)
			throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void processClientRequest(DataInputStream in) throws IOException {
		carNumber=in.readUTF();
		beginDate=in.readUTF();
		endDate=in.readUTF();
		ClientThread clientThread = getClientThread();
		getServer().registrNewOrder(this, clientThread);
	}

	@Override
	public void sendResponseToClient(DataOutputStream out) throws IOException {
		// TODO Auto-generated method stub

	}

}
