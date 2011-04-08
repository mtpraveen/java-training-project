/**
 * 
 */
package bsu.JavaTrening.Homework;

import java.io.IOException;


/**
 * @author alex
 *
 */
public class Car extends MechanicalVihecle{
	boolean AutoTransmission;
	
	/**
	 * @return the autoTransmission
	 */
	public boolean isAutoTransmission() {
		return AutoTransmission;
	}
	/**
	 * @param autoTransmission the autoTransmission to set
	 */
	public void setAutoTransmission(boolean autoTransmission) {
		AutoTransmission = autoTransmission;
	}
	public void driveNext() 
	{
		if(isAutoTransmission()){
			driveNow++;
			System.out.println(toString());
		}
		else
			try {
				super.driveNext();
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public void drivePrev() 
	{
		if(isAutoTransmission())
		{
			System.out.println(toString());
			driveNow--;
		}
		else
			try {
				super.drivePrev();
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	/**
	 * 
	 */
	public Car() {
		// TODO Auto-generated constructor stub
		super();
		setMaxDrive(6);
		setName("Car");
		setMaxspeed(190);
		setSpeedDispersal(3);	
		setAutoTransmission(false);
		drive[0]=0;
		drive[1]=35;
		drive[2]=60;
		drive[3]=80;
		drive[4]=110;
		drive[5]=getMaxDrive();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String result="The car " + name
				+ " whith drive " + getDriveNow() + " and speed "
				+ getSpeed();
		if (isAutoTransmission())
			result+=" whith automatic transmission ";
		else
			result+=" whith Manual transmission ";
		if (isWorking())
			result+="is working ";
		else
			result+="isn't working ";
		return result;
		
	}
	public Car(String name,boolean autoTransmission)
	{
		this();
		setAutoTransmission(autoTransmission);
		setName(name);
	}
}
