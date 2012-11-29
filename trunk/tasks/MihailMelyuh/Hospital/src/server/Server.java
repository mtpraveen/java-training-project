package server;

import hospital.AccessRights;
import hospital.Hospital;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import commands.AbstractCommand;
import commands.Commands;
import commands.ShutdownCommand;
import server.ClientThreadAtServer;
import server.Server;

public class Server {
	private static final Logger LOGGER = Logger.getLogger(Server.class);
	private int port;
	private Hospital hospital;
	private boolean isDataLoading;
	private ServerSocket serverSocket;
	private ArrayList<String> clientCommands;
	private ArrayList<String> clients;
	private BlockingQueue<ClientThreadAtServer> threads = new LinkedBlockingQueue<ClientThreadAtServer>();

	public Server(int port) throws Exception {
		PropertyConfigurator.configure("log4j.properties");
		this.port = port;
		this.serverSocket = new ServerSocket(port);
		try {
			hospital = new Hospital();
		} catch (ClassNotFoundException | IOException e) {
			LOGGER.error("Password file is destroyed", e);
			throw new Exception("Password file is destroyed");
		}
		clients = new ArrayList<String>();
		clientCommands = new ArrayList<String>();
		clientCommands.add(Commands.MESSAGE.name());
		clientCommands.add(Commands.CSVREPORT.name());
		clientCommands.add(Commands.LOGIN.name());
		clientCommands.add(Commands.EXIT.name());
		clientCommands.add(Commands.REPORT.name());
		clientCommands.add(Commands.APPOINTASSIGNMENT.name());
		clientCommands.add(Commands.APPOINTDOCTOR.name());
		clientCommands.add(Commands.HELP.name());
		clientCommands.add(Commands.REMOVEPATIENT.name());
	}

	public void userIsLogin(String login) {
		System.out.println("Connected " + login);
	}

	public void run() throws Exception {
		try {
			while (!serverSocket.isClosed()) {
				Socket client = serverSocket.accept();
				ClientThreadAtServer clientThreadAtServer = new ClientThreadAtServer(
						"Unknown", AccessRights.Unknown, client, this);
				threads.offer(clientThreadAtServer);
				clientThreadAtServer.start();
			}
		} catch (IOException e) {
			LOGGER.error(e);
		} finally {
			if (!serverSocket.isClosed()) {
				try {
					serverSocket.close();
				} catch (IOException ignored) {
					LOGGER.error("Server not responding", ignored);
					throw new Exception("Server not responding");
				}
			}
		}
	}

	public synchronized void shutdown(ShutdownCommand shutdown) {
		try {
			int delay = shutdown.getDelay();
			System.out.println("Server is about to shutdown (in " + delay
					+ " secs)");
			// close all clients
			for (ClientThreadAtServer t : threads) {
				ShutdownCommand newShutdown = new ShutdownCommand();
				newShutdown.setClientThread(t);
				newShutdown.setMessage(shutdown.getMessage());
				t.addCommand(newShutdown);
			}
			threads.clear();
			TimeUnit.SECONDS.sleep(delay);
			serverSocket.close();
			System.out.println("Exited");
			System.exit(0);
		} catch (IOException e) {
			LOGGER.error("Can't close server socket.", e);
		} catch (InterruptedException e) {
			LOGGER.error("Server doesn't wait.", e);
		}

	}

	public synchronized void waitProcessTermination() {
		boolean allTerminated = false;
		while (!allTerminated) {
			allTerminated = false;
			for (ClientThreadAtServer thread : threads) {
				if (thread.isProcessed()) {
					allTerminated = true;
					break;
				}
			}
		}
	}

	public synchronized void removeClient(ClientThreadAtServer t) {
		System.out.println("Client " + t.getClientName() + " exited");
		t.close();
		threads.remove(t);
	}

	public synchronized void sendMessageToClients(AbstractCommand cmd,
			ClientThreadAtServer clientThreadAtServer) {
		for (ClientThreadAtServer t : threads) {
			if (!t.getClientName().equals(clientThreadAtServer.getClientName())) {
				t.addCommand(cmd);
			}
		}

	}

	public synchronized void sendMessageToClient(AbstractCommand cmd,
			ClientThreadAtServer clientThreadAtServer) {
		for (ClientThreadAtServer t : threads) {
			if (t.getClientName().equals(clientThreadAtServer.getClientName())) {
				t.addCommand(cmd);
			}
		}

	}

	public synchronized void sendMessageToClients(AbstractCommand cmd) {
		for (ClientThreadAtServer t : threads) {
			t.addCommand(cmd);
		}

	}

	/**
	 * @return the hospital
	 */
	public synchronized Hospital getHospital() {
		return hospital;
	}

	/**
	 * @param hospital
	 *            the hospital to set
	 */
	public synchronized void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	/**
	 * @return the serverSocket
	 */
	public synchronized ServerSocket getServerSocket() {
		return serverSocket;
	}

	/**
	 * @param serverSocket
	 *            the serverSocket to set
	 */
	public synchronized void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

	/**
	 * @return the threads
	 */
	public synchronized BlockingQueue<ClientThreadAtServer> getThreads() {
		return threads;
	}

	/**
	 * @param threads
	 *            the threads to set
	 */
	public synchronized void setThreads(
			BlockingQueue<ClientThreadAtServer> threads) {
		this.threads = threads;
	}

	public static void main(String[] args) throws Exception {
		Server server;
		try {
			server = new Server(1111);
			server.run();
		} catch (IOException e) {
			LOGGER.error("Can't start server", e);
			throw new Exception("Can't start server");
		}
	}

	/**
	 * @return the clients
	 */
	public synchronized ArrayList<String> getClients() {
		return clients;
	}

	/**
	 * @return the clientCommands
	 */
	public synchronized ArrayList<String> getClientCommands() {
		return clientCommands;
	}

	/**
	 * @return the isDataLoading
	 */
	public synchronized boolean isDataLoading() {
		return isDataLoading;
	}

	/**
	 * @param isDataLoading
	 *            the isDataLoading to set
	 */
	public synchronized void setDataLoading(boolean isDataLoading) {
		this.isDataLoading = isDataLoading;
	}

	/**
	 * @return the logger
	 */
	public static Logger getLogger() {
		return LOGGER;
	}

}
