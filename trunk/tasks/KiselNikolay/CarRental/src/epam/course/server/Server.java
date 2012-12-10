package epam.course.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import epam.course.commands.AbstractCommand;
import epam.course.commands.ChangeLanguageCommand;
import epam.course.commands.Commands;
import epam.course.commands.LoginCommand;
import epam.course.commands.OrderCommand;
import epam.course.commands.RegistrationCommand;
import epam.course.commands.SendMessageCommand;
import epam.course.commands.ShowCarsCommand;
import epam.course.commands.ShowOrdersCommand;
import epam.course.commands.ShutdownCommand;
import epam.course.csv.CsvWriting;
import epam.course.domain.Car;
import epam.course.domain.Customer;
import epam.course.domain.Order;
import epam.course.fao.Packing;
import epam.course.fao.Serialization;
import epam.course.xml.JdomWriting;

public class Server {

	private final String carsFile = "Car.txt";
	private final String customersFile = "Customer.txt";
	private final String ordersFile = "Order.txt";
	private final String[] filesToZip = new String[] { carsFile, customersFile,
			ordersFile };
	private final String zipFile = "CarRental.zip";

	private final String DATE_FORMAT = "dd.MM.yyyy";

	private static List<Customer> customers;
	private static List<Car> cars;
	private static List<Order> orders;
	static {
		// Customer c1 = new Customer("Ivan", "Ivanov", "AB234", "user",
		// "user");
		// Customer c2 = new Customer("Petr", "Sidorov", "AB345", "admin",
		// "admin");
		customers = new ArrayList<Customer>();
		// customers.add(new Customer("Ivan", "Ivanov", "AB234", "user",
		// "user"));
		// customers
		// .add(new Customer("Petr", "Sidorov", "AB345", "admin", "admin"));
		//
		// Car car1 = new Car(1, "BMV", "M5", 2001, 10);
		// Car car2 = new Car(2, "BMV", "M1", 1990, 20);
		// Car car3 = new Car(3, "Audi", "A5", 1989, 15);
		cars = new ArrayList<Car>();
		// cars.add(new Car(1, "BMV", "M5", 2001, 12));
		// cars.add(new Car(2, "BMV", "M1", 1990, 11));
		// cars.add(new Car(3, "Audi", "100", 1989, 15));

		orders = new ArrayList<Order>();
		// orders.add(new Order(1, c1, car1, new Date(), new Date()));
	}

	private int port;
	private ServerSocket serverSocket;
	private int clients;
	private BlockingQueue<ClientThread> threads = new LinkedBlockingQueue<ClientThread>();

	public Server(int port) throws IOException {
		this.port = port;
		this.serverSocket = new ServerSocket(port);
	}

	public void run() {
		try {
			Packing.unpack(zipFile);
			cars = Serialization.deserializeCar(carsFile);
			customers = Serialization.deserializeCustomer(customersFile);
			orders = Serialization.deserializeOrder(ordersFile);
			// printCars(cars);
			// printCustomers(customers);
			// printOrders(orders);
			// JdomWriting.writeCarsToXml(cars, "jdom-output.xml");
			// CsvWriting.writeCarsToCsv(cars, "my.csv");
			System.out.println("Server started. ");
			while (!serverSocket.isClosed()) {
				Socket client = serverSocket.accept();
				System.out.println("Connected " + client.getInetAddress());
				clients++;
				ClientThread clientThread = new ClientThread(
						"Client" + clients, client, this);
				threads.offer(clientThread);
				clientThread.start();
				SendMessageCommand greeting = new SendMessageCommand();
				greeting.setMessage(clientThread.getClientName()
						+ " Hello! To work with our system you have to register or login \n"
						+ "Use the following commands: \n"
						+ "login yourlogin password \n"
						+ "registration name surname number_passport login password");
				greeting.setClientThread(clientThread);
				greeting.setTimestamp(System.currentTimeMillis());
				// sendMessageToClients(greeting, clientThread);
				clientThread.addCommand(greeting);
			}
		} catch (IOException e) {

		} finally {
			if (!serverSocket.isClosed()) {
				try {
					serverSocket.close();
				} catch (IOException ignored) {

				}
			}
		}
	}

