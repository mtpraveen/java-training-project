
public class Main {
 
	

	
	private static Client  client;
	private static Order order;
	private static Autobase autobase;
	private static Admin admin;
	
	
	
	public static Admin adminCreator() {
		admin=new Admin();
		return admin;
		}
	public static Autobase autobaseCreator() {
		 autobase=new Autobase();
		return autobase;
		}
	public static Client clientCreator() {
		 client=new Client();
		return client;
		}
	public static Order orderCreator() {
		 order=new Order();
		return order;
		}
	public static String way() {
		String way = "D:\\Clients";
		return way;
		}

	public static void main(String[] args)  {
		
		
		
		UnpackJar.unpack("D:\\",way()+".zip");
		
		admin=new Admin();
		autobase=new Autobase();
		autobase.autobase();
		client=new Client();
		order=new Order();
		order.order();
		client.identity();
		
		}
}


