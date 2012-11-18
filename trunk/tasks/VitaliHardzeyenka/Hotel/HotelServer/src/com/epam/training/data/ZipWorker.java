package com.epam.training.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import com.epam.training.logic.Logger;

public class ZipWorker {
	
	private static ZipOutputStream zipOutputStream;
	private static ZipInputStream zipInputStream;
	private static ZipEntry zipEntry;
	private static FileOutputStream fileOutputStream;
	private static FileInputStream fileInputStream;
	private static Logger logger = new Logger(org.apache.log4j.Logger.getLogger(ZipWorker.class.getName()));
	
	/**
	 * Add files to archive .zip in the same with files directory.
	 * @param files files would be add to archive
	 * @param zipName name of future archive
	 */
	public static void addFilesToZip(File[] files, String zipName) {
		byte[] buffer = new byte[1024]; // buffer for copying data from file to archive file

		// Create new file in archive and copy data to it.
    	try {
    		fileOutputStream = new FileOutputStream(zipName);
    		zipOutputStream = new ZipOutputStream(fileOutputStream);
    		
    		for (File file : files) {
    			zipEntry = new ZipEntry(file.getName());
    			zipOutputStream.putNextEntry(zipEntry);
    			
    			fileInputStream = new FileInputStream(file.getAbsolutePath());
    			int length;
    			while ((length = fileInputStream.read(buffer)) > 0) {
    				zipOutputStream.write(buffer, 0, length);
    			}
    		}
           
            fileInputStream.close();
            zipOutputStream.closeEntry();
            zipOutputStream.close();
    	} catch(IOException exception) {
    		logger.getExeptionsLogger().error(exception);
    	}
	}
	
	/**
	 * Delete specified file.
	 * @param file file that will be deleted.
	 * @return true if file has been deleted, false if not.
	 */
	public static boolean deleteFile(File file) {
		if (!file.isDirectory()) {
			if (file.isFile()) {
				if (file.exists()) {
					file.delete();
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Unzip .zip file, copy all inside files in the same directory.
	 * @param zipFile file that will bee inzip.
	 */
	public static void unzipFile(File zipFile) {
		Scanner scanner = null;
		
		try {
			zipInputStream = new ZipInputStream(new FileInputStream(zipFile)); // for reading from zip
			
			while ((zipEntry = zipInputStream.getNextEntry()) != null) { // get next file from .zip
				scanner = new Scanner(zipInputStream); // for reading from file
				fileOutputStream = new FileOutputStream(FileFinder.findFileParentFolder(zipFile.getAbsolutePath()) + zipEntry.getName()); // create new file
				while (scanner.hasNextLine()) {
					fileOutputStream.write(scanner.nextLine().getBytes());
					fileOutputStream.write("\n".getBytes());
				}
				
				fileOutputStream.close();
				zipInputStream.closeEntry();
			}
			
			zipInputStream.close();
		} catch (IOException exception) {
			logger.getExeptionsLogger().error(exception);
		}
	}
	
	/**
	 * Delete folder if it is empty; else delete all
	 * subfolders and files in they first, then delete 
	 * parent folder. 
	 * @param directory folder will be deleted.
	 * @return
	 */
	public static boolean deleteFolder(String directory) {
		// TODO write recursion for deleting all subfolders and 
		// files in specified folder.
		File deletingFile = new File(directory);
		if (deletingFile.isDirectory()) {
			String[] files = deletingFile.list();
			if (files.length == 0) {
				deletingFile.delete();
				return true;
			} else {
				for (String fileName : files) {
					File file = new File(directory +  "\\" + fileName);
					file.delete();
				}
				deletingFile.delete();
				return true;
			}
		}		
		return false;
	}
	
	/**
	 * Check is .zip contains all need files and
	 * they are not empty.
	 * @return
	 */
	public static boolean checkFiles(File zipFile, File[] files) {
		int filesCount = 0;
		try {
			zipInputStream = new ZipInputStream(new FileInputStream(zipFile));
			if (zipFile.exists()) {
				if (zipFile.isFile()) {
					while ((zipEntry = zipInputStream.getNextEntry()) != null) {
						for (File file : files) {
							if (file.getName().equals(zipEntry.getName())) {
								if (zipEntry.getSize() != 0) {
									filesCount++;
									break; // exit from "for" cycle not from "while"
								}
							}
						}
					}
				}
			}
			
			if (filesCount == files.length) {
				return true;
			}
		} catch (FileNotFoundException exception) {
			logger.getExeptionsLogger().error(exception);
		} catch (IOException exception) {
			logger.getExeptionsLogger().error(exception);
		}
		return false;
	}
}
