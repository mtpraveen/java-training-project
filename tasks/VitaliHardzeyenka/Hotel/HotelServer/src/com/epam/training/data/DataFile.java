package com.epam.training.data;

import java.io.File;

/**
 * This class contains definition text file, path to it.
 * @author Gordeenko
 *
 */
public class DataFile {
	public File file; // text file

	/**
	 * Constructor.
	 * @param path file directory.
	 */
	public DataFile(String path) {
		this.file = new File(path);
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}   
}
