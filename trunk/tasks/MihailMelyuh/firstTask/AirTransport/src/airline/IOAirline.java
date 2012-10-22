/**
 * 
 */
package airline;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author Mihail
 * 
 */
public abstract class IOAirline {

	public static void saveData(Airline obj, String fileName) throws IOException {
		FileOutputStream fos = new FileOutputStream(fileName);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(obj);
		oos.flush();
		oos.close();
	}

	public static Airline readData(String fileName) throws IOException,
			ClassNotFoundException {
		FileInputStream fis = new FileInputStream(fileName);
		ObjectInputStream oin = new ObjectInputStream(fis);
		Airline obj = (Airline) oin.readObject();
		oin.close();
		return obj;
	}
}
