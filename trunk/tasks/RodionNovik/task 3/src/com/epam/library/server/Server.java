package com.epam.library.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import com.epam.library.commands.AbstractCommand;
import com.epam.library.commands.LoginCommand;
import com.epam.library.models.Book;
import com.epam.library.models.BookCatalogue;
import com.epam.library.models.Reader;

public class Server {
	private BookCatalogue catalogue = new BookCatalogue();
	private ConcurrentHashMap<String, String> admins = new ConcurrentHashMap<String, String>();
	private ConcurrentHashMap<String, String> mapOfReaders = new ConcurrentHashMap<String, String>(); // login and password
	private ConcurrentHashMap<Reader, HashMap<Book, Integer>> readers = new ConcurrentHashMap<Reader, HashMap<Book, Integer>>(); // reader, book and amount of books
	private ServerSocket serverSocket;
	private int clients;
	private int port;
	private BlockingQueue<ClientThread> threads = new LinkedBlockingQueue<ClientThread>();

	public Server(int port) {
		this.port = port;
		try {
			this.serverSocket = new ServerSocket(port);
			loadAdmins();
			loadReaders();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void loadReaders() throws FileNotFoundException {
		File file = new File("G:\\Readers.csv");
		Scanner scan = new Scanner(file);
		String row = "";
		while (scan.hasNextLine()) {
			row = scan.nextLine();
			String[] values = row.split(",");
			mapOfReaders.put(values[0], values[1]);
		}
	}

	public void run() {
		while (true) {
			try {
				Socket clientSocket = serverSocket.accept();
				System.out.println("Client " + clients + "connected");
				clients++;
				ClientThread clientThread = new ClientThread(
						"Client" + clients, clientSocket, this);
				threads.offer(clientThread);
				clientThread.start();

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	public boolean isReader(String login, String password) {
		if (mapOfReaders.containsKey(login)
				&& password.equals(mapOfReaders.get(login))) {
			return true;
		} else
			return false;
	}

	public boolean isAdmin(String login, String password) {
		if (admins.containsKey(login) && password.equals(admins.get(login))) {
			return true;
		} else
			return false;
	}

	public synchronized void sendMessageToClients(AbstractCommand cmd) {
		for (ClientThread client : threads) {
			client.addCommand(cmd);
		}
	}

	public synchronized void addReader(Reader reader) {
		readers.put(reader, new HashMap<Book, Integer>());
	}

	public synchronized void deleteReader(Reader reader) {
		readers.remove(reader);
	}

	public boolean changeUsersPassword(String login, String password) {
		if (mapOfReaders.containsKey(login)) {
			mapOfReaders.replace(login, password);
			return true;

		} else if (admins.containsKey(login)) {
			admins.replace(login, password);
			return true;
		}
		else return false;
	}

	public synchronized void loadCatalogueFromFile(String path) {
		File file = new File(path);
		if (file.exists()) {
			catalogue.loadFromCsvFile(file);
		}
	}

	private void loadAdmins() {
		try {
			File file = new File("G:\\Admins.csv");
			if (file.exists()) {
				Scanner scan = new Scanner(file);
				String row = "";
				while (scan.hasNextLine()) {
					row = scan.nextLine();
					String[] values = row.split(",");
					admins.put(values[0], values[1]);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Server(1234).run();
	}

}
