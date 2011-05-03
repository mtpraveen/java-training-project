/**
 * 
 */
package by.brsu.java.training;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.TreeMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;

/**
 * Database which stores data in collections 
 * @author yura
 * 
 */
public class SimpleDB implements IDBSystem {
	private static final Logger LOGGER = Logger.getLogger(MySQLDB.class);
	
	private static final int BUFFER_SIZE = 128;
	private static final String USERS_FILE_NAME = ".users.txt";
	private static final String SERVICES_FILE_NAME = ".services.txt";
	private static final String PAYMENTS_FILE_NAME = ".payments.txt";
	private static final String OPERATIONS_FILE_NAME = ".operations.txt";
	private static final String ARCHIVE_FILE_NAME = "phoneSystem.zip";

	private TreeMap<Integer, User> users = new TreeMap<Integer, User>();
	private ArrayList<Operation> operations = new ArrayList<Operation>();
	private TreeMap<Integer, Payment> payments = new TreeMap<Integer, Payment>();
	private TreeMap<Integer, Service> availableServices = new TreeMap<Integer, Service>();

	/**
	 * 
	 */
	public SimpleDB() {
		super();
		createServices();

	}

	private void createServices() {
		Service service1 = new Service("Talk", 10);
		Service service2 = new Service("Auto answer", 20);
		Service service3 = new Service("Alarm beep", 30);

		availableServices.put(service1.getId(), service1);
		availableServices.put(service2.getId(), service2);
		availableServices.put(service3.getId(), service3);
	}

	@Override
	public boolean addUser(User user) {
		if (users.containsKey(user.getId()))
			return false;
		users.put(user.getId(), user);
		return true;
	}

