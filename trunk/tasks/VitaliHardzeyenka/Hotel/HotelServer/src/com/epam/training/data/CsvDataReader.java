package com.epam.training.data;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

public class CsvDataReader extends DataFile {
	
	 /**
     * Constructor
     * @param path - path to the directory of existing.
     */
    public CsvDataReader(String path) {
       super(path);
    }

    /**
     * Read whole text file line by line.
     * @return list of strings.
     */
    public ArrayList<String> read() {

        if (super.getFile().exists()) { // if file exist
            if (super.getFile().isFile()) { // if it is file
                try {
                	// read line by line
                	return (ArrayList<String>) FileUtils.readLines(super.getFile()); 
                } catch (IOException e) {
                	// logger
                    System.err.println("Reading file error: " + e);
                }
            }
        }
        
		return null;
    }
    

    /**
     * Read certain line from file.
     * @param number number of reading line.
     * @return reading string from file.
     */
    public String read(int number) {
        StringBuilder fileReadString = new StringBuilder(); // return
        
         if (super.getFile().exists()) { // if file exist
            if (super.getFile().isFile()) { // if it is file
                try {
                    // read the certain line from file.
                    fileReadString.append((String) FileUtils.readLines(super.getFile()).get(number));
                } catch (IOException ex) {
                    //Logger.getLogger(TextFileReader.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return fileReadString.toString();
    }

}
