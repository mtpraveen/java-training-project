package com.epam.library.commands;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.epam.library.client.Client;
import com.epam.library.models.Book;

public class ReturnTheBookCommand extends AbstractCommand {
	private String name;
	private String author;
	private int yearOfProduction;
	
	boolean returned;
	
	public void setParams(String name, String author, int yearOfProduction){
		this.name=name;
		this.author = author;
		this.yearOfProduction = yearOfProduction;
	}
	
	@Override
	public void sendRequestToServer(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.RETURNTHEBOOK.name());
		out.writeUTF(name);
		out.writeUTF(author);
		out.writeInt(yearOfProduction);
	}

	@Override
	public void processClientRequest(DataInputStream in) throws IOException {
		this.name = in.readUTF();
		this.author = in.readUTF();
		this.yearOfProduction = in.readInt();
		returned = getServer().returnTheBook(new Book(name, author,yearOfProduction),getClientThread().getName());

	}

	@Override
	public void processResponseFromServer(DataInputStream in)
			throws IOException {
		String answer = Client.getRb().getString(in.readUTF());
		System.out.println(answer);

	}

	@Override
	public void sendResponseToClient(DataOutputStream out) throws IOException {
		out.writeUTF(Commands.RETURNTHEBOOK.name());
		if(returned){
			out.writeUTF("ReturnedSuccessfully");
		}
		else out.writeUTF("ErrorYouHaven'tThisBook");

	}
}
