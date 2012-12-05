package com.epam.library.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.epam.library.commands.AbstractCommand;
import com.epam.library.commands.ShutDownCommand;
import com.epam.library.models.Book;
import com.epam.library.models.BookCatalogue;
import com.epam.library.models.InterimReportItem;
import com.epam.library.models.Reader;
import com.epam.library.models.ReportItem;

public class Server {
	private BookCatalogue catalogue = new BookCatalogue();
	private ConcurrentHashMap<String, String> admins = new ConcurrentHashMap<String, String>(); // login and password
	private ConcurrentHashMap<String, Reader> readersLoginsAndInfo = new ConcurrentHashMap<String, Reader>(); // readers logins and information																			
	private ConcurrentHashMap<String, String> mapOfReaders = new ConcurrentHashMap<String, String>(); // login and password								
	private ConcurrentHashMap<Reader, HashMap<Book, Integer>> readers = new ConcurrentHashMap<Reader, HashMap<Book, Integer>>(); // reader, book and amount of books
	private ConcurrentHashMap<String, Reader> blackList = new ConcurrentHashMap<String, Reader>();
	private ConcurrentHashMap<String, Date> timeOfEntry = new ConcurrentHashMap<String, Date>();
	private ServerSocket serverSocket;
	private int clients;
	private int port;
	private BlockingQueue<ClientThread> threads = new LinkedBlockingQueue<ClientThread>();
	private ConcurrentHashMap<InterimReportItem,Date> interimReport = new ConcurrentHashMap<InterimReportItem,Date>();//interim report
	private ConcurrentHashMap<ReportItem,Date> report = new ConcurrentHashMap<ReportItem,Date>();