	@Override
	public boolean deleteUser(int userId) {
		if (!users.containsKey(userId)) {
			return false;
		}
		users.remove(userId);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see by.brsu.java.training.ISystemDB#loadUser(int)
	 */
	@Override
	public User getUser(int userId) {
		if (!users.containsKey(userId))
			return null;
		return users.get(userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see by.brsu.java.training.ISystemDB#saveUser(by.brsu.java.training.User)
	 */
	@Override
	public boolean setUser(User user) {
		if (!users.containsKey(user.getId())) {
			return false;
		}
		users.put(user.getId(), user);
		return true;
	}

	@Override
	public TreeMap<Integer, User> getUsers() {
		return users;
	}

	@Override
	public void addPayment(Payment payment) {
		payments.put(payment.getId(), payment);
	}

	@Override
	public ArrayList<Payment> getPayments(int userId) {
		ArrayList<Payment> result = new ArrayList<Payment>();
		for (Payment payment : this.getPayments()) {
			if (payment.getUserId() == userId) {
				result.add(payment);
			}
		}
		return result;
	}

	@Override
	public void addUsedServices(ArrayList<Service> services, int userId,
			Date date) {
		operations.add(new Operation(services, userId, date));
	}

	@Override
	public ArrayList<Operation> getUsedServices(int userId, Date begin, Date end) {
		ArrayList<Operation> userOperations = new ArrayList<Operation>();
		Calendar beginDate = new GregorianCalendar();
		beginDate.setTime(begin);
		if ( beginDate.get(Calendar.MONTH) == 0 ) {
			beginDate.set(Calendar.YEAR, beginDate.get(Calendar.YEAR) - 1);
			beginDate.set(Calendar.MONTH, 11);
		} else {
			beginDate.set(Calendar.MONTH, beginDate.get(Calendar.MONTH) - 1);
		}
		Calendar endDate = new GregorianCalendar();
		endDate.setTime(end);
		if ( endDate.get(Calendar.MONTH) == 11 ) {
			endDate.set(Calendar.YEAR, endDate.get(Calendar.YEAR) + 1);
			endDate.set(Calendar.MONTH, 0);
		} else {
			endDate.set(Calendar.MONTH, endDate.get(Calendar.MONTH) + 1);
		}
		
		
		for (Operation operation : operations) {
			if (operation.getUserId() == userId) {
				if (operation.getDate().after(beginDate.getTime())
						&& operation.getDate().before(endDate.getTime())) {
					userOperations.add(operation);
				}
			}
		}
		return userOperations;
	}

	@Override
	public ArrayList<Service> getAvailableServices() {
		return new ArrayList<Service>(availableServices.values());
	}

	@Override
	public ArrayList<Payment> getPayments() {
		return new ArrayList<Payment>(payments.values());
	}

	@Override
	public void drop() {
		availableServices = new TreeMap<Integer, Service>();
		operations = new ArrayList<Operation>();
		payments = new TreeMap<Integer, Payment>();
		users = new TreeMap<Integer, User>();
	}

	@Override
	public boolean addAvailableService(Service service) {
		if (availableServices.containsKey(service.getId()))
			return false;
		availableServices.put(service.getId(), service);
		return true;
	}

	@Override
	public ArrayList<Operation> getUsedServices() {
		return operations;
	}

	@Override
	public boolean addOperation(Operation operation) {
		operations.add(operation);
		return true;
	}

	// work with files
	private boolean deleteFiles(String basicFileName) {
		File usersFile = new File(basicFileName + USERS_FILE_NAME);
		File servicesFile = new File(basicFileName + SERVICES_FILE_NAME);
		File paymentsFile = new File(basicFileName + PAYMENTS_FILE_NAME);
		File operationsFile = new File(basicFileName + OPERATIONS_FILE_NAME);
		return usersFile.delete() && servicesFile.delete()
				&& paymentsFile.delete() && operationsFile.delete();
	}

	@Override
	public boolean saveFiles(String basicFileName) {
		ArrayList<User> users = new ArrayList<User>(this.getUsers().values());
		ArrayList<Service> services = this.getAvailableServices();
		ArrayList<Payment> payments = this.getPayments();
		ArrayList<Operation> operations = this.getUsedServices();
		try {
			FileWriter usersFW = new FileWriter(new File(basicFileName
					+ USERS_FILE_NAME));
			FileWriter servicesFW = new FileWriter(new File(basicFileName
					+ SERVICES_FILE_NAME));
			FileWriter paymentsFW = new FileWriter(new File(basicFileName
					+ PAYMENTS_FILE_NAME));
			FileWriter operationsFW = new FileWriter(new File(basicFileName
					+ OPERATIONS_FILE_NAME));

			BufferedWriter usersBW = new BufferedWriter(usersFW);
			BufferedWriter servicesBW = new BufferedWriter(servicesFW);
			BufferedWriter paymentsBW = new BufferedWriter(paymentsFW);
			BufferedWriter operationsBW = new BufferedWriter(operationsFW);

			PrintWriter usersWriter = new PrintWriter(usersBW);
			PrintWriter servicesWriter = new PrintWriter(servicesBW);
			PrintWriter paymentsWriter = new PrintWriter(paymentsBW);
			PrintWriter operationsWriter = new PrintWriter(operationsBW);

			for (Service service : services) {
				servicesWriter.println(service.toString());
			}
			for (Payment payment : payments) {
				paymentsWriter.println(payment.toString());
			}
			for (Operation operation : operations) {
				operationsWriter.println(operation.toString());
			}
			for (User user : users) {
				usersWriter.println(user.toString());
			}
			usersWriter.close();
			servicesWriter.close();
			paymentsWriter.close();
			operationsWriter.close();

			usersBW.close();
			servicesBW.close();
			paymentsBW.close();
			operationsBW.close();

			usersFW.close();
			servicesFW.close();
			paymentsFW.close();
			operationsFW.close();
			return true;
		} catch (IOException e) {
			LOGGER.error(String.format("Input/Output error %s", e.getStackTrace().toString()));
			return false;
		}
	}

	@Override
	public boolean loadFiles(String basicFileName) {
		try {
			BufferedReader usersReader = new BufferedReader(new FileReader(
					new File(basicFileName + USERS_FILE_NAME)));
			BufferedReader servicesReader = new BufferedReader(new FileReader(
					new File(basicFileName + SERVICES_FILE_NAME)));
			BufferedReader paymentsReader = new BufferedReader(new FileReader(
					new File(basicFileName + PAYMENTS_FILE_NAME)));
			BufferedReader operationsReader = new BufferedReader(
					new FileReader(new File(basicFileName
							+ OPERATIONS_FILE_NAME)));

			// reading data from files
			String str;
			while ((str = servicesReader.readLine()) != null) {
				Service service = new Service();
				if (service.parseService(str))
					this.addAvailableService(service);
			}
			while ((str = usersReader.readLine()) != null) {
				User user = new User();
				if (user.parseUser(str))
					this.addUser(user);
			}
			while ((str = paymentsReader.readLine()) != null) {
				Payment payment = new Payment();
				if (payment.parsePayment(str))
					this.addPayment(payment);
			}
			while ((str = operationsReader.readLine()) != null) {
				Operation operation = new Operation();
				if (operation.parseOperation(str))
					this.addOperation(operation);
			}

			return true;
		} catch (IOException e) {
			LOGGER.error(String.format("Input/Output error %s", e.getStackTrace().toString()));
			return false;
		}
	}

	@Override
	public boolean zipArchive(String basicFileName) {
		try {
			FileInputStream usersInputStream = new FileInputStream(
					basicFileName + USERS_FILE_NAME);
			FileInputStream servicesInputStream = new FileInputStream(
					basicFileName + SERVICES_FILE_NAME);
			FileInputStream paymentsInputStream = new FileInputStream(
					basicFileName + PAYMENTS_FILE_NAME);
			FileInputStream operationsInputStream = new FileInputStream(
					basicFileName + OPERATIONS_FILE_NAME);
			FileOutputStream fout = new FileOutputStream(basicFileName
					+ ARCHIVE_FILE_NAME);
			ZipOutputStream zout = new ZipOutputStream(fout);

			writeEntry(servicesInputStream, zout, SERVICES_FILE_NAME);
			writeEntry(paymentsInputStream, zout, PAYMENTS_FILE_NAME);
			writeEntry(usersInputStream, zout, USERS_FILE_NAME);
			writeEntry(operationsInputStream, zout, OPERATIONS_FILE_NAME);

			zout.close();
			fout.close();
			usersInputStream.close();
			servicesInputStream.close();
			paymentsInputStream.close();
			operationsInputStream.close();
			return this.deleteFiles(basicFileName);
		} catch (FileNotFoundException e) {
			LOGGER.error(String.format("Input/Output error %s", e.getStackTrace().toString()));
			return false;
		} catch (IOException e) {
			LOGGER.error(String.format("Input/Output error %s", e.getStackTrace().toString()));
			return false;
		}
	}

	@Override
	public boolean unzipArchive(String basicFileName) {
		try {
			FileOutputStream servicesOS = new FileOutputStream(basicFileName
					+ SERVICES_FILE_NAME);
			FileOutputStream paymentsOS = new FileOutputStream(basicFileName
					+ PAYMENTS_FILE_NAME);
			FileOutputStream operationsOS = new FileOutputStream(basicFileName
					+ OPERATIONS_FILE_NAME);
			FileOutputStream usersOS = new FileOutputStream(basicFileName
					+ USERS_FILE_NAME);

			BufferedOutputStream serviceBOS = new BufferedOutputStream(
					servicesOS);
			BufferedOutputStream paymentsBOS = new BufferedOutputStream(
					paymentsOS);
			BufferedOutputStream operationsBOS = new BufferedOutputStream(
					operationsOS);
			BufferedOutputStream usersBOS = new BufferedOutputStream(usersOS);

			ZipFile zip = new ZipFile(basicFileName + ARCHIVE_FILE_NAME);
			Enumeration<ZipEntry> entries = (Enumeration<ZipEntry>) zip
					.entries();

			ZipEntry entry = entries.nextElement();
			readEntry(zip.getInputStream(entry), new BufferedOutputStream(
					new FileOutputStream(basicFileName + SERVICES_FILE_NAME)));
			entry = entries.nextElement();
			readEntry(zip.getInputStream(entry), new BufferedOutputStream(
					new FileOutputStream(basicFileName + PAYMENTS_FILE_NAME)));
			entry = entries.nextElement();
			readEntry(zip.getInputStream(entry), new BufferedOutputStream(
					new FileOutputStream(basicFileName + USERS_FILE_NAME)));
			entry = entries.nextElement();
			readEntry(zip.getInputStream(entry), new BufferedOutputStream(
					new FileOutputStream(basicFileName + OPERATIONS_FILE_NAME)));
			zip.close();

			serviceBOS.close();
			paymentsBOS.close();
			operationsBOS.close();
			usersBOS.close();

			servicesOS.close();
			paymentsOS.close();
			operationsOS.close();
			usersOS.close();
			return true;
		} catch (FileNotFoundException e) {
			LOGGER.error(String.format("Input/Output error %s", e.getStackTrace().toString()));
			return false;
		} catch (IOException e) {
			LOGGER.error(String.format("Input/Output error %s", e.getStackTrace().toString()));
			return false;
		}
	}

	private void writeEntry(FileInputStream inputStream, ZipOutputStream zout,
			String fileName) throws IOException {
		try {
			byte[] byteBuf = new byte[BUFFER_SIZE];
			int byteCount = 0;
			int size = 0;
			ZipEntry ze = new ZipEntry(fileName);
			zout.putNextEntry(ze);
			while ((byteCount = inputStream.read(byteBuf)) != -1) {
				zout.write(byteBuf, 0, byteCount);
				size += byteCount;
			}
			zout.closeEntry();
		} finally {
		}
	}

	private void readEntry(InputStream in, OutputStream out) throws IOException {
		try {
			byte[] byteBuffer = new byte[BUFFER_SIZE];
			int byteCount;
			while ((byteCount = in.read(byteBuffer)) >= 0)
				out.write(byteBuffer, 0, byteCount);
		} finally {
			in.close();
			out.close();
		}
	}
}
