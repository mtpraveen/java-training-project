package com.epam.training.data;

/**
 * This class describe find diretory to the needed file.
 * @author Gordeenko
 */
public class FileFinder {

    /**
     * Return full path of the specified file name.
     * All information files are in the "data" directory in the root of application.
     * @param fileName name of file which path will be find.
     * @return file directory as string
     */
    public static String findFilePath(String fileName) {
        return System.getProperty("user.dir") + String.format("\\data\\%s", fileName);
    }
    
    /**
     * Find parent folder of specified file.
     * @param absolutePath path to file.
     * @return parent folder as string
     */
    public static String findFileParentFolder(String absolutePath) {
    	String parentFolder = null;
    	int index = absolutePath.lastIndexOf("\\");
    	parentFolder = absolutePath.substring(0, index + 1);
    	return parentFolder;
    }

}
