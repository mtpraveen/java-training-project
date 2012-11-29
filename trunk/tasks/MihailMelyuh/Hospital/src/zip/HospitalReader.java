package zip;

import hospital.Hospital;
import hospital.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class HospitalReader {
	public Hospital readData(String zipName) throws IOException,
			ClassNotFoundException {
		ZipInputStream zin = new ZipInputStream(new FileInputStream(zipName));
		ZipEntry entry;
		Hospital obj = new Hospital();
		while ((entry = zin.getNextEntry()) != null) {
			ObjectInputStream oin = new ObjectInputStream(zin);
			obj = (Hospital) oin.readObject();
			zin.closeEntry();
		}
		zin.close();
		return obj;
	}
	
	public ArrayList<User> readUsers (String fileName) throws IOException, ClassNotFoundException{
		ArrayList<User> newUsers = new ArrayList<User>();
		FileInputStream fis = new FileInputStream(fileName);
		ObjectInputStream oin = new ObjectInputStream(fis);
		newUsers = (ArrayList<User>) oin.readObject();
		oin.close();
		return newUsers;
	}
}
