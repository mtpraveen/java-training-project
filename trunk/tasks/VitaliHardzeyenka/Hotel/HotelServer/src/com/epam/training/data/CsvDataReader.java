package com.epam.training.data;

import java.io.FileReader;
import java.io.IOException;

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
     * Read information from file.
     * @return information from file as string.
     */
    public String read() {
        StringBuilder fileReadString = new StringBuilder(); // return
        int currentChar; // current reading char from file

        if (super.getFile().exists()) { // if file exist
            if (super.getFile().isFile()) { // if it is file
                try {
                    FileReader fileReader = new FileReader(super.getFile());
                    while ((currentChar = fileReader.read()) != -1) {
                        fileReadString.append((char) currentChar); // add char to return string
                    }
                } catch (IOException e) {
                	// logger
                    System.err.println("Reading file error: " + e);
                }
            }
        }

        return fileReadString.toString();
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
