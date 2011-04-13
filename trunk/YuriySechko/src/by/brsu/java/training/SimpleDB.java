/**
 * 
 */
package by.brsu.java.training;

import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author yura
 *
 */
public class SimpleDB implements ISystemDB {

	private TreeMap<Integer, User> users = new TreeMap<Integer, User>();
	private TreeSet<Balance> balances = new TreeSet<Balance>();
	private TreeSet<Operation> operations = new TreeSet<Operation>();
	private TreeMap<Integer, Payment> payments = new TreeMap<Integer, Payment>();
	private TreeMap<Integer, Service> availableServices = new TreeMap<Integer, Service>();

	/**
	 * 
	 */
	public SimpleDB() {
		super();
    	loadServices();
	}

	private void loadServices() {
		Service service1 = new Service("Talk", 10);
		Service service2 = new Service("Auto answer", 20);
		Service service3 = new Service("Alarm beep", 30);
		
		availableServices.put(service1.getId(), service1);
		availableServices.put(service2.getId(), service2);
		availableServices.put(service3.getId(), service3);
	}

	@Override
	public boolean addUser(User user) {
		if ( users.containsKey(user.getId()) )
			return false;
		users.put(user.getId(), user);
		return true;
	}

	@Override
	public boolean deleteUser(int userId) {
		if ( !users.containsKey(userId) ) {
			return false;
		}
		users.remove(userId);
		return true;
	}
	
	/* (non-Javadoc)
	 * @see by.brsu.java.training.ISystemDB#loadUser(int)
	 */
	@Override
	public User getUser(int userId) {
		if ( !users.containsKey(userId) )
			return null;
		return users.get(userId);
	}

	/* (non-Javadoc)
	 * @see by.brsu.java.training.ISystemDB#saveUser(by.brsu.java.training.User)
	 */
	@Override
	public boolean setUser(User user) {
		if ( !users.containsKey(user.getId()) ) {
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
		return new ArrayList<Payment>(payments.values());
	}

	@Override
	public void addBalance(Balance balance) {
		balances.add(balance);
	}

	@Override
	public ArrayList<Balance> getBalances() {
		return new ArrayList<Balance>(balances);
	}

	@Override
	public ArrayList<Balance> getBalances(int userId) {
		ArrayList<Balance> userBalances = new ArrayList<Balance>();
		for( Balance balance : balances ) {
			if ( balance.getUserId() == userId )
				userBalances.add(balance);
		}
		return userBalances;
	}

	@Override
	public void addUserServices(ArrayList<Service> services, int userId, Date date) {
		operations.add(new Operation(services, userId, date));
	}

	@Override
	public ArrayList<Operation> getUsedServices(int userId, Date begin, Date end) {
		ArrayList<Operation> userOperations = new ArrayList<Operation>();
		for( Operation operation : operations ) {
			if ( operation.getUserId() == userId ) {
				if ( operation.getDate().after(begin) && operation.getDate().before(end) ) {
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
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SimplePhoneSystemDB [users=" + users + ", balances=" + balances
				+ ", operations=" + operations + ", payments=" + payments
				+ ", availableServices=" + availableServices + "]";
	}
}