	public Server(int port) {
		this.port = port;
		try {
			this.serverSocket = new ServerSocket(port);
			loadAdminsFromZip("G:\\pack.zip");
			loadReadersFromZip("G:\\pack.zip");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void loadReadersFromZip(String path) {
		try {
			ZipInputStream zin = new ZipInputStream(new FileInputStream(path));
			ZipEntry zipEntry = null;
			while ((zipEntry = zin.getNextEntry()) != null) {
				if (zipEntry.getName().equalsIgnoreCase("readers.csv")) {
					Scanner scan = new Scanner(zin);
					String row = "";
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					Date dateOfBirth = new Date();
					while (scan.hasNextLine()) {
						row = scan.nextLine();
						String[] values = row.split(",");
						mapOfReaders.put(values[0], values[1]);
						try {
							dateOfBirth = df.parse(values[3]);
						} catch (ParseException e) {
							e.printStackTrace();
						}
						readersLoginsAndInfo.put(values[0], new Reader(
								values[2], dateOfBirth, values[4]));
						HashMap<Book, Integer> tempMap = new HashMap<Book, Integer>();
						if (values.length > 5) {
							int i = 5;
							while (i < values.length) {
								tempMap.put(new Book(values[i], values[i + 1],
										Integer.parseInt(values[i + 2])),
										Integer.parseInt(values[i + 3]));
								i += 4;
							}
						}
						readers.put(new Reader(values[2], dateOfBirth,
								values[4]), tempMap);
					}
				}
			}

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	public synchronized String getStatisticAboutCurrentSession(){
		Date currentDate = new Date();
		String result="";
		for(ClientThread t:threads){
			result+=t.getName();
			result+="\t"+timeOfEntry.get(t.getName());
			result+="\t"+((currentDate.getTime() - timeOfEntry.get(t.getName()).getTime())/60000)+" min\n";
		}
		return result;
	}
	

	public synchronized boolean isReader(String login, String password) {
		if (mapOfReaders.containsKey(login)
				&& password.equals(mapOfReaders.get(login))) {
			return true;
		} else
			return false;
	}

	public synchronized boolean isReader(String login) {
		if (mapOfReaders.containsKey(login)) {
			return true;
		} else
			return false;
	}
	
	public synchronized boolean isInBlackList(String login){
		if(blackList.containsKey(login))
			return true;
		else return false;
	}
	
	public synchronized String getReport(){
		String result="";
		Set<Entry<ReportItem,Date>> reportElemets = report.entrySet();
		for(Entry<ReportItem, Date> t:reportElemets){
			result+=t.toString();
			result+="\n";
		}
		int index = result.indexOf('=');
		result = new StringBuilder(result).deleteCharAt(index).toString();
		return result;
	}
	
	public synchronized boolean returnTheBook(Book book, String login) {
		Reader tmpReader = readersLoginsAndInfo.get(login);
		HashMap<Book, Integer> tmpMap = readers.get(tmpReader);
		Integer tmpAmount = tmpMap.get(book);
		if (tmpAmount > 0) {
			tmpAmount--;
			tmpMap.put(book, tmpAmount);
			readers.put(tmpReader, tmpMap);
			Date tmpDate = interimReport.get(new InterimReportItem(login, book));
			report.put(new ReportItem(login, book, tmpDate), new Date());
			return true;
		} else
			return false;
	}
	
	public synchronized boolean giveTheBook(Book book,String login){
		if(catalogue.isBookAvailable(book)){
			Reader tmp = readersLoginsAndInfo.get(login);
			catalogue.getBook(book);
			//////////////////////
			HashMap<Book,Integer> tmpMap = new HashMap<Book,Integer>();
			tmpMap = readers.get(tmp);
			//////////////////////
			if(tmpMap.containsKey(book)){
//				HashMap<Book,Integer> tmpMap = readers.get(tmp);
				Integer tmpAmount = tmpMap.get(book);
				tmpAmount++;
				tmpMap.put(book, tmpAmount);
				readers.put(tmp, tmpMap);				
			}
			else{
				readers.get(tmp).put(book, 1);
			}
			interimReport.put(new InterimReportItem(login,book), new Date());			
			return true;
		}
		else return false;
	}

	public synchronized boolean addToBlackList(String login) {
		if (mapOfReaders.containsKey(login)) {
			blackList.put(login, readersLoginsAndInfo.get(login));
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

	public boolean isAdmin(String login) {
		if (admins.containsKey(login)) {
			return true;
		} else
			return false;
	}

	public synchronized void sendMessageToClients(AbstractCommand cmd) {
		for (ClientThread client : threads) {
			client.addCommand(cmd);
		}
	}

	// public synchronized void addReader(Reader reader) {
	// readers.put(reader, new HashMap<Book, Integer>());
	// }

	// public synchronized void deleteReader(Reader reader) {
	// readers.remove(reader);
	// }

	public synchronized void removeClient(ClientThread client) {
		client.close();
		threads.remove(client);
		clients--;
	}

	public synchronized void shutdown(ShutDownCommand shutdown) {
		try {
			int delay = shutdown.getDelay();
			System.out.println("Server is about to shutdown (in " + delay
					+ " secs)");
			// close clients
			for (ClientThread t : threads) {
				ShutDownCommand newShutDown = new ShutDownCommand();
				newShutDown.setClientThread(t);
				newShutDown.setMessage(shutdown.getMessage());
				t.addCommand(newShutDown);
			}
			threads.clear();
			TimeUnit.SECONDS.sleep(delay);
			serverSocket.close();
			System.out.println("Exited");
			System.exit(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public synchronized boolean removeReader(String login, Reader reader) {
		if (mapOfReaders.containsKey(login)
				&& reader.equals(readersLoginsAndInfo.get(login))) {
			mapOfReaders.remove(login);
			readersLoginsAndInfo.remove(login);
			readers.remove(reader);
			return true;
		} else
			return false;
	}

	public synchronized boolean changeUsersPassword(String login,
			String password) {
		if (mapOfReaders.containsKey(login)) {
			mapOfReaders.replace(login, password);
			return true;

		} else if (admins.containsKey(login)) {
			admins.replace(login, password);
			return true;
		} else
			return false;
	}

	public synchronized void loadCatalogueFromFile(String path) {
		
		File file = new File(path);
		if (file.exists()) {
			catalogue.loadFromCsvFileInZip(file);
		}
	}

	private void loadAdminsFromZip(String path) {
		try {
			ZipInputStream zin = new ZipInputStream(new FileInputStream(path));
			ZipEntry zipEntry = null;
			while ((zipEntry = zin.getNextEntry()) != null) {
				if (zipEntry.getName().equalsIgnoreCase("admins.csv")){
					Scanner scan = new Scanner(zin);
					String row = "";
					while (scan.hasNextLine()) {
						row = scan.nextLine();
						String[] values = row.split(",");
						admins.put(values[0], values[1]);
					}
					break;
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized boolean addUser(String type, Reader reader,
			String login, String password) {
		if (type.equalsIgnoreCase("admin")) {
			admins.put(login, password);
			return true;
		} else if (type.equalsIgnoreCase("reader")) {
			mapOfReaders.put(login, password);
			readersLoginsAndInfo.put(login, reader);
			readers.put(reader, new HashMap<Book, Integer>());
			return true;
		} else
			return false;
	}

	public void setTimeOfEntry(String login, Date date) {
		timeOfEntry.put(login, date);
	}
	
	public synchronized void saveReportInFile(String path) throws IOException {
		File file = new File(path);
		if(!file.exists()){
			file.createNewFile();
			FileWriter fw = new FileWriter(file);
			fw.write(getReport());
			fw.flush();
			fw.close();
		}
	}
	
	public static void main(String[] args) {
		new Server(1234).run();
	}

	



}
