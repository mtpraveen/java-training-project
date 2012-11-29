package zip;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import position.Person;

public class ZipWriter {
	ZipOutputStream zout = null;

	public ZipWriter(String zipName) throws FileNotFoundException {
		this.zout = new ZipOutputStream(new FileOutputStream(zipName));
	}

	public void writeData(ArrayList<ArrayList<? extends Person>> allPersons)
			throws IOException {
		int i=0;
		String fileNames[] = { "Patient.csv", "Doctor.csv", "Nurse.csv" };
		for (String fileName : fileNames) {
				ZipEntry ze = new ZipEntry(fileName);
				zout.putNextEntry(ze);
				CsvWriter writer = new CsvWriter(zout, fileName);
				writer.writeFile(allPersons.get(i));
				zout.closeEntry();
				i++;
		}
	}
	public void close() throws IOException{
		zout.close();
	}
}
