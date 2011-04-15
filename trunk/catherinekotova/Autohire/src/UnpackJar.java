
import java.io.*;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class UnpackJar {
	private static File destFile;
	// ������ ������ ��� ����������
	public final static int BUFFER = 2048;

  public static void unpack(String destinationDirectory, 
							String nameJar) {
		File sourceJarFile = new File(nameJar);
		try {
		File unzipDestinationDirectory = 
new File(destinationDirectory);
			// �������� zip-������ ��� ������
		JarFile jFile = new JarFile(sourceJarFile);
		Enumeration jarFileEntries = jFile.entries();
		while (jarFileEntries.hasMoreElements()) {
			// ���������� ������� ������ �� ������
		JarEntry entry = 
(JarEntry) jarFileEntries.nextElement();

		String entryname = entry.getName();
			//entryname = entryname.substring(2);
		
			destFile = 
new File(unzipDestinationDirectory, entryname);
			// ����������� ��������
			File destinationParent = 
destFile.getParentFile();
			// �������� ��������� ���������
				destinationParent.mkdirs();
			// �������������� ������, ���� ��� �� �������
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
 		// ������ ����� �� ����
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
