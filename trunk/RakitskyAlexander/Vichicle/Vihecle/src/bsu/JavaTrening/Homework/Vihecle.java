/**
 * 
 */
package bsu.JavaTrening.Homework;

/**
 * @author alex
 *
 */
public class Vihecle {
	static int maxSpeed;
	int speed;
	double distance;
	boolean powerUp;
	double speedDispersal;
	boolean working=true;
	
	/**
	 * @return the speedDispersal
	 */
	public double getSpeedDispersal() {
		return speedDispersal;
	}

	/**
	 * @param speedDispersal the speedDispersal to set
	 */
	public void setSpeedDispersal(double speedDispersal) {
		this.speedDispersal = speedDispersal/3600d;
	}

	/**
	 * @return the distance
	 */
	public double getDistanse() {
		return distance;
	}
	/**
	 * @return the maxSpeed
	 */
	public static int getMaxspeed() {
		return maxSpeed;
	}

	/**
	 * @param maxsize the maxSpeed to set
	 */
	public static void setMaxspeed(int maxsize) {
		maxSpeed = maxsize;
	}

	/**
	 * @param Add distance 
	 */
	public void addDistanse(double distance) {
		if (distance==0)
			this.distance++;
		else
			this.distance+=distance;
	}
	/**
	 * @return the speed
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(int speed) {
		if (powerUp=false)
			start();
		int speedNow=getSpeed();
		if (speed==speedNow){
			staticGo(0);
		}
		else
			if (speed<speedNow)
				if (speedNow>0)
				{
					decreaseSpeed(speed);
				}
				else
					decreaseSpeed(0);
			else
				if (speed<maxSpeed)
				{
					increaseSpeed(speed);
				}
					else				
					increaseSpeed(getMaxspeed());
	}


	/**
	 * @return the working
	 */
	public boolean isWorking() {
		return working;
	}

	/**
	 * @param working the working to set
	 */
	public void setWorking(boolean working) {
		this.working = working;
	}

	public boolean start()
	{
		if (working)
		{
			speed=0;
			powerUp=true;
			return true;
		}
		else return false;
	}
	
	public void stop(){
		if (this.getSpeed()!=0)
			decreaseSpeed(0);
		powerUp=false;
		speed=0;
	}
	
	public void breaking(){
		setSpeedDispersal(4);
		decreaseSpeed(0);
	}
	

	public void addDistance(){
		distance+=(double)(getSpeed()*speedDispersal);
		System.out.println("Speed->"+speed+" Kilometers in hour \t Distance->"+distance+" Kilometers");
	}
	
	private void addDistance(int secondTime){
		distance+=getSpeed()*(secondTime/3600D);
	}
	
	public void increaseSpeed(int speed){
		int speedNow=getSpeed();
		for(int i=speedNow;i<speed;i++)
		{
			addDistance();
			this.speed++;
		}
		addDistance();
	}
	
	public void staticGo(int timeSecond){
		if (getSpeed()!=0)
		{
			double oldDistance=getDistanse();
			addDistance(timeSecond);
			System.out.println("the vihecle static go "+timeSecond+" second "+(getDistanse()-oldDistance)+" Kilometers");
			System.out.println("Distance now:"+getDistanse());
		}
	}
	
	public void decreaseSpeed(int speed){
		int speedNow=getSpeed();
		for(int i=speedNow;i>speed;i--)
		{
			addDistance();
			this.speed--;
		}
		//if (speed!=0)
			addDistance();	
	}
	
	Vihecle(){
		distance=0;
		working=true;
	}

}
