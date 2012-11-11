package com.epam.training.logic;

import java.io.IOException;
import org.apache.log4j.*;

public class Logger {
	
	private org.apache.log4j.Logger exeptionsLogger; // text logger for exceptions
	private FileAppender textFileAppender; // text file appender used simple layout
	
	/**
	 * Constructor.
	 * @param logger
	 */
	public Logger(org.apache.log4j.Logger logger) {
		this.exeptionsLogger = logger;
		try {
			this.textFileAppender = new FileAppender(new SimpleLayout(), System.getProperty("user.dir") + String.format("\\log\\%s", "exeptionLog.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.exeptionsLogger.addAppender(this.textFileAppender);
	}

	public org.apache.log4j.Logger getExeptionsLogger() {
		return exeptionsLogger;
	}
}
