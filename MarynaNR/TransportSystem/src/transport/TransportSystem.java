package transport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.io.*;

/**
 * 
 */

/**
 * @author Мара
 * @param trans contains list of transport existing in town
 * @param ways contains list of transport's Ways existing in town
 * @param breakages contains list of broken transports
 * @param assignment contains list of transports assigned to the specific date.
 * @param autoNumber how much buses do we need for a day 
 * @param trallNumber how much trolleybuses do we need for a day
 * @param tramNumber how much trams do we need for a day
 * @param delimiter is delimiter for convenient output.    
 */
public class TransportSystem {
	private static final String delimiter = ";";
	private int autoNumber = 0, tralNumber = 0, tramNumber = 0;
	private List<Transport> trans = new ArrayList<Transport>();
	private List<Way> ways = new ArrayList<Way>();
	private List<Breakage> breakages = new ArrayList<Breakage>();
	private List<Assignment> assignment = new ArrayList<Assignment>();

	public int getAutoNumber() {
		return autoNumber;
	}

	public void setAutoNumber(int autoNumber) {
		this.autoNumber = autoNumber;
	}

	public int getTralNumber() {
		return tralNumber;
	}

	public void setTralNumber(int tralNumber) {
		this.tralNumber = tralNumber;
	}

	public int getTramNumber() {
		return tramNumber;
	}

	public void setTramNumber(int tramNumber) {
		this.tramNumber = tramNumber;
	}

	/*public int getAutoWaysNumber() {
		return autoWaysNumber;
	}

	public void setAutoWaysNumber(int autoWaysNumber) {
		this.autoWaysNumber = autoWaysNumber;
	}

	public int getTralWaysNumber() {
		return tralWaysNumber;
	}

	public void setTralWaysNumber(int tralWaysNumber) {
		this.tralWaysNumber = tralWaysNumber;
	}

	public int getTramWaysNumber() {
		return tramWaysNumber;
	}

	public void setTramWaysNumber(int tramWaysNumber) {
		this.tramWaysNumber = tramWaysNumber;
	}*/

	
	
	/*
	 * create and fill structure List of Way according to the info read in file Ways.txt
	 * @param pathWays1 the path to the file Ways.txt (read from the "message.properties")
	 * @return void
	 * */
	private void addWays(String pathWays1) {
		pathWays1 = pathWays1 + "Ways2.txt";
		try {
			FileReader fr = new FileReader(pathWays1);
			Scanner scan = new Scanner(fr);
			while (scan.hasNext()) {
				Way w = new Way();
				if (scan.hasNextInt())
					w.setIdWays(scan.nextInt());
				if (scan.hasNextInt())
					w.setStopNumber(scan.nextInt());
				if (scan.hasNextInt())
					w.setTransNumber(scan.nextInt());
				w.setStopTime(w.getStopNumber() * 6 / w.getTransNumber());
				if (scan.hasNextInt())
					w.setTypeTrans(scan.nextInt());
				if (scan.hasNextInt())
					w.setWayNumber(scan.nextInt());
				if (scan.hasNext())
					w.setFirstStop(scan.next());
				if (scan.hasNext())
					w.setLastStop(scan.next());
				this.ways.add(w);

			}
			for (int u = 0; u < this.ways.size(); u++)
				System.out.println(this.ways.get(u).getFirstStop() + " - "
						+ this.ways.get(u).getLastStop() + " - "
						+ this.ways.get(u).getWayNumber());

			fr.close();
		} catch (IOException e) {
			System.out.println("Ошибка: 		" + e.toString());
		}
	}

