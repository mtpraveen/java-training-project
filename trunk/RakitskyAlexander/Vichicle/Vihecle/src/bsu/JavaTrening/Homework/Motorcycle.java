/**
 * 
 */
package bsu.JavaTrening.Homework;

/**
 * @author alex
 *
 */
public class Motorcycle extends MechanicalVihecle{
	Motorcycle(){
		super();
		setMaxDrive(5);
		setName("Moto");
		setMaxspeed(100);
		setSpeedDispersal(2);	
		drive[0]=0;
		drive[1]=20;
		drive[2]=40;
		drive[3]=60;
		drive[4]=getMaxDrive();
	}
	Motorcycle(String name){
		this();
		setName(name);
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String result="The motorcycle " + name
				+ " whith drive " + getDriveNow() + " and speed "
				+ getSpeed();
		if (isWorking())
			result+="is working ";
		else
			result+="isn't working ";
		return result;
		
	}
}
