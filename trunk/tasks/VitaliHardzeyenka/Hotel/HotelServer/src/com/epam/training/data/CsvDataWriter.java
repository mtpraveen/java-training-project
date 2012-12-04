package com.epam.training.data;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

import com.epam.training.logic.Logger;

/**
 * Write string lines to specified text files.
 * @author EXHUMLOKI
 *
 */
public class CsvDataWriter extends DataFile {

	private final Logger logger = new Logger(org.apache.log4j.Logger.getLogger(CsvDataReader.class.getName()));
	
	/**
	 * Constructor.
	 * @param path
	 */
	public CsvDataWriter(String path) {
		super(path);
	}
	
	/**
	 * Write one string into specified text file. 
	 * @param data
	 */
	public void write(String data) {
		try {
			FileUtils.writeStringToFile(super.getFile(), data);
		} catch (IOException e) {
			logger.getExeptionsLogger().error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Write lines collection into specified text file.
	 * @param dateLines
	 */
	public void write(ArrayList<String> dateLines) {
		try {
			FileUtils.writeLines(super.getFile(), dateLines);
		} catch (IOException e) {
			logger.getExeptionsLogger().error(e.getMessage());
			e.printStackTrace();
		}
	}
}
