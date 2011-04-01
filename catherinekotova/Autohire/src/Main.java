
public class Main {
 
	

	private static int sum,n;

	public static void main(String[] args)  { 
		Admin admin= new Admin(78); 
		Autobase autobase1 = new Autobase();	
		autobase1.autobase();
		
		Client c1 = new Client("petrov ","his data");
	    c1.identity();
		n=c1.choice();
		//hiring period
		Order o1 = new Order(10);
		o1.ordering();
		 if (admin.desision(true)){
		 autobase1.price(n);
		 sum=o1.getDa()*autobase1.price(n);
		 
		 System.out.println(sum);
		 if(c1.pay(true)){
			 c1.using();
			 if (c1.damage(true)){
				 admin.notes();
			 }
		 }
				 
		 }
	}
}

