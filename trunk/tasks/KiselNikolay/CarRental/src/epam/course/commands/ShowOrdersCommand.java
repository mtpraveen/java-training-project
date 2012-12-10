package epam.course.commands;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import epam.course.csv.CsvWriting;
import epam.course.domain.Order;
import epam.course.server.ClientThread;
import epam.course.xml.JdomWriting;

public class ShowOrdersCommand extends AbstractCommand {

	private String date;
	private String type;
	private String fileName;
	private int count;

	private List<Order> ordersToShow;

	public List<Order> getOrdersToShow() {
		return ordersToShow;
	}

	public void setOrdersToShow(List<Order> ordersToShow) {
		this.ordersToShow = ordersToShow;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void printOrders(List<Order> order) {
		System.out.println();
		for (Order l : order) {
			System.out.println(l.getClient());
		}
	}
	
	public void createXml(List<Order> orders, String file) {
		JdomWriting.writeOrdersToXml(orders, file);
	}
	
	public void createCsv(List<Order> orders, String file) {
		CsvWriting.writeOrdersToCsv(orders, file);
	}

	@Override
	public void sendRequestToServer(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.SHOWORDERS.name());
		out.writeUTF(date);
		out.writeUTF(type);
		out.writeUTF(fileName);
	}

	@Override
	public void processResponseFromServer(DataInputStream in)
			throws IOException {
		type = in.readUTF();
		fileName = in.readUTF();
		count = in.readInt();
		Order ob = null;
		ObjectInputStream istream = null;
		ordersToShow = new ArrayList<Order>();
		try {
			istream = new ObjectInputStream(in);

			for (int i = 0; i < count; i++) {
				ob = (Order) istream.readObject();
				// System.out.println("get "+ob);
				ordersToShow.add(ob);

			}

		} catch (EOFException e) {

		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
		if (type.equalsIgnoreCase("xml")) {
			createXml(ordersToShow, fileName);
		} else if (type.equalsIgnoreCase("csv")) {
			createCsv(ordersToShow, fileName);
		} else {
			printOrders(ordersToShow);
		}
		

	}

	@Override
	public void processClientRequest(DataInputStream in) throws IOException {
		date = in.readUTF();
		type = in.readUTF();
		fileName = in.readUTF();
		ClientThread clientThread = getClientThread();
		getServer().getOrders(this, clientThread);
	}

	@Override
	public void sendResponseToClient(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.SHOWORDERS.name());
		out.writeUTF(type);
		out.writeUTF(fileName);
		out.writeInt(count);
		ObjectOutputStream ostream = new ObjectOutputStream(out);
		for (Order ob : ordersToShow) {
			ostream.writeObject(ob);
		}
		ostream.flush();
		// ostream.close();

	}

}
