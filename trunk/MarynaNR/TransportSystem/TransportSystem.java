
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

/**
 * 
 */

/**
 * @author Мара
 *
 */
public class TransportSystem {
private static final String delimeter = ";";
public int autoNumber=0, tralNumber=0, tramNumber=0, autoWaysNumber, tralWaysNumber, tramWaysNumber;
public List <Transport> trans = new ArrayList<Transport>(); 
/*public List <Transport> auto = new ArrayList<Transport>(); 
public List <Transport> tral = new ArrayList<Transport>();
public List <Transport> tram = new ArrayList<Transport>();*/
public List <Way> ways=new ArrayList<Way>();
public List <Breakage> breakages=new ArrayList<Breakage>();
public List <Assignment> assignment=new ArrayList<Assignment>();

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
public int getAutoWaysNumber() {
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
}
/*public List<Transport> getAuto() {
	return auto;
}
public void setAuto(List<Transport> auto) {
	this.auto = auto;
}
public List<Transport> getTral() {
	return tral;
}
public void setTral(List<Transport> tral) {
	this.tral = tral;
}
public List<Transport> getTram() {
	return tram;
}
public void setTram(List<Transport> tram) {
	this.tram = tram;
}*/

public class Assignment{
public int idAssign, idTrans, idWays;
public Date date;

public int getIdAssign() {
	return idAssign;
}
public void setIdAssign(int idAssign) {
	this.idAssign = idAssign;
}
public int getIdTrans() {
	return idTrans;
}
public void setIdTrans(int idTrans) {
	this.idTrans = idTrans;
}
public int getIdWays() {
	return idWays;
}
public void setIdWays(int idWays) {
	this.idWays = idWays;
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
}

public class Breakage {
public int idTrans, idBreakage, idWay;
public int getIdWay() {
	return idWay;
}
public void setIdWay(int idWay) {
	this.idWay = idWay;
}
public String dateBr;
public String description;
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public int getIdBreakage() {
	return idBreakage;
}
public void setIdBreakage(int idBreakage) {
	this.idBreakage = idBreakage;
}


public int getIdTrans() {
	return idTrans;
}
public void setIdTrans(int idTrans) {
	this.idTrans = idTrans;
}
public String getDateBr() {
	return dateBr;
}
public void setDateBr(String string) {
	this.dateBr = string;
}


} 

public static class Way implements Serializable{
public int stopNumber, idWays, transNumber, stopTime, wayNumber, typeTrans;
public String firstStop, lastStop;


public int getWayNumber() {
	return wayNumber;
}

public void setWayNumber(int wayNumber) {
	this.wayNumber = wayNumber;
}

public int getStopNumber() {
	return stopNumber;
}

public void setStopNumber(int stopNumber) {
	this.stopNumber = stopNumber;
}

public int getIdWays() {
	return idWays;
}

public void setIdWays(int idWays) {
	this.idWays = idWays;
}

public int getTransNumber() {
	return transNumber;
}

public void setTransNumber(int transNumber) {
	this.transNumber = transNumber;
}

public int getStopTime() {
	return stopTime;
}

public void setStopTime(int stopTime) {
	this.stopTime = stopTime;
}

public int getTypeTrans() {
	return typeTrans;
}

public void setTypeTrans(int typeTrans) {
	this.typeTrans = typeTrans;
}

public String getFirstStop() {
	return firstStop;
}

public void setFirstStop(String firstStop) {
	this.firstStop = firstStop;
}

public String getLastStop() {
	return lastStop;
}

public void setLastStop(String lastStop) {
	this.lastStop = lastStop;
}

private String toString(Way w) {

return "\n"+idWays + " "+ stopNumber ;
}
}

public class Transport {
public int idTrans, transType, breakage, idWay;
public String carNumber;
public int getIdWay() {
	return idWay;
}
public void setIdWay(int idWay) {
	this.idWay = idWay;
}
public String getCarNumber() {
	return carNumber;
}
public void setCarNumber(String carNumber) {
	this.carNumber = carNumber;
}

public int getIdTrans() {
	return idTrans;
}
public void setIdTrans(int transId) {
	this.idTrans = transId;
}
public int getTransType() {
	return transType;
}
public void setTransType(int transType) {
	this.transType = transType;
}
public int getBreakage() {
	return breakage;
}
public void setBreakage(int breakage) {
	this.breakage = breakage;
}
public int getidWay() {
	return idWay;
}
public void setidWay(int idWay) {
	this.idWay = idWay;
}

}

public void addWays(String pathWays1){
	pathWays1 = pathWays1 + "Ways2.txt";
	try {
		FileReader fr = new FileReader(pathWays1);
		Scanner scan = new Scanner(fr);
		while (scan.hasNext()) {
			Way w=new Way();
			if (scan.hasNextInt())
				   w.setIdWays(scan.nextInt());
			if (scan.hasNextInt())
			    w.setStopNumber(scan.nextInt());
			if (scan.hasNextInt())
			    w.setTransNumber(scan.nextInt());
			w.setStopTime(w.getStopNumber()*6/w.getTransNumber());
		//	if (scan.hasNextInt())
		//	    w.setStopTime(scan.nextInt());
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
		for (int u=0; u<this.ways.size(); u++) System.out.println(this.ways.get(u).getFirstStop() + " - "+this.ways.get(u).getLastStop()+ " - "+this.ways.get(u).getWayNumber());
		
		fr.close();
	} catch (IOException e) {
		System.out.println("Ошибка: 		" + e.toString());
	}
}


private void addTrans(String pathWays1) {
	pathWays1 = pathWays1 + "Transport.txt";
	try {
		FileReader fr = new FileReader(pathWays1);
		Scanner scan = new Scanner(fr);
		while (scan.hasNext()) {
			Transport t=new Transport();
			if (scan.hasNextInt())
				   t.setIdTrans(scan.nextInt());
			if (scan.hasNextInt())
			   t.setTransType(scan.nextInt());
			if (scan.hasNext())
			    t.setCarNumber(scan.next());
	/*if (t.getTransType()==0) autoNumber++;
		//this.auto.add(t);
		if (t.getTransType()==1) tralNumber++; 
			//this.tral.add(t);
		if (t.getTransType()==2) tramNumber++;
			//this.tram.add(t);*/
		
		this.trans.add(t);
		}
		/*for (int u=0; u<this.auto.size(); u++) System.out.println(this.auto.get(u).getTransType() + " - "+this.auto.get(u).getCarNumber());
		for (int u=0; u<this.tral.size(); u++) System.out.println(this.tral.get(u).getTransType() + " - "+this.tral.get(u).getCarNumber());
		for (int u=0; u<this.tram.size(); u++) System.out.println(this.tram.get(u).getTransType() + " - "+this.tram.get(u).getCarNumber());*/
		
	fr.close();
	} catch (IOException e) {
		System.out.println("Ошибка: 		" + e.toString());
	}
	
}

private void addBreakage(String pathWays1) {
	pathWays1 = pathWays1 + "Breakage.txt";
	try {
		FileReader fr = new FileReader(pathWays1); // файл должен заканчиваться Enterom!!!!!
		Scanner scan = new Scanner(fr);
		while (scan.hasNext()) {
			Breakage b=new Breakage();
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
		
		
		
		for (int u=0; u<this.breakages.size(); u++) {
			this.trans.get(this.breakages.get(u).getIdTrans()-1).setBreakage(1);//пометим в списке как поломанный!
			System.out.print(this.breakages.get(u).getIdTrans() + " - "+this.breakages.get(u).getDateBr()+this.breakages.get(u).getDescription());}
	fr.close();
	} catch (IOException e) {
		System.out.println("Ошибка: 		" + e.toString());
	}
}

private void makeAssignment(String pathWays1) {

		int size = 0;
		int u,f,k; 
		
		for (u=0; u<this.ways.size(); u++){
			 size = size+this.ways.get(u).getTransNumber();}
		
		for (u=0; u<this.ways.size(); u++){
			if (this.ways.get(u).getTypeTrans()==0)
			 autoNumber = autoNumber+this.ways.get(u).getTransNumber();} // сколько надо транспорта на день
		for (u=0; u<this.ways.size(); u++){
			if (this.ways.get(u).getTypeTrans()==1)
			 tralNumber = tralNumber+this.ways.get(u).getTransNumber();}
		for (u=0; u<this.ways.size(); u++){
			if (this.ways.get(u).getTypeTrans()==2)
			 tramNumber = tramNumber+this.ways.get(u).getTransNumber();}
			
		
		
		u=1; k=1;
		while (k<size){  
		Assignment a=new Assignment();
		f=0;
		for (int j=0; j<this.breakages.size(); j++) 
		if (this.trans.get(u-1).getIdTrans() == this.breakages.get(j).getIdTrans()) // проверили на breakage
		f=f+1;
		if (f==0) {
		a.setIdTrans(this.trans.get(u-1).getIdTrans());
		this.assignment.add(a);
		k++;
		}
		u++;
		System.out.println(k+" "+u);
		
		if (u>this.trans.size())//tramNumber+tralNumber+autoNumber)
			{
			System.out.println("Не хватило!");
			k=size;			
			}
		else 
			if (k>tralNumber+autoNumber)// и 49 +136=185
			 while (this.trans.get(u-1).getTransType()==1) // доходим до следующего типа транспорта
				 u++;
		else 
			if (k>autoNumber)//136
			  while (this.trans.get(u-1).getTransType()==0)  // доходим до следующего типа транспорта
			  u++; //154 и 136
		}//заполнили структуру
		
			
		f=0;k=0;
		for (u=0; u<this.assignment.size();u++){  
		if (f<this.ways.get(k).getTransNumber()){
		this.assignment.get(u).setIdWays(this.ways.get(k).getIdWays());
		System.out.println(this.assignment.get(u).getIdWays());
		f++;
		}
		else {
		k++;
		this.assignment.get(u).setIdWays(this.ways.get(k).getIdWays()); // установили IdWays
		f=1;}}
		
		for (u=0; u<this.assignment.size(); u++)
		this.assignment.get(u).setIdAssign(u+1);		//установили IdAssign
		
		
		
		for (u=0; u<this.assignment.size(); u++) System.out.println(this.assignment.get(u).getIdAssign() + " - "+this.assignment.get(u).getIdTrans());
			System.out.println(size +" "+ this.trans.size()+" "+this.assignment.size());
			
		SimpleDateFormat formatter =
			new SimpleDateFormat("dd_MM_yyyy");
		String s = "Assignment_"+ formatter.format(new Date())+".txt";//+".csv";
				
		pathWays1 = pathWays1 + s;
		File outputFile = new File(pathWays1);
		try {
		if (outputFile.createNewFile())
			System.out.println("Файл "+outputFile.getName()+" создан");
		
		FileWriter fw = new FileWriter(outputFile, true);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter pw = new PrintWriter(bw);
		
		for (u=0; u<this.assignment.size()-1; u++) {
		//int j=this.assignment.get(u).getIdAssign();
		String str="";
		if (this.trans.get(this.assignment.get(u).getIdTrans()).getTransType()==0)
		str="автобус	";
		if (this.trans.get(this.assignment.get(u).getIdTrans()).getTransType()==1)
		str="троллейбус	";
		if (this.trans.get(this.assignment.get(u).getIdTrans()).getTransType()==2)
		str="трамвай	";
			pw.println(this.assignment.get(u).getIdAssign()+"	Тр-т "+this.assignment.get(u).getIdTrans()+" - "+str+"гос.номер "+this.trans.get(this.assignment.get(u).getIdTrans()-1).getCarNumber()+"		по маршруту №"+this.ways.get(this.assignment.get(u).getIdWays()-1).getWayNumber()+"		"+this.ways.get(this.assignment.get(u).getIdWays()-1).getFirstStop() + " - "+this.ways.get(this.assignment.get(u).getIdWays()-1).getLastStop()+"	"+this.ways.get(this.assignment.get(u).getIdWays()-1).getStopTime());
		//	pw.println(this.assignment.get(u).getIdAssign()+delimeter +"Тр-т "+this.assignment.get(u).getIdTrans()+delimeter+str+delimeter+"гос.номер "+this.trans.get(this.assignment.get(u).getIdTrans()-1).getCarNumber()+delimeter+"		по маршруту №"+this.ways.get(this.assignment.get(u).getIdWays()-1).getWayNumber()+delimeter+this.ways.get(this.assignment.get(u).getIdWays()-1).getFirstStop() + delimeter+this.ways.get(this.assignment.get(u).getIdWays()-1).getLastStop());
			pw.println();
			}
		pw.close();
		} catch (IOException e) {
			System.err.println(e);}
		
		
		
		
}
	private void verification() {
		int autoN=0, tralN=0, tramN=0, autoBrN=0, tralBrN=0, tramBrN=0;
		int u,f, size=0, k=1;
		for (u=0; u<this.ways.size(); u++){
		 size = size+this.ways.get(u).getTransNumber();}
		
		for (u=0; u<this.ways.size(); u++){
			if (this.ways.get(u).getTypeTrans()==0)
			 autoNumber = autoNumber+this.ways.get(u).getTransNumber();} // сколько надо транспорта на день
		for (u=0; u<this.ways.size(); u++){
			if (this.ways.get(u).getTypeTrans()==1)
			 tralNumber = tralNumber+this.ways.get(u).getTransNumber();}
		for (u=0; u<this.ways.size(); u++){
			if (this.ways.get(u).getTypeTrans()==2)
			 tramNumber = tramNumber+this.ways.get(u).getTransNumber();}
		
		for (u=0; u<this.trans.size(); u++) {
			if (this.trans.get(u).getTransType()==0) autoN++; //сколько есть в парке транспорта 
			if (this.trans.get(u).getTransType()==1) tralN++;
			if (this.trans.get(u).getTransType()==2) tramN++;}
		
		
		for (int j=0;j<this.trans.size();j++)	{
			if (this.trans.get(j).getBreakage()==1){
		    if (this.trans.get(j).getTransType()==0) 
			 autoBrN++;
			if (this.trans.get(j).getTransType()==1) 
			 tralBrN++;
			if (this.trans.get(j).getTransType()==2) 
			 tramBrN++;}}
		
		
		//if (u>this.trans.size())//tramNumber+tralNumber+autoNumber)
		//{	
	int fauto=autoNumber-autoN+autoBrN;// сколько не хватает
	int ftral=tralNumber-tralN+tralBrN;
	int ftram=tramNumber-tramN+tramBrN;
	if (fauto<=0) fauto=0;
	if (ftral<=0) ftral=0;
	if (ftram<=0) ftram=0;
	
			/*if (fauto>0){
			for (int j=0;j<u;j++)
			this.ways.get(this.assignment.get(j).getIdWays()-1).setStopTime((this.ways.get(this.assignment.get(j).getIdWays()-1).getStopNumber()-fauto)*6/this.ways.get(this.assignment.get(j).getIdWays()-1).getTransNumber());
		}
		
		
		int ftral=tralNumber-tralN+tralBrN;
		if (ftral>0)
			for (int j=autoNumber-fauto;j<u;j++){
			this.ways.get(this.assignment.get(j).getIdWays()-1).setStopTime((this.ways.get(this.assignment.get(j).getIdWays()-1).getStopNumber()-ftral)*6/this.ways.get(this.assignment.get(j).getIdWays()-1).getTransNumber());
		}*/
		//if (ftram>0)
			//for (int j=(autoNumber-fauto+tralNumber-ftral);j<autoNumber-fauto+tralNumber-ftral+tramNumber-ftram;j++)
			
		//for (int j=0;j<ftram;j++)
	int j=0;int f1=0;
	f=this.breakages.size();
		//for (f=this.breakages.size();f>0;f--){
		//while (f>0) {			
				while (ftram>0) {//for (int j=0;j<ftram;j++){
					while (f!=0){
						if (ftram==0) 
							break;
						while (this.trans.get(this.breakages.get(f-1).getIdTrans()-1).getTransType()!=2){//найдем в списке поломок трамвай
						f--;//в списке нет больше трамваев!!!!
					
						if ((f==0)&(ftram>0)){//что-то здесь
						f=f1;
						break;}}
					
					while (this.ways.get(j).getTypeTrans()!=2) //найдем в списке маршрутов трамвай
						j++;
					
					k=this.breakages.get(f-1).getIdWay()+j;//1+15
					this.ways.get(k-1).setTransNumber((this.ways.get(k-1).getTransNumber())-1);
					this.ways.get(k-1).setStopTime(this.ways.get(k-1).getStopNumber()*6/this.ways.get(k-1).getTransNumber());	
					ftram--;
					f1=f;
					f--;}
				}

				/*j=0;
				while (ftral>0) {//for (int j=0;j<ftram;j++){
					while (this.trans.get(this.breakages.get(f-1).getIdTrans()-1).getTransType()!=1)
						f--;
					while (this.ways.get(j).getTypeTrans()!=1)
						j++;
					k=this.breakages.get(f-1).getIdWay()+j;
					this.ways.get(k-1).setStopTime(this.ways.get(k-1).getStopNumber()*6/(this.ways.get(k-1).getTransNumber()-1));	
					ftral--;
					f--;
				}

				j=0;
				while (fauto>0) {//for (int j=0;j<ftram;j++){
					while (this.trans.get(this.breakages.get(f-1).getIdTrans()-1).getTransType()!=0)
						f--;
					k=this.breakages.get(f-1).getIdWay()+j;
					this.ways.get(k-1).setStopTime(this.ways.get(k-1).getStopNumber()*6/(this.ways.get(k-1).getTransNumber()-1));	
					fauto--;
					f--;
				}*/
			//	f--;
				//this.ways.get(this.assignment.get(j).getIdWays()-1).setStopTime(this.ways.get(this.assignment.get(j).getIdWays()-1).getStopNumber()*6/(this.ways.get(this.assignment.get(j).getIdWays()-1).getTransNumber()-ftram));
	//	}
	
	//	}
		
		
			
		}
	
	public static void main(String[] args) {
		String pathWays = "d:\\Politech\\hobby\\__JAVA\\workspace\\TransportSystem\\data\\";//c:\\Users\\epam0020\\workspace\\TransportSystem\\data\\";
		TransportSystem trSystem=new TransportSystem();
		trSystem.addWays(pathWays);
		trSystem.addTrans(pathWays);
		trSystem.addBreakage(pathWays);
	//	trSystem.verification();
		trSystem.makeAssignment(pathWays);

	//	Transport tr=trSystem.new Transport();
	}
	

}