	/*
	 * create and fill structure List of Transport according to the info read in file Transport.txt
	 * @param pathWays1 the path to the file Transport.txt (read from the "message.properties")
	 * @return void
	 * */
	private void addTrans(String pathWays1) {
		pathWays1 = pathWays1 + "Transport.txt";
		try {
			FileReader fr = new FileReader(pathWays1);
			Scanner scan = new Scanner(fr);
			while (scan.hasNext()) {
				Transport t = new Transport();
				if (scan.hasNextInt())
					t.setIdTrans(scan.nextInt());
				if (scan.hasNextInt())
					t.setTransType(scan.nextInt());
				if (scan.hasNext())
					t.setCarNumber(scan.next());

				this.trans.add(t);
			}
			fr.close();
		} catch (IOException e) {
			System.out.println("Ошибка: 		" + e.toString());
		}

	}
	/*
	 * create and fill structure List of Breakage according to the info read in file Breakage.txt
	 * @param pathWays1 the path to the file Breakage.txt (read from the "message.properties")
	 * @return void
	 * */
	private void addBreakage(String pathWays1) {
		pathWays1 = pathWays1 + "Breakage.txt";
		try {
			FileReader fr = new FileReader(pathWays1); // file must end with
														// Enter!!!!!
			Scanner scan = new Scanner(fr);
			while (scan.hasNext()) {
				Breakage b = new Breakage();
				if (scan.hasNextInt())
					b.setIdBreakage(scan.nextInt());
				if (scan.hasNextInt())
					b.setIdTrans(scan.nextInt());
				if (scan.hasNextInt())
					b.setIdWay(scan.nextInt());
				if (scan.hasNext())
					b.setDateBr(scan.next());
				scan.useDelimiter("\n");
				if (scan.hasNext(".*\\s"))
					b.setDescription(scan.next(".*\\s"));
				scan.reset();
				this.breakages.add(b);
			}

			for (int u = 0; u < this.breakages.size(); u++) {
				this.trans.get(this.breakages.get(u).getIdTrans() - 1)
						.setBreakage(1);// mark in List as broken
				System.out.print(this.breakages.get(u).getIdTrans() + " - "
						+ this.breakages.get(u).getDateBr()
						+ this.breakages.get(u).getDescription());
			}
			fr.close();
		} catch (IOException e) {
			System.out.println("Ошибка: 		" + e.toString());
		}
	}

	/*
	 * create and fill structure List of Assignment according to the info read from the Lists of Way, Transport, Breakage. Create the file with this info.
	 * @param pathWays1 the path to the file Assignvent.txt to create (read from the "message.properties")
	 * @return void
	 * */
	