	public synchronized void shutdown(ShutdownCommand shutdown) {
		Serialization.serializeCar(carsFile, cars);
		Serialization.serializeCustomer(customersFile, customers);
		Serialization.serializeOrder(ordersFile, orders);
		Packing.pack(filesToZip, zipFile);
		try {
			int delay = shutdown.getDelay();
			System.out.println("Server is about to shutdown (in " + delay
					+ " secs)");
			threads.clear();
			// close clients
			for (ClientThread t : threads) {
				ShutdownCommand newShutdown = new ShutdownCommand();
				newShutdown.setClientThread(t);
				newShutdown.setMessage(shutdown.getMessage());
				t.addCommand(newShutdown);
			}

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

	public synchronized void removeClient(ClientThread t) {
		System.out.println("Client " + t.getClientName() + " exited");
		t.close();
		threads.remove(t);
	}

	public synchronized void sendMessageToClients(AbstractCommand cmd,
			ClientThread clientThread) {
		// for (ClientThread t : threads) {
		// if (!t.getClientName().equals(clientThread.getClientName())) {
		// t.addCommand(cmd);
		// }
		// }
		clientThread.addCommand(cmd);

	}

	public synchronized void sendMessageToClients(AbstractCommand cmd) {
		for (ClientThread t : threads) {
			t.addCommand(cmd);
		}

	}

	public synchronized void loginCustomer(LoginCommand cmd,
			ClientThread clientThread) {
		if (clientThread.getCustomer() != null) {
			SendMessageCommand sm = new SendMessageCommand();
			sm.setMessage("You was already registered!");
			sm.setClientThread(clientThread);
			clientThread.addCommand(sm);
			return;
		}

		for (Customer c : customers) {
			if (c.getLogin().equals(cmd.getLogin())
					&& c.getPassword().equals(cmd.getPassword())) {
				c.setEntered(true);
				clientThread.setName(c.getLogin());
				cmd.setLogged(true);
				clientThread.setCustomer(c);

			}
		}
		clientThread.addCommand(cmd);
	}

	public synchronized void showCars(ShowCarsCommand cmd,
			ClientThread clientThread) {
		for (Car c : cars) {
			ShowCarsCommand newCommand = (ShowCarsCommand) Commands.SHOWCARS
					.getCommandInstance();
			newCommand.setId(c.getId());
			newCommand.setBrand(c.getBrand());
			newCommand.setModel(c.getModel());
			newCommand.setYear(c.getYear());
			clientThread.addCommand(newCommand);
		}
	}

	public synchronized void changeLanguage(ChangeLanguageCommand cmd,
			ClientThread clientThread) {
		clientThread.setLocaleNumber((clientThread.getLocaleNumber() + 1) % 2);
		if (clientThread.getLocaleNumber() == 0)
			clientThread.setLocale(new Locale("", ""));
		else
			clientThread.setLocale(new Locale("RU", ""));
		clientThread.addCommand(cmd);
	}

	public void printCars(List<Car> cars2) {
		System.out.println();
		for (Object l : cars2) {
			System.out.println(l);
		}
	}

	public void printCustomers(List<Customer> cust) {
		System.out.println();
		for (Object l : cust) {
			System.out.println(l);
		}
	}

	public void printOrders(List<Order> order) {
		System.out.println();
		for (Object l : order) {
			System.out.println(l);
		}
	}

	public static void main(String[] args) throws IOException {
		Server server = new Server(1234);
		server.run();

	}

	public synchronized void registrationCustomer(RegistrationCommand cmd,
			ClientThread clientThread) {
		if (clientThread.getCustomer() != null) {
			SendMessageCommand sm = new SendMessageCommand();
			sm.setMessage("You was already registered!");
			sm.setClientThread(clientThread);
			clientThread.addCommand(sm);
			return;
		}
		customers.add(new Customer(cmd.getName(), cmd.getSurname(), cmd
				.getNumberPassp(), cmd.getLogin(), cmd.getPassword()));
		clientThread.addCommand(cmd);
	}

	public synchronized void registrNewOrder(OrderCommand cmd,
			ClientThread clientThread) {
		for (Car c : cars) {
			if (c.getId() == Integer.parseInt(cmd.getCarNumber())) {
				SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
				try {
					Date begin = sdf.parse(cmd.getBeginDate());
					Date end = sdf.parse(cmd.getEndDate());
					int lastNumber = orders.get(orders.size() - 1).getNumber();
					orders.add(new Order(++lastNumber, clientThread
							.getCustomer(), c, begin, end));
					SendMessageCommand save = new SendMessageCommand();
					ResourceBundle resourseBundle = ResourceBundle.getBundle(
							"messages", clientThread.getLocale());
					String str = resourseBundle.getString("send_order");
					save.setMessage(str);
					save.setClientThread(clientThread);
					save.setTimestamp(System.currentTimeMillis());
					// sendMessageToClients(greeting, clientThread);
					clientThread.addCommand(save);
					// System.out.println(sdf.format(ddd));
				} catch (ParseException e) {
					e.printStackTrace();
					SendMessageCommand error = new SendMessageCommand();
					ResourceBundle resourseBundle = ResourceBundle.getBundle(
							"messages", clientThread.getLocale());
					String str = resourseBundle.getString("date_format");
					error.setMessage(str);
					error.setClientThread(clientThread);
					error.setTimestamp(System.currentTimeMillis());
					// sendMessageToClients(greeting, clientThread);
					clientThread.addCommand(error);
				}

			}
		}

	}

	public synchronized void getOrders(ShowOrdersCommand cmd,
			ClientThread clientThread) {
		List<Order> ordersToShow = new ArrayList<Order>();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		try {
			Date ddd = sdf.parse(cmd.getDate());
			int count = 0;
			for (Order o : orders) {
				if (o.getBeginOrder().compareTo(ddd) < 0
						&& o.getEndOrder().compareTo(ddd) >= 0) {
					// System.out.println("add "+o);
					ordersToShow.add(o);
					count++;
				}
			}
			cmd.setOrdersToShow(ordersToShow);
			cmd.setCount(count);
			clientThread.addCommand(cmd);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
