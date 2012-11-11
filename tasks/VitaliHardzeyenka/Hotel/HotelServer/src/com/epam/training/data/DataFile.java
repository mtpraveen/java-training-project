package com.epam.training.data;

import java.io.File;

public class DataFile {
	public File file; // text file

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