	private void makeAssignment(String pathWays1) {

		int size = 0;
		int u, f, k;

		autoNumber = 0;
		tralNumber = 0;
		tramNumber = 0;
		for (u = 0; u < this.ways.size(); u++) {
			size = size + this.ways.get(u).getTransNumber();
		}

		for (u = 0; u < this.ways.size(); u++) {
			if (this.ways.get(u).getTypeTrans() == 0)
				autoNumber = autoNumber + this.ways.get(u).getTransNumber();
		} // how much do we need transport for a day
		for (u = 0; u < this.ways.size(); u++) {
			if (this.ways.get(u).getTypeTrans() == 1)
				tralNumber = tralNumber + this.ways.get(u).getTransNumber();
		}
		for (u = 0; u < this.ways.size(); u++) {
			if (this.ways.get(u).getTypeTrans() == 2)
				tramNumber = tramNumber + this.ways.get(u).getTransNumber();
		}

		u = 1;
		k = 1;
		while (k < size) {
			Assignment a = new Assignment();
			f = 0;
			for (int j = 0; j < this.breakages.size(); j++)
				if (this.trans.get(u - 1).getIdTrans() == this.breakages.get(j)
						.getIdTrans()) // check for breakage
					f = f + 1;
			if (f == 0) {
				a.setIdTrans(this.trans.get(u - 1).getIdTrans());
				this.assignment.add(a);
				k++;
			}
			u++;
			// System.out.println(k+" "+u);
			// System.out.println("сколько надо транспорта на день: 	автобусы "
			// +
			// autoNumber+" 	троллейбусы "+tralNumber+" 	трамваи "+tramNumber);
			if (u > this.trans.size()) {
				System.out.println("Не хватило!");
				k = size;
			} else if (k > tralNumber + autoNumber)// и 49 +136=185
				while (this.trans.get(u - 1).getTransType() == 1)
					// going to another typeTransport
					u++;
			else if (k > autoNumber)// 136
				while (this.trans.get(u - 1).getTransType() == 0)
					// going to another typeTransport
					u++; 
		}// filled the structure

		f = 0;
		k = 0;
		for (u = 0; u < this.assignment.size(); u++) {
			if (f < this.ways.get(k).getTransNumber()) {
				this.assignment.get(u).setIdWays(this.ways.get(k).getIdWays());
				// System.out.println(this.assignment.get(u).getIdWays());
				f++;
			} else {
				k++;
				this.assignment.get(u).setIdWays(this.ways.get(k).getIdWays()); // set IdWays
				f = 1;
			}
		}

		for (u = 0; u < this.assignment.size(); u++)
			this.assignment.get(u).setIdAssign(u + 1); // set IdAssign

		System.out.println("сколько надо транспорта в итоге:	автобусы "
				+ autoNumber + " 	троллейбусы " + tralNumber + " 	трамваи "
				+ tramNumber);
		System.out.println();
		System.out
				.println(size + "(надо всего) " + this.trans.size()
						+ "(есть в принципе)	" + this.assignment.size()
						+ "(назначено)");

		SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy");
		String s = "Assignment_" + formatter.format(new Date()) + ".csv";// ".txt";//+

		pathWays1 = pathWays1 + s;
		File outputFile = new File(pathWays1);
		try {
			if (outputFile.createNewFile())
				System.out.println("Файл " + outputFile.getName() + " создан");
			else {
				outputFile.delete();
				outputFile.createNewFile();
			}

			FileWriter fw = new FileWriter(outputFile, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);

			for (u = 0; u < this.assignment.size(); u++) {
				String str = "";
				if (this.trans.get(this.assignment.get(u).getIdTrans())
						.getTransType() == 0)
					str = "автобус	";
				if (this.trans.get(this.assignment.get(u).getIdTrans())
						.getTransType() == 1)
					str = "троллейбус	";
				if (this.trans.get(this.assignment.get(u).getIdTrans())
						.getTransType() == 2)
					str = "трамвай	";
				// pw.println(this.assignment.get(u).getIdAssign()+"	Тр-т "+this.assignment.get(u).getIdTrans()+" - "+str+"гос.номер "+this.trans.get(this.assignment.get(u).getIdTrans()-1).getCarNumber()+"		по маршруту №"+this.ways.get(this.assignment.get(u).getIdWays()-1).getWayNumber()+"		"+this.ways.get(this.assignment.get(u).getIdWays()-1).getFirstStop()
				// +
				// " - "+this.ways.get(this.assignment.get(u).getIdWays()-1).getLastStop()+"	"+this.ways.get(this.assignment.get(u).getIdWays()-1).getStopTime());
				pw.println(this.assignment.get(u).getIdAssign()	+ delimiter+ "Тр-т "+ this.assignment.get(u).getIdTrans()+ delimiter+ str
						+ delimiter	+ "гос.номер "+ this.trans.get(this.assignment.get(u).getIdTrans() - 1).getCarNumber()
						+ delimiter	+ "		по маршруту №"+ this.ways.get(this.assignment.get(u).getIdWays() - 1).getWayNumber()
						+ delimiter	+ this.ways.get(this.assignment.get(u).getIdWays() - 1).getFirstStop()
						+ delimiter+ this.ways.get(this.assignment.get(u).getIdWays() - 1).getLastStop()
						+ delimiter	+ "(всего "	+ this.ways.get(this.assignment.get(u).getIdWays() - 1).getTransNumber()+ " транспортных средств  с интервалом в "
						+ this.ways.get(this.assignment.get(u).getIdWays() - 1).getStopTime() + " мин.)");
				pw.println();
			}
			pw.close();
		} catch (IOException e) {
			System.err.println(e);
		}

	}

	/*
	 * verify the necessity to change transport's StopTime according to the number of needed and working transport
	 * @return void
	 * */
	
