package filesproject;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ReadDir {
	private static void compressTextFiles(String dirName) {
		File dir = new File(dirName);
		if (dir.exists()) {
			try {
				File[] files = dir.listFiles(new FileFilter() {
					
					@Override
					public boolean accept(File file) {
						if (file.isFile() && isTextFile(file.getName()))
							return true;
						return false;
					}

					private boolean isTextFile(String filename) {
						String []extensions = new String[]{"txt", "html", "htm", "xml", "csv"};
						
						String fileExt = filename.substring(filename.length() - 3).toLowerCase();
						for(String ext: extensions) {
							if (fileExt.equals(ext))
								return true;	
						}
						return false;
					}
				});		

				ZipOutputStream zout = new ZipOutputStream(
											new FileOutputStream("output.zip"));
				ZipEntry ze = new ZipEntry("data.txt");
				zout.putNextEntry(ze);
		
				for(File file: files) {
					Reader reader = new InputStreamReader(
					        new FileInputStream(file));
					int ch = 0;
					while ((ch = reader.read()) >= 0){ 
						zout.write(ch);
					}
					reader.close();
				}
				
				zout.closeEntry();
				zout.close();
			}
			catch (Exception e) {
				System.err.println(e.getMessage());
			}
		} else
			System.out.println("Directory "+dir.getName()+"don't exists");
	}
	
	public static void main(String []args) {
		if (args.length > 0 && new File(args[0]).exists())
			compressTextFiles(args[0]);
		else {
			// test directory located in the project folder
			compressTextFiles("dir");		
		}
	}
}
