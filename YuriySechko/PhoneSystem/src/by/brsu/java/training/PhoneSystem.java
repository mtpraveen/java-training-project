package by.brsu.java.training;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.PropertyConfigurator;

/**
 * Class for modeling automatic phone system
 * 
 * @author yura
 * 
 */

public class PhoneSystem {
	private static long nextPhoneNumber = 1024;
	private static PhoneSystem instance = new PhoneSystem();

	private IDBSystem database;
	private Administrator administrator = new Administrator();

	/**
	 * Returns instance of PhoneSystem singleton
	 * 
	 * @return reference to unique PhoneSystem instance
	 */
	public static PhoneSystem getInstance() {
		return instance;
	}

	/**
	 * Returns next possibly available phone number in system
	 * 
	 * @return Next possibly available phone number
	 */
	private static long getNextPhoneNumber() {
		return nextPhoneNumber++;
	}

	/**
	 * Creates phone system with <code>userCount</code> users and 1
	 * administrator
	 * 
	 * @param userCount
	 *            Specifies total user count
	 */
	private PhoneSystem() {
	}

	/**
	 * Returns payment for user with specified id
	 * 
	 * @param userId
	 *            User's id
	 * @return Payment for user's minutes and services if user is exist,
	 *         otherwise <code>null</code>
	 */
	public Payment getPayment(int userId) {
		User user = getUser(userId);

		if (user == null)
			return null;

		Payment lastPayment = getLastPayment(userId);
		long totalCost = 0;
		Calendar curDate = new GregorianCalendar();
		curDate.setTimeInMillis(System.currentTimeMillis());
		if (lastPayment != null) {
			GregorianCalendar lastDate = new GregorianCalendar();
			lastDate.setTime(lastPayment.getDate());
			if ((curDate.get(Calendar.MONTH) == lastDate.get(Calendar.MONTH) && curDate
					.get(Calendar.YEAR) == lastDate.get(Calendar.YEAR))
					|| curDate.before(lastPayment.getDate()))
				return null;
		}

		if (user.getServices() != null) {
			for (Service service : user.getServices()) {
				totalCost += service.getCost();
			}
		}

		if (totalCost == 0)
			return null;

		Payment payment = new Payment(totalCost, curDate.getTime(), userId);
		return payment;
	}

	private Payment getLastPayment(int userId) {
		if (getUser(userId) == null)
			return null;
		// sorting user's payments in direct order
		ArrayList<Payment> userPayments = database.getPayments(userId);
		Collections.sort(userPayments, new Comparator<Payment>() {
			public int compare(Payment o1, Payment o2) {
				return o1.getDate().compareTo(o2.getDate());
			}
		});

		Payment lastPayment = userPayments.size() > 0 ? userPayments
				.get(userPayments.size() - 1) : null;

		return lastPayment;
	}

	/**
	 * Automatically changes user's balance
	 * 
	 * @param userId
	 *            User's id
	 * @param howMoney
	 *            How much money must be payed
	 * @return <code>true</code> if user with specified id is exist and have
	 *         sufficient balance, otherwise <code>false</code>
	 */
	public boolean pay(int userId, Payment payment) {
		User user = getUser(userId);

		if (user == null)
			return false;

		if (userId != payment.getUserId())
			return false;

		Payment lastPayment = getLastPayment(userId);
		Calendar curDate = new GregorianCalendar();
		curDate.setTimeInMillis(System.currentTimeMillis());
		Calendar lastDate = new GregorianCalendar();

		if (lastPayment != null) {
			lastDate.setTime(lastPayment.getDate());
			if (curDate.get(Calendar.MONTH) == lastDate.get(Calendar.MONTH)
					&& curDate.get(Calendar.YEAR) == lastDate
							.get(Calendar.YEAR))
				return false;
		}
		long totalCost = payment.getCost();
		if (totalCost <= 0)
			return false;

		long balance = user.getBalance();
		if ((balance - totalCost) < 0)
			return false;
		user.setBalance(balance - totalCost);
		database.addPayment(payment);
		database.setUser(user);
		database.addOperation(new Operation(user.getServices(), user.getId(),
				curDate.getTime()));
		return true;
	}

	/**
	 * Deletes services for specified user
	 * 
	 * @param userId
	 *            User's ids
	 */
	public void setUserServices(int userId, ArrayList<Service> services) {
		User user = getUser(userId);
		user.setServices(services);
		database.setUser(user);
	}

	/**
	 * Sets new phone number for specified user
	 * 
	 * @param userId
	 *            User's id
	 * @param newPhoneNumber
	 *            New phone number
	 */
	public void setUserPhoneNumber(int userId, long newPhoneNumber) {
		User user = getUser(userId);
		user.setPhoneNumber(newPhoneNumber);
		database.setUser(user);
	}

	/**
	 * Returns array witch contains id's for all users in system
	 */
	public ArrayList<Integer> getUsersIds() {
		ArrayList<Integer> idArray = new ArrayList<Integer>();
		for (Integer id : database.getUsers().keySet()) {
			idArray.add(id);
		}
		return idArray;
	}

	/**
	 * Check phone number availability
	 * 
	 * @param phoneNumber
	 *            Phone number to check
	 * @return <code>true</code> if specified phone number is available
	 */
	public boolean isPhoneNumberAvailable(long phoneNumber) {
		ArrayList<Integer> idList = getUsersIds();
		for (Integer userId : idList) {
			if (getUser(userId).getPhoneNumber() == phoneNumber)
				return false;
		}
		return true;
	}