	private void verification(){//(String pathWays1) {
		int autoN = 0, tralN = 0, tramN = 0, autoBrN = 0, tralBrN = 0, tramBrN = 0;
		int u, f, size = 0, k = 1;
		for (u = 0; u < this.ways.size(); u++) {
			size = size + this.ways.get(u).getTransNumber();
		}

		for (u = 0; u < this.ways.size(); u++) {
			if (this.ways.get(u).getTypeTrans() == 0)
				autoNumber = autoNumber + this.ways.get(u).getTransNumber();
		} // сколько надо транспорта на день
		for (u = 0; u < this.ways.size(); u++) {
			if (this.ways.get(u).getTypeTrans() == 1)
				tralNumber = tralNumber + this.ways.get(u).getTransNumber();
		}
		for (u = 0; u < this.ways.size(); u++) {
			if (this.ways.get(u).getTypeTrans() == 2)
				tramNumber = tramNumber + this.ways.get(u).getTransNumber();
		}

		for (u = 0; u < this.trans.size(); u++) {
			if (this.trans.get(u).getTransType() == 0)
				autoN++; // how much do we have transport in park
			if (this.trans.get(u).getTransType() == 1)
				tralN++;
			if (this.trans.get(u).getTransType() == 2)
				tramN++;
		}

		for (int j = 0; j < this.trans.size(); j++) {// broken
			if (this.trans.get(j).getBreakage() == 1) {
				if (this.trans.get(j).getTransType() == 0) {
					autoBrN++;
					System.out.println(this.trans.get(j).getIdTrans());
				}
				if (this.trans.get(j).getTransType() == 1)
					tralBrN++;
				if (this.trans.get(j).getTransType() == 2)
					tramBrN++;
			}
		}

		System.out.println("сколько есть в парке транспорта: 	автобусы "
				+ autoN + " 	троллейбусы " + tralN + " 	трамваи " + tramN);
		System.out.println("сколько поломано: 			автобусы " + autoBrN
				+ " 	троллейбусы " + tralBrN + " 	трамваи " + tramBrN);
		System.out.println("сколько надо изначально: 		автобусы " + autoNumber
				+ " 	троллейбусы " + tralNumber + " 	трамваи " + tramNumber);
		int fauto = autoNumber - autoN + autoBrN;// how much not enough
		int ftral = tralNumber - tralN + tralBrN;
		int ftram = tramNumber - tramN + tramBrN;
		if (fauto <= 0)
			fauto = 0;
		if (ftral <= 0)
			ftral = 0;
		if (ftram <= 0)
			ftram = 0;

		System.out.println("сколько не хватает: 			автобусы " + fauto
				+ " 	троллейбусы " + ftral + " 	трамваи " + ftram);
		System.out.println();

		int j = 0;
		int f1 = 0;
		f = this.breakages.size();
		while (ftram > 0) {
			while (f != 0) {
				if (ftram == 0)
					break;
				while (this.trans.get(
						this.breakages.get(f - 1).getIdTrans() - 1)
						.getTransType() != 2) {// find in Breakage List tram
					f--;// there are no trams in List!!!!

					if ((f == 0) & (ftram > 0)) {
						f = f1;
						break;
					}
				}

				while (this.ways.get(j).getTypeTrans() != 2)
					// find in Ways List tram
					j++;

				k = this.breakages.get(f - 1).getIdWay() + j;
				this.ways.get(k - 1).setTransNumber((this.ways.get(k - 1).getTransNumber()) - 1);
				this.ways.get(k - 1).setStopTime(this.ways.get(k - 1).getStopNumber() * 6/ this.ways.get(k - 1).getTransNumber());
				ftram--;
				f1 = f;
				f--;
			}
		}

		j = 0;
		f1 = 0;
		f = this.breakages.size();
		while (ftral > 0) {
			while (f != 0) {
				if (ftral == 0)
					break;
				while (this.trans.get(
						this.breakages.get(f - 1).getIdTrans() - 1)
						.getTransType() != 1) {// find in Breakage List
												// trolleybus
					f--;// there are no trolleybus in List!!!!

					if ((f == 0) & (ftral > 0)) {
						f = f1;
						break;
					}
				}

				while (this.ways.get(j).getTypeTrans() != 1)
					// find in Ways List trolleybus
					j++;

				k = this.breakages.get(f - 1).getIdWay() + j;// 1+15
				this.ways.get(k - 1).setTransNumber(
						(this.ways.get(k - 1).getTransNumber()) - 1);
				this.ways.get(k - 1).setStopTime(
						this.ways.get(k - 1).getStopNumber() * 6
								/ this.ways.get(k - 1).getTransNumber());
				ftral--;
				f1 = f;
				f--;
			}
		}

		j = 0;
		f1 = 0;
		f = this.breakages.size();
		while (fauto > 0) {
			while (f != 0) {
				if (fauto == 0)
					break;
				while (this.trans.get(
						this.breakages.get(f - 1).getIdTrans() - 1)
						.getTransType() != 0) {// find in Breakage List autobus
					f--;// there are no autobus in List!!!!

					if ((f == 0) & (fauto > 0)) {
						f = f1;
						break;
					}
				}

				while (this.ways.get(j).getTypeTrans() != 0)
					j++;

				k = this.breakages.get(f - 1).getIdWay() + j;
				this.ways.get(k - 1).setTransNumber(
						(this.ways.get(k - 1).getTransNumber()) - 1);
				this.ways.get(k - 1).setStopTime(
						this.ways.get(k - 1).getStopNumber() * 6
								/ this.ways.get(k - 1).getTransNumber());
				fauto--;
				f1 = f;
				f--;
			}
		}

		/*
		 * String s = "Real_Ways"+".txt";
		 * 
		 * pathWays1 = pathWays1 + s; File outputFile = new File(pathWays1); try
		 * { if (outputFile.createNewFile())
		 * System.out.println("Файл "+outputFile.getName()+" создан"); else {
		 * outputFile.delete(); outputFile.createNewFile(); }
		 * 
		 * FileWriter fw = new FileWriter(outputFile, true); BufferedWriter bw =
		 * new BufferedWriter(fw); PrintWriter pw = new PrintWriter(bw);
		 * 
		 * for (u=0; u<this.ways.size(); u++) {
		 * pw.println(this.ways.get(u).getIdWays
		 * ()+"	"+this.ways.get(u).getStopNumber
		 * ()+"	"+this.ways.get(u).getTransNumber
		 * ()+"	"+this.ways.get(u).getStopTime
		 * ()+"	"+this.ways.get(u).getTypeTrans
		 * ()+"	"+this.ways.get(u).getWayNumber()); //
		 * pw.println(this.assignment.get(u).getIdAssign()+delimeter
		 * +"Тр-т "+this
		 * .assignment.get(u).getIdTrans()+delimeter+str+delimeter+"гос.номер "
		 * +this
		 * .trans.get(this.assignment.get(u).getIdTrans()-1).getCarNumber()+
		 * delimeter
		 * +"		по маршруту №"+this.ways.get(this.assignment.get(u).getIdWays
		 * ()-1)
		 * .getWayNumber()+delimeter+this.ways.get(this.assignment.get(u).getIdWays
		 * ()-1).getFirstStop() +
		 * delimeter+this.ways.get(this.assignment.get(u).
		 * getIdWays()-1).getLastStop());
		 * 
		 * } pw.close(); } catch (IOException e) { System.err.println(e);}
		 */

	}

