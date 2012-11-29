package zip;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import position.Person;

public class ZipReader {
	ZipInputStream zin = null;

	public ZipReader(String zipName) throws FileNotFoundException {
		this.zin = new ZipInputStream(new FileInputStream(zipName));
	}

	public ArrayList<Person> readData(String fileName) throws IOException {
		ArrayList<Person> persons = null;
		ZipEntry entry;
		boolean isFileExist=false;
		int fileIndex=4;
		String fileNames[] = { "Patient.csv", "Doctor.csv", "Nurse.csv" };
		for (int i=0;i<4;i++){
			fileIndex=i;
			if(fileName.equals(fileNames[i])){
				break;
			}
		}
		while ((entry = zin.getNextEntry()) != null) {
			if (fileName.equals(entry.getName())) {
				CsvReader reader = new CsvReader(zin);
				persons = reader.readFile(fileIndex);
				isFileExist=true;
				zin.closeEntry();
			}
		}
		if(!isFileExist){
			throw new FileNotFoundException();
		}
		return persons;
	}

	public void close() throws IOException {
		zin.close();
	}
}