	/**
	 * Returns array witch contains all available services
	 */
	public ArrayList<Service> getAvailableServices() {
		return database.getAvailableServices();
	}

	/**
	 * Creates new random user in system
	 * 
	 * @return <code>false</code> if there are not available phone numbers
	 */
	private boolean createRandomUser() {
		// create new user with random properties
		java.util.Random randGenerator = new java.util.Random();
		User user = new User(getNextPhoneNumber(), UserStatus.NORMAL,
				randGenerator.nextInt() % 100 + 100,
				database.getAvailableServices());
		long curPhoneNumber = user.getPhoneNumber();

		// checking phone number, if phone number is exist, take previous + 1
		while (!this.isPhoneNumberAvailable(user.getPhoneNumber())) {
			curPhoneNumber = getNextPhoneNumber();
			user.setPhoneNumber(curPhoneNumber);
			if (curPhoneNumber > 999999999)
				return false;
		}

		// add new user in list of users
		database.addUser(user);
		return true;
	}

	public boolean addUser(User user) {
		if (user == null)
			return false;
		database.addUser(user);
		return true;
	}

	/**
	 * Returns current administrator
	 * 
	 * @return
	 */
	public Administrator getAdministrator() {
		return administrator;
	}

	/**
	 * Sets new status for specified user
	 * 
	 * @param userId
	 *            User's id
	 * @param newStatus
	 *            New status
	 */
	public void changeUserStatus(int userId, UserStatus newStatus) {
		User user = getUser(userId);
		user.setStatus(newStatus);
		database.setUser(user);
	}

	/**
	 * Returns reference to user with specified id
	 * 
	 * @param userId
	 *            User's id
	 * @return <code>null</code> if users with specified id is not exist
	 */
	public User getUser(int userId) {
		return database.getUser(userId);
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("PhoneSystem \n[\n");

		ArrayList<User> users = new ArrayList<User>(database.getUsers()
				.values());

		for (User user : users) {
			strBuf.append(user.toString()).append("\n");
		}

		strBuf.append(administrator.toString()).append("\n");
		strBuf.append("Available services: ")
				.append(this.getAvailableServices()).append("\n");
		strBuf.append("Used services: ").append(database.getUsedServices())
				.append("\n");
		strBuf.append("Payments: ").append(database.getPayments()).append("\n");

		return strBuf.toString();
	}

	public boolean bindDatabase(IDBSystem database) {
		if (database == null)
			return false;
		this.database = database;
		return true;
	}

	public boolean saveArchive(String basicFileName) {
		return database.saveFiles(basicFileName)
				&& database.zipArchive(basicFileName);
	}

	public boolean loadArchive(String basicFileName) {
		database.drop();
		return database.unzipArchive(basicFileName)
				&& database.loadFiles(basicFileName);
	}

	private boolean isPayed(Operation operation, ArrayList<Payment> userPayments) {
		for (Payment payment : userPayments) {
			Calendar operationDate = new GregorianCalendar();
			operationDate.setTime(operation.getDate());
			Calendar paymentDate = new GregorianCalendar();
			paymentDate.setTime(payment.getDate());
			if (operationDate.get(Calendar.MONTH) == paymentDate
					.get(Calendar.MONTH)
					&& operationDate.get(Calendar.YEAR) == paymentDate
							.get(Calendar.YEAR)) {
				return true;
			}
		}
		return false;
	}

	public boolean createReport(int userId, Date begin, Date end,
			String fileName) {
		try {
			char delim = ',';
			ArrayList<Payment> userPayments = database.getPayments(userId);
			ArrayList<Operation> userOperations = database.getUsedServices(
					userId, begin, end);
			FileOutputStream outFile = new FileOutputStream(fileName);
			PrintWriter writer = new PrintWriter(outFile);
			writer.println("Date" + delim + "Service name" + delim
					+ "service cost" + delim + "user\'s id" + delim
					+ "payed(yes or no)");
			for (Operation operation : userOperations) {
				boolean payed = isPayed(operation, userPayments);
				for (Service service : operation.getServices()) {
					writer.print(operation.getDate());
					writer.print(delim);
					writer.print(service.getName());
					writer.print(delim);
					writer.print(service.getCost());
					writer.print(delim);
					writer.print(userId);
					writer.print(delim);
					writer.println(payed ? "yes" : "no");
				}
			}
			writer.close();
			outFile.close();
		} catch (FileNotFoundException e) {
			return false;
		} catch (IOException e) {
			return false;
		}

		return true;
	}

	public static void main(String[] args) {
		PropertyConfigurator.configure("log4j.properties");
		PhoneSystem ps = PhoneSystem.getInstance();
		IDBSystem database = new SimpleDB();
		ps.bindDatabase(database);

		ps.loadArchive(".\\");
		System.out.println(ps.toString());

		// Calendar begin = new GregorianCalendar();
		// Calendar end = new GregorianCalendar();
		// begin.setTimeInMillis(System.currentTimeMillis());
		// end.setTimeInMillis(System.currentTimeMillis());
		// begin.set(Calendar.MONTH, 2);
		// end.set(Calendar.MONTH, 5);
		// ps.createReport( 1, begin.getTime(), end.getTime(), "report.txt");

//		Date begin = new Date(System.currentTimeMillis());
//		Date end = new Date(System.currentTimeMillis());
//		begin.setMonth(2);
//		end.setMonth(6);
//		ps.createReport(1, begin, end, "report.txt");

	}

}