	/*
	 * unpack files light in ziparchive according to the path read from "message.properties"
	 * @param destinationDirectory the path to write unpacked files 
	 * @return void
	 * */
	private static void unpack(String destinationDirectory) {
		String nameZip = destinationDirectory + "transport.zip";
		File sourceZipFile = new File(nameZip);
		int currentByte;
		int BUFFER = 2048;
		try {
			File unzipDestinationDirectory = new File(destinationDirectory);
			ZipFile zFile = new ZipFile(sourceZipFile);
			Enumeration<?> zipFileEntries = zFile.entries();
			while (zipFileEntries.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) zipFileEntries.nextElement(); // extract current record
				String entryname = entry.getName();
				System.out.println("Extracting: " + entry);
				File destFile = new File(unzipDestinationDirectory, entryname);
				File destinationParent = destFile.getParentFile();// directory definition
				destinationParent.mkdirs(); // directory making
				System.out.println(destinationParent);
				if (!entry.isDirectory()) { // extracting record, if it's not a directory

					BufferedInputStream is = new BufferedInputStream(
							zFile.getInputStream(entry));

					byte data[] = new byte[BUFFER];
					BufferedOutputStream dest = new BufferedOutputStream(
							new FileOutputStream(destFile), BUFFER);
					while ((currentByte = is.read(data, 0, BUFFER)) > 0) {
						dest.write(data, 0, currentByte);
					}
					dest.flush();
					dest.close();
					is.close();
				}
			}
			zFile.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	/*
	 * delete files unpacked from ziparchive
	 * @param pathWays the path to the unpacked files 
	 * @return void
	 * */
	private static void delete(String pathWays) {
		File file = new File(pathWays + "ways2.txt");
		file.delete();
		File fileTrFile = new File(pathWays + "transport.txt");
		fileTrFile.delete();
		File fileBrFile = new File(pathWays + "breakage.txt");
		fileBrFile.delete();
	}

	/*
	 * read path to the archive with data from file "message.properties"
	 * @return void
	 * */
	private static String readPath() {
		Properties props = new Properties();
		try {
			props.load(new FileInputStream("message.properties"));

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		String message = props.getProperty("path");
		System.out.println(message);
		return message;
	}

	public static void main(String[] args) {
		String pathWays = readPath();
		TransportSystem trSystem = new TransportSystem();
		unpack(pathWays);
		trSystem.addWays(pathWays);
		trSystem.addTrans(pathWays);
		trSystem.addBreakage(pathWays);
		trSystem.verification();//(pathWays);
		trSystem.makeAssignment(pathWays);
		delete(pathWays);
	}
}
