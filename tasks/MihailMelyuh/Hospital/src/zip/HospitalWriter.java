package zip;

import hospital.Hospital;
import hospital.User;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class HospitalWriter {
	public void saveData(Hospital obj, String zipName) throws IOException {
		ZipOutputStream zout = new ZipOutputStream(
				new FileOutputStream(zipName));
		ZipEntry ze = new ZipEntry("Hospital.bin");
		zout.putNextEntry(ze);
		ObjectOutputStream oos = new ObjectOutputStream(zout);
		oos.writeObject(obj);
		oos.flush();
		zout.closeEntry();
		zout.close();
	}
	
	public void saveUsers(ArrayList<User> users, String fileName) throws IOException {
		FileOutputStream fos = new FileOutputStream(fileName);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(users);
		oos.flush();
		oos.close();
	}
}
