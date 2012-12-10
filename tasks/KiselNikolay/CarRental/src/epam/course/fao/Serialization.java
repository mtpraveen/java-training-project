package epam.course.fao;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import epam.course.domain.Car;
import epam.course.domain.Customer;
import epam.course.domain.Order;

public class Serialization {

	static File fw = new File("car.txt");
	
	public static void serializeCar(String fileName, List<Car> list) {
		File fw=new File(fileName);
		try {
			ObjectOutputStream ostream=new ObjectOutputStream( 
					new FileOutputStream(fileName));
			for (Car ob : list) {
				ostream.writeObject(ob);
			}
			
			ostream.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public static void serializeCustomer(String fileName, List<Customer> list) {
		File fw=new File(fileName);
		try {
			ObjectOutputStream ostream=new ObjectOutputStream( 
					new FileOutputStream(fileName));
			for (Customer ob : list) {
				ostream.writeObject(ob);
			}
			
			ostream.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public static void serializeOrder(String fileName, List<Order> list) {
		File fw=new File(fileName);
		try {
			ObjectOutputStream ostream=new ObjectOutputStream( 
					new FileOutputStream(fileName));
			for (Order ob : list) {
				ostream.writeObject(ob);
			}
			
			ostream.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	/*public static void serialize(String fileName, Object ob) {
		final boolean exists = fw.exists();
		try {
			ObjectOutputStream ostream = new ObjectOutputStream(
					new FileOutputStream(fw, true)) {
				
				@Override
				protected void writeStreamHeader() throws IOException {
					if (!exists)
						super.writeStreamHeader();
				}
			};
			ostream.writeObject(ob);
			ostream.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}*/

	public static List<Car> deserializeCar(String fileName) {
		Car ob = null;
		ObjectInputStream istream = null;
		List<Car> list=new ArrayList<Car>();
		try {
			istream = new ObjectInputStream(
					new FileInputStream(fileName));
			for (;;) {
				ob = (Car) istream.readObject();
				list.add(ob);
				
			} 
			
		} catch (EOFException e) {
			
		} catch (IOException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
		try {
			istream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<Customer> deserializeCustomer(String fileName) {
		Customer ob = null;
		ObjectInputStream istream = null;
		List<Customer> list=new ArrayList<Customer>();
		try {
			istream = new ObjectInputStream(
					new FileInputStream(fileName));
			for (;;) {
				ob = (Customer) istream.readObject();
				list.add(ob);
				
			} 
			
		} catch (EOFException e) {
			
		} catch (IOException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
		try {
			istream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<Order> deserializeOrder(String fileName) {
		Order ob = null;
		ObjectInputStream istream = null;
		List<Order> list=new ArrayList<Order>();
		try {
			istream = new ObjectInputStream(
					new FileInputStream(fileName));
			for (;;) {
				ob = (Order) istream.readObject();
				list.add(ob);
				
			} 
			
		} catch (EOFException e) {
			
		} catch (IOException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
		try {
			istream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
}
