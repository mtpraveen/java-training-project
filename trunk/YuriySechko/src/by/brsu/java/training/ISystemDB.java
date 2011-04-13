/**
 * 
 */
package by.brsu.java.training;

import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;

/**
 * @author yura
 *
 */
public interface ISystemDB {
	public boolean addUser(User user);
	public boolean deleteUser(int userId);
	public User getUser(int userId);
    public boolean setUser(User user);
    public TreeMap<Integer, User> getUsers();
    
    public void addPayment(Payment payment);
    public ArrayList<Payment> getPayments();
    public ArrayList<Payment> getPayments(int userId);
   
    public void addBalance(Balance balance);
    public ArrayList<Balance> getBalances();
    public ArrayList<Balance> getBalances(int userId);
    
    public void addUserServices(ArrayList<Service> services, int userId, Date date);
    public ArrayList<Operation> getUsedServices(int userId, Date begin, Date end);
    public ArrayList<Service> getAvailableServices();
}
