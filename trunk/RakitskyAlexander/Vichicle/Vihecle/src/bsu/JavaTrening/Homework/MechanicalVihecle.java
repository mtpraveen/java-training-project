/**
 * 
 */
package bsu.JavaTrening.Homework;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * @author alex
 *
 */
public class MechanicalVihecle extends Vihecle{
	 int driveNow;
	 int maxDrive;
	 int drive[];
	 String name;
	 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the driveNow
	 */
	public int getDriveNow() {
		return driveNow;
	}
	/**
	 * @param driveNow the driveNow to set
	 */
	public void setDriveNow(int driveNow) {
		this.driveNow = driveNow;
	}
	/**
	 * @return the maxDrive
	 */
	public int getMaxDrive() {
		return maxDrive;
	}
	/**
	 * @param maxDrive the maxDrive to set
	 */
	public void setMaxDrive(int maxDrive) {
		drive=new int[maxDrive];
		this.maxDrive = maxDrive;
	}
	public void driveNext() throws NumberFormatException, IOException{
		long secondBeforeInput=System.currentTimeMillis();
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter please drive(now drive "+getDriveNow()+", increaseSpeed): ");
		int enterDrive=Integer.parseInt(r.readLine());
		long secondAfterInput=System.currentTimeMillis();
		staticGo((int)(secondAfterInput-secondBeforeInput)/1000);
		if (enterDrive==(driveNow+1))
		{
			if (driveNow<maxDrive)
				driveNow++;
		}
		else
		{
			breakage();
			System.out.println("The machin "+name+" don't working");
		}
		
	}
	public void drivePrev() throws NumberFormatException, IOException{
		long secondBeforeInput=System.currentTimeMillis();
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter please drive(now drive "+getDriveNow()+", decreaseSpeed): ");
		int enterDrive=Integer.parseInt(r.readLine());
		long secondAfterInput=System.currentTimeMillis();
		staticGo((int)(secondAfterInput-secondBeforeInput)/1000);
		if (enterDrive==(driveNow-1))
		{
			if (driveNow!=0)
				driveNow--;
		}else
		{
			breakage();
			System.out.println("The vihecle "+name+" don't working");
		}
	}
	
	public void breakage(){
		setWorking(false);
		super.decreaseSpeed(0);
	}
	
	public void increaseSpeed(int speed){
		int speedNow=getSpeed();
		for(int i=speedNow;i<speed;i++)
			if (isWorking())
			{
				{
					if (i==drive[driveNow])
					{
						try {
							driveNext();
						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if (isWorking())
					{
						addDistance();
						this.speed++;
					}
				}
			}
		if (getSpeed()!=0)
			addDistance();
	}
	
	
	public void decreaseSpeed(int speed)
	{
		int speedNow=getSpeed();
		for(int i=speedNow;i>=(speed);i--)
			if (isWorking())
			{
				{
					addDistance();
					if (getSpeed()!=0)
						this.speed--;
					if (i==(drive[driveNow-1]))
					{
						try {
							drivePrev();
						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		//addDistance();
	}
	
	MechanicalVihecle(){
		super();
		setDriveNow(0);
			}

}
