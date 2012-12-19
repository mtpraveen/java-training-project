package commands;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Client.Client;

import position.Person;

import zip.ZipWriter;

public class CsvReportCommand extends AbstractCommand {
private String filePath="";
private ArrayList<ArrayList<? extends Person>> persons;
	
	/**
 * @return the filePath
 */
public String getFilePath() {
	return filePath;
}

/**
 * @param filePath the filePath to set
 */
public void setFilePath(String filePath) {
	this.filePath = filePath;
}

	@Override
	public void sendRequestToServer(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.CSVREPORT.name());
		out.writeUTF(filePath);
	}

	@Override
	public void processResponseFromServer(DataInputStream in)
			throws IOException {
		filePath=in.readUTF();
		ObjectInputStream oin = new ObjectInputStream(in);
		try {
			persons = (ArrayList<ArrayList<? extends Person>>) oin.readObject();
		} catch (ClassNotFoundException e) {
			Client.getLogger().error("Read wrong format.",e);
			throw new IOException("Read wrong format.");
		}
		ZipWriter writerDoc = new ZipWriter(filePath);
		writerDoc.writeData(persons);
		writerDoc.close();
		System.out.println("Report is saved!");
	}

	@Override
	public void processClientRequest(DataInputStream in) throws IOException {
		filePath=in.readUTF();
		persons=new ArrayList<ArrayList<? extends Person>>();
		persons.add(this.getServer().getHospital().getPatients());
		persons.add(this.getServer().getHospital().getDoctors());
		persons.add(this.getServer().getHospital().getNurses());
	}

	@Override
	public void sendResponseToClient(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.CSVREPORT.name());
		out.writeUTF(filePath);
		ObjectOutputStream oos = new ObjectOutputStream(out);
		oos.writeObject(persons);
		oos.flush();
	}

	@Override
	public void clearClientRequest(DataInputStream in) throws IOException {
		in.readUTF();
	}

	public CsvReportCommand() {
		super.setDeclaration("Save the report to format the disk Csv");
	}

	@Override
	public void setParametrs(DataOutputStream serverOutputStream,BufferedReader consoleInputStream) throws IOException {
		System.out.println("Input filePath:");
		this.setFilePath(consoleInputStream
						.readLine());
	}

}
