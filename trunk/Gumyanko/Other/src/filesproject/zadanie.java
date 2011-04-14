package filesproject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.util.zip.Deflater;

public class zadanie {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		File myDir = new File("L:\\1\\");
		if (myDir.exists() && myDir.isDirectory()) {
			System.out.println("каталог " + myDir.getName() + " существует");
			File fls[] = myDir.listFiles();
			try {

				FileWriter dataFile = new FileWriter("L:\\data.txt");
				int b, count = 0;
				for (int i = 0; i < fls.length; i++) {
					FileReader is = new FileReader(fls[i]);
					while ((b = is.read()) != -1) {/* чтение */
						System.out.print((char) b);
						count++;
						dataFile.write(b);
					}

				}
				dataFile.close();
			} catch (IOException e) {
				System.out.println("Ошибка записи: " + e.toString());
			}

			try {
				byte[] buffer = new byte[1024];
				ZipOutputStream jos = new ZipOutputStream(new FileOutputStream(
						"L:\\test.zip"));
				// метод сжатия
				jos.setLevel(Deflater.DEFAULT_COMPRESSION);
				jos.putNextEntry(new ZipEntry("L:\\data.txt"));

				FileInputStream in = new FileInputStream("L:\\data.txt");
				int len;
				while ((len = in.read(buffer)) > 0)
					jos.write(buffer, 0, len);
				jos.closeEntry();
				in.close();

				jos.close();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				System.err.println("Некорректный аргумент");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.err.println("Файл не найден");
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("Ошибка доступа");
			}
		}

	}

}
