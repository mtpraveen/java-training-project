package epam.course.fao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class Packing {

	private static File destFile;
	public static final int BUFFER = 2048;

	public static void unpack(String nameZip) {
		File zipFile = new File(nameZip);
		try {
			// открытие zip-архива для чтения
			ZipFile zFile = new ZipFile(zipFile);
			Enumeration zipFileEntries = zFile.entries();
			while (zipFileEntries.hasMoreElements()) {
				// извлечение текущей записи из архива
				ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();
				String entryname = entry.getName();
				System.out.println("Extracting: " + entry);
				destFile = new File(entryname);
				writeFile(zFile, entry);
			}
			zFile.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	private static void writeFile(ZipFile zFile, ZipEntry entry)
			throws IOException {
		BufferedInputStream is = new BufferedInputStream(
				zFile.getInputStream(entry));
		int currentByte;
		byte data[] = new byte[BUFFER];
		// запись файла на диск
		BufferedOutputStream dest = new BufferedOutputStream(
				new FileOutputStream(destFile), BUFFER);
		while ((currentByte = is.read(data, 0, BUFFER)) > 0) {
			dest.write(data, 0, currentByte);
		}
		dest.flush();
		dest.close();
		is.close();
	}

	public static void pack(String[] filesToZip, String zipFileName) {
		try {
			byte[] buffer = new byte[BUFFER];
			ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(
					zipFileName));
			zip.setLevel(Deflater.DEFAULT_COMPRESSION);
			for (int i = 0; i < filesToZip.length; i++) {
				//System.out.println(i);
				zip.putNextEntry(new ZipEntry(filesToZip[i]));
				FileInputStream in = new FileInputStream(filesToZip[i]);
				int ch;
				while ((ch = in.read(buffer)) > 0)
					zip.write(buffer, 0, ch);
				zip.closeEntry();
				in.close();
			}
			zip.close();
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
