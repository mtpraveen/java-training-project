package com.epam.logic;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.SimpleLayout;

/**
 * @author Gordeenko_XP
 */
public class Logger {
	
    private org.apache.log4j.Logger exceptionTextFileLogger; // logger for text file
    private FileAppender textFileAppender;

    /**
    * Constructor.
    * @param logger logger for text and console appender.
    */
    public Logger(org.apache.log4j.Logger logger) {
        this.exceptionTextFileLogger = logger;

        this.exceptionTextFileLogger.setLevel(Level.DEBUG);

        // Create text file appender.
        try {
            if ((this.textFileAppender = new FileAppender(new SimpleLayout(), 
            		System.getProperty("user.dir") + String.format("\\log\\%s", "exeptionLog.txt"))) == null) {
                throw new FileNotFoundException();
            }
        } catch (FileNotFoundException exception) {
            System.out.println("There are not found file /log/exeptionLog.txt");
        } catch (IOException exception) {
            System.out.println("Unknown exeption in Logger");
        }

        // Add appenders to the logger.
        this.exceptionTextFileLogger.addAppender(this.textFileAppender);
    }

    public org.apache.log4j.Logger getExceptionTextFileLogger() {
        return exceptionTextFileLogger;
    }

}
