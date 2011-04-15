
import java.io.*;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class UnpackJar {
	private static File destFile;
	// размер буфера для распаковки
	public final static int BUFFER = 2048;

  public static void unpack(String destinationDirectory, 
							String nameJar) {
		File sourceJarFile = new File(nameJar);
		try {
		File unzipDestinationDirectory = 
new File(destinationDirectory);
			// открытие zip-архива для чтения
		JarFile jFile = new JarFile(sourceJarFile);
		Enumeration jarFileEntries = jFile.entries();
		while (jarFileEntries.hasMoreElements()) {
			// извлечение текущей записи из архива
		JarEntry entry = 
(JarEntry) jarFileEntries.nextElement();

		String entryname = entry.getName();
			//entryname = entryname.substring(2);
		
			destFile = 
new File(unzipDestinationDirectory, entryname);
			// определение каталога
			File destinationParent = 
destFile.getParentFile();
			// создание структуры каталогов
				destinationParent.mkdirs();
			// распаковывание записи, если она не каталог
				if (!entry.isDirectory()) {
					writeFile(jFile, entry);
				}
			}
			jFile.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

  private static void writeFile(JarFile jFile, JarEntry entry)
  throws IOException {
 		BufferedInputStream is = 
 new BufferedInputStream(
 jFile	.getInputStream(entry));
 		int currentByte;
 		byte data[] = new byte[BUFFER];
 		// запись файла на диск
 		BufferedOutputStream dest = 
 new BufferedOutputStream(
 new FileOutputStream(destFile), BUFFER);

 	while ((currentByte = is.read(data, 0, BUFFER)) > 0){
 			dest.write(data, 0, currentByte);
 		}
 		dest.flush();
 		dest.close();
 		is.close();
 	}



}
