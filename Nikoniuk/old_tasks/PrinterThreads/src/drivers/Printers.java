package drivers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

class FilePrinter extends Thread {
	private static PrintStream output;
	private String data;
	public FilePrinter() {
		try {
			if (output == null) 
				output = new PrintStream(
							new FileOutputStream(
								new File("output.txt")));
		} catch (FileNotFoundException e) {
			System.err.println(e);
		}
	}
	
	final public synchronized void run() {
		try {
			System.out.println("Printing " + data + " to File");
			Thread.sleep(Math.round(Math.random() * 1000));
			output.write((data + "\n").getBytes());
		} catch (IOException e) {
			System.err.println(e);
		} catch (InterruptedException e) {
			System.err.println(e);
		}
	}

	final public synchronized void print(String data) {
		this.data = data;
		this.start();
	}
}

class ConsolePrinter extends Thread {
	private static PrintStream output;
	private String data;
	
	public ConsolePrinter() {
		if (output == null)
			output = System.out;
	}
	
	final public synchronized void run() {
		try {
			System.out.println("Printing " + data + " to Console");
			Thread.sleep(Math.round(Math.random() * 1000));
			output.write((data + "\n").getBytes());
		} catch (IOException e) {
			System.err.println(e);
		} catch (InterruptedException e) {
			System.err.println(e);
		}
	}

	final public synchronized void print(String data) {
		this.data = data;
		this.start();
	}
}

public class Printers {

	public static void main(String[] args) {
		ConsolePrinter consolePrinter = null;
		FilePrinter filePrinter = null;

		for (int i = 1; i <= 10; i++) {
			final int taskNumber = i;

			try {
				if (filePrinter == null || 
						!filePrinter.isAlive()) {
					filePrinter = new FilePrinter();
					filePrinter.print("task " + taskNumber);
				} else if (consolePrinter == null ||
							!consolePrinter.isAlive()) {
					consolePrinter = new ConsolePrinter();
					consolePrinter.print("task " + taskNumber);
				} else {
					consolePrinter.join();
					consolePrinter = new ConsolePrinter();
					consolePrinter.print("task " + taskNumber);
				}
			} catch (InterruptedException e) {
				System.err.println(e);
				;
			}
		}
	}
}
