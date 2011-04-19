package periodicals;
import java.util.*;
public class PeriodicalSystem {
	//MAIN METHOD
	public static void main(String[] args) {
	
		Reader dr = new Reader("Vova.Sidorov");
		 List<Paper> m1 = new LinkedList<Paper>();
		 m1.add(new Paper("Vecherniy Brest", 1.0));
		 m1.add(new Paper("Brestskiy Kourier",0.5));
		 m1.add(new Paper("Zarya",0.3));
		dr.setReaderPapers(m1);
		Order ord = new Order();
		ord.setReaderOrder(dr);
		ord.setListOrder(m1);
		ord.setSumOrder(dr.priceSelectedPapers(m1));
		@SuppressWarnings("deprecation")
		Date date1=new Date(2011,4,8);
		@SuppressWarnings("deprecation")
		Date date2=dr.pay(new Date(2011,4,12));
		ord.setDateOrderWasPaid(date1);
		ord.setDatetoPayOrder(date2);
		
		Admin a1=new Admin("Pupkin Vasya");
		a1.getOrder(ord);
	}
}
//Paper Class
class Paper {
	private String name;
	private double price;
	public Paper(String name, double price) {
		this.name = name;
		this.price = price;
	}
	
	public String toString(){
		return name+" "+ price+"$";
	}
	//Getters & Setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}
//Admin Class
class Admin{
	private String name;
	private Order order=null;
	public Admin(String name){
		this.name=name;
	}
	public void getOrder(Order order){
		this.order=order;
		if (order.getDateOrderWasPaid().before(order.getDatetoPayOrder()))
			this.giveOrder(order);
		else this.toBlackList(order);
			
	}
	private void giveOrder(Order order){
		System.out.println("Admin "+" give  this order!");
	}
	private void toBlackList(Order order){
		System.out.println("Admin "+" send to Black List  this order!");
	}
}
//Reader Class
class Reader{
	private String name;
	private List<Paper> readerPapers=null;
	//constructor
	public Reader(String name) {
		this.name = name;
		System.out.println("\nThe Reader "+name+" was authorization!");
	}
	//methods
	
	public double priceSelectedPapers(List<Paper> readerPapers) {
		double totalPrice=0.0;
		if (readerPapers==null) return 0.0;
		for (Paper paper:readerPapers)			
			totalPrice+=paper.getPrice();
		return totalPrice;
	}
	
	public Date pay(Date date){
		return date;
	}
	
	
	//Getters & Setters
	public List<Paper> getReaderPapers() {
		return readerPapers;
	}
	public void setReaderPapers(List<Paper> readerPapers) {
		this.readerPapers = readerPapers;
	}
	public String getName() {
		return name;
	}
	
	
}

class Order{
	private Reader readerOrder=null;
	private List<Paper> listOrder=null;
	private double sumOrder=0.0;
	private Date dateOrderWasPaid=null;
	private Date datetoPayOrder=null;
	Order(){
		}
	/**
	 * @param listOrder
	 * @param sumOrder
	 * @param dateOrder
	 * @param datetoPayOrder
	 */
	Order(List<Paper> listOrder, double sumOrder, Date dateOrder,
			Date datetoPayOrder) {
		this.listOrder = listOrder;
		this.sumOrder = sumOrder;
		this.dateOrderWasPaid = dateOrder;
		this.datetoPayOrder = datetoPayOrder;
	}
	
	
	
	@Override
	public String toString() {
		return "Order [listOrder=" + listOrder + ", sumOrder=" + sumOrder
				+ ", dateOrder=" + dateOrderWasPaid + ", datetoPayOrder="
				+ datetoPayOrder + "]";
	}
	
	//Getters & Setters
	
	public Reader getReaderOrder() {
		return readerOrder;
	}
	public void setReaderOrder(Reader readerOrder) {
		this.readerOrder = readerOrder;
	}
	public List<Paper> getListOrder() {
		return listOrder;
	}
	public void setListOrder(List<Paper> listOrder) {
		this.listOrder = listOrder;
	}
	public double getSumOrder() {
		return sumOrder;
	}
	public void setSumOrder(double sumOrder) {
		this.sumOrder = sumOrder;
	}
	public Date getDateOrderWasPaid() {
		return dateOrderWasPaid;
	}
	public void setDateOrderWasPaid(Date dateOrderWasPaid) {
		this.dateOrderWasPaid = dateOrderWasPaid;
	}
	public Date getDatetoPayOrder() {
		return datetoPayOrder;
	}
	public void setDatetoPayOrder(Date datetoPayOrder) {
		this.datetoPayOrder = datetoPayOrder;
	}
}
