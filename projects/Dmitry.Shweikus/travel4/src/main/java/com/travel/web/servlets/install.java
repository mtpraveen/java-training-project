package com.travel.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.travel.dao.ClientDao;
import com.travel.dao.DiscountDao;
import com.travel.dao.OrderDao;
import com.travel.dao.PaymentDao;
import com.travel.dao.TourDao;
import com.travel.dao.TourSheduleDao;
import com.travel.dao.UserDao;
import com.travel.db.ConnectionManager;
import com.travel.enums.TransportKind;
import com.travel.enums.TravelKind;
import com.travel.exceptions.DbSqlException;
import com.travel.pojo.Client;
import com.travel.pojo.Discount;
import com.travel.pojo.Order;
import com.travel.pojo.Payment;
import com.travel.pojo.Tour;
import com.travel.pojo.TourShedule;
import com.travel.pojo.User;
import com.travel.web.utils.TravelSecurity;

/**
 * Servlet implementation class install 
 * @WebServlet("/install") - we don't need this unit
 */
public class install extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao = new UserDao();
	private ClientDao clientDao = new ClientDao();
	private DiscountDao discountDao = new DiscountDao();
	private TourSheduleDao arrivalSheduleDao = new TourSheduleDao();
	private OrderDao orderDao = new OrderDao();
	private PaymentDao paymentDao = new PaymentDao();
	private TourDao tourDao = new TourDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public install() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(checkCanInstall())
		{
			try
			{
				clearDataBase();
				execInstall(response.getWriter());
			} catch (DbSqlException e)
			{
				throw new ServletException(e.getMessage());
			}
		}
		else
		{
			response.getWriter().print("ERROR : Database is not empty.");
		}
	}

    private boolean checkCanInstall()
    {
    	return true;
    }
    
    private void clearDataBase() throws DbSqlException
    {
    	paymentDao.deleteAll();
    	orderDao.deleteAll();
    	arrivalSheduleDao.deleteAll();
    	tourDao.deleteAll();
    	clientDao.deleteAll();
    	discountDao.deleteAll();
    	userDao.deleteAll();
    }
    private void createTableCaption(PrintWriter writer, String tableName)
    {
    	writer.println("Creating table : " + tableName + " <br/>");
    }
    private void c(PrintWriter writer, Object object)
    {
    	writer.println(object.toString() + " <br/>");
    }
    private void execInstall(PrintWriter writer) throws DbSqlException
    {
    	//==============================================
    	//==============================================
    	createTableCaption(writer, userDao.getTableName());
    	User admin = userDao.createUser("Administrator", "admin", TravelSecurity.hashPassword("1"), true);
    	User manager1 = userDao.createUser("Ivan Ivanov", "ivan", TravelSecurity.hashPassword("1"), false);
    	User manager2 = userDao.createUser("Masha Petrova", "masha", TravelSecurity.hashPassword("1"), false);
    	//c(writer,admin);
    	//c(writer,manager1);
    	//c(writer,manager2);
    	//==============================================
    	//==============================================
    	//==============================================
    	//==============================================
    	createTableCaption(writer, discountDao.getTableName());
    	Discount discount10 = discountDao.create(100, 2,  true); 
    	Discount discount20 = discountDao.create(200, 3,  false); 
    	Discount discount50 = discountDao.create(500, 5,  true); 
    	Discount discount90 = discountDao.create(900, 20, true); 
    	//==============================================
    	//==============================================
    	createTableCaption(writer, clientDao.getTableName());
    	Client client1 = clientDao.create("John", "Smith", "AB123456789", "d1", "", "", "client1");
    	Client client2 = clientDao.create("Piter", "Pan",  "AB999999999", "d2", "", "", "client2");
    	Client client3 = clientDao.create("David", "O'Reylly", "AB77777777", "d3", "", "", "client3");
    	//WARNING NON-ASCI SYMBOLS FOR TESTING DATABASE ENCODING
    	char c = 0x424;
    	Client client4 = clientDao.create("name", "fam", "AB77777777",String.valueOf(c), "", "", "client3");
    	//==============================================
    	//==============================================
    	createTableCaption(writer, tourDao.getTableName());
    	Tour tour1 = tourDao.create("Sunny Italy", TransportKind.AVIA, TravelKind.REST, "Ultra cool travel to Italy", "PASSPORT, EU VISA", 15);
    	Tour tour2 = tourDao.create("Drunk Prague", TransportKind.BUS, TravelKind.TOUR, "Beer cruise to prague", "PASSPORT, EU VISA", 4);
    	Tour tour3 = tourDao.create("Big Warsaw", TransportKind.BUS, TravelKind.SHOPPING, "Shopping in Warsaw", "PASSPORT, EU VISA", 3);
    	Tour tour4 = tourDao.create("Quiet Belarus", TransportKind.TRAIN, TravelKind.TOUR, "Explore most democratic country in Europe", "PASSPORT, Belarussian VISA", 20);
    	//==============================================
    	//==============================================    	
    	createTableCaption(writer, arrivalSheduleDao.getTableName());
    	TourShedule arrivalShedule1  = arrivalSheduleDao.create(tour1, new Date(2013 - 1900, 0, 2),  200, 15);
    	TourShedule arrivalShedule21 = arrivalSheduleDao.create(tour2, new Date(2013 - 1900, 1, 5),  70, 15);
    	TourShedule arrivalShedule22 = arrivalSheduleDao.create(tour2, new Date(2013 - 1900, 2, 7),  (75), 15);
    	TourShedule arrivalShedule31 = arrivalSheduleDao.create(tour3, new Date(2013 - 1900, 3, 25), (100), 15);
    	TourShedule arrivalShedule32 = arrivalSheduleDao.create(tour3, new Date(2013 - 1900, 4, 7),  (110), 14);
    	TourShedule arrivalShedule33 = arrivalSheduleDao.create(tour3, new Date(2013 - 1900, 5, 7),  (120), 15);
    	TourShedule arrivalShedule4  = arrivalSheduleDao.create(tour4, new Date(2013 - 1900, 5, 7),  (50), 15);

    	//==============================================
    	//==============================================
    	BigDecimal two = BigDecimal.valueOf(2);
    	BigDecimal three = BigDecimal.valueOf(3);
    	createTableCaption(writer, orderDao.getTableName());
    	Order order11 = orderDao.create(client1, arrivalShedule1, manager1, new Date(2012 - 1900,11,30), 2, arrivalShedule1.getPrice()*2, "", false, null);
    	Order order12 = orderDao.create(client1, arrivalShedule21, manager1, new Date(2012 - 1900,11,30), 2, arrivalShedule21.getPrice()*2, "", false, null);
    	Order order13 = orderDao.create(client1, arrivalShedule32, manager2, new Date(2012 - 1900,11,30), 3, arrivalShedule32.getPrice()*3, "", false, null);
    	
    	Order order21 = orderDao.create(client2, arrivalShedule22, manager1, new Date(2012 - 1900,11,30), 1, arrivalShedule22.getPrice(), "", false, null);
    	Order order22 = orderDao.create(client2, arrivalShedule31, manager1, new Date(2012 - 1900,11,30), 2, arrivalShedule31.getPrice()*2, "", false, null);

    	Order order3 = orderDao.create(client3, arrivalShedule4, manager2, new Date(2012 - 1900,11,30), 1, arrivalShedule4.getPrice(), "", false, null);
    	//==============================================
    	//==============================================
    	createTableCaption(writer, paymentDao.getTableName());
    	Date nowDate = new Date(new java.util.Date().getTime());
    	Payment payment11 = paymentDao.create(order11, order11.getTotalPrice(), nowDate);
    	Payment payment13 = paymentDao.create(order13, order13.getTotalPrice(), nowDate);

    	Payment payment21_1 = paymentDao.create(order21, order21.getTotalPrice()/2,  nowDate);
    	Payment payment21_2 = paymentDao.create(order21, order21.getTotalPrice()/2,  nowDate);
    	
    	Payment payment3 = paymentDao.create(order3, order3.getTotalPrice(), nowDate);
    	ConnectionManager.closeSession();
    }
}
