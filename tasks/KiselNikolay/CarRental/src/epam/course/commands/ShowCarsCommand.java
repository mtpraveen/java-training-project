package epam.course.commands;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import epam.course.server.ClientThread;

public class ShowCarsCommand extends AbstractCommand {

	private int id;
	private String brand;
	private String model;
	private int year;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public void sendRequestToServer(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.SHOWCARS.name());
	}

	@Override
	public void processResponseFromServer(DataInputStream in)
			throws IOException {
		//System.out.println("Cars: ");
		System.out.println(in.readUTF() + " " + in.readUTF() + " "
				+ in.readUTF() + " " + in.readUTF());

	}

	@Override
	public void processClientRequest(DataInputStream in) throws IOException {
		ClientThread clientThread = getClientThread();
		getServer().showCars(this, clientThread);

	}

	@Override
	public void sendResponseToClient(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.SHOWCARS.name());
		out.writeUTF(Integer.toString(id));
		out.writeUTF(brand);
		out.writeUTF(model);
		out.writeUTF(Integer.toString(year));
	}

}
