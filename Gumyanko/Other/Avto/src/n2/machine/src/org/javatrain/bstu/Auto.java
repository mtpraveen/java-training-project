/**
 * 
 */
package n2.machine.src.org.javatrain.bstu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.String;

/**
 * @author epam0009
 * 
 */
public class Auto extends Transport implements Movement {
	private float fuel;
	private int speed;
	float time=0;
	float distanse=0;
	boolean started=false;

	

	public void setTime(float time) {
		this.time = time;
	}

	private Engine myEngine = new Auto.Engine();
	//Random random = new Random();
	
	private boolean isEnoughFuel() {
		return getFuel() > 0;

	}

	public float getSpeed() {
		return speed;
	}
	public float getTime() {
		return time;
	}
		public float getFuel() {
		return fuel;
	}

	public void setFuel(float fuel) {
		this.fuel = fuel;
	}

	public class Engine implements IEngine {
		byte Transmission;

		@Override
		public void onClutch() {
			setTime(time+=0.001);
		}

		@Override
		public void offClutch() {
			setTime(time+=0.001);
		}

		@Override
		public void onTransmission() {
			setTime(time+=0.001);
			Transmission = 1;
		}

		@Override
		public void offTransmission() {
			setTime(time+=0.001);
			Transmission = 0;
		}

		@Override
		public void endingTheWheels() {

		}

	
		@Override
		public void createEngineSpeed() {
			switch (Transmission) {
			case 0:
				System.out.println("0км/ч!");
				break;
			case 1:
				speed=(int) (1 + Math.random()*19);
				System.out.println(getSpeed()+"км/ч!");
				break;
			case 2:
				speed=(int) (21 + Math.random()*19);
				System.out.println(getSpeed()+"км/ч!");
				break;
			case 3:
				speed=(int) (41 + Math.random()*29);
				System.out.println(getSpeed()+"км/ч!");
				break;
			case 4:
				speed=(int) (71 + Math.random()*49);
				System.out.println(getSpeed()+"км/ч!");
				break;
			case 5:
				speed=(int) (121 + Math.random()*79);
				System.out.println(getSpeed()+"км/ч!");
				break;
			default:
				System.out.println("Казус!!");
				break;
			}
		}

		@Override
		public int numberOfFuelSupply() {
			return 0;
		}

		@Override
		public int numberOfWheelRevolutions() {
			return 0;
		}

		@Override
		public void increaseTransmission() {
			time+=0.005;
			Transmission++;
			if (Transmission == 6) {
				Transmission = 5;
				System.out.println("Выше передачи нет!");
			}
		}

		@Override
		public void decreaseTransmission() {
			time+=0.005;
			// TODO Auto-generated method stub
			Transmission--;
			if (Transmission == -1) {
				Transmission = 0;
				System.out.println("Вы по прежнему на нейтралке.Ниже передачи нет!");
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args){
		Auto s = new Auto();
		s.setFuel(23);
		System.out.println("Press:'1' - to start, '2' - to stop,'3' - to brake, '4'-increaseSpeed, " +
		"'5'-decreaseSpeed, '6'-testSpeed, '7'-distance,   '0'-Exit from program");
		char c = 0;
		while(c!='0'){
		try{
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter: ");
	    String str = read.readLine();
	    char c1 = str.charAt(0);
	    c=c1;
		
		switch (c) {
		case '1':
			s.start();
			break;
		case '2':
			s.stop();
			break;
		case '3':
			s.brake();
			break;
		case '4':
			s.increaseSpeed();
			break;
		case '5':
			s.decreaseSpeed();
			break;
		case '6':
			s.testSpeed();
			break;
		case '7':
			s.distanse();
			break;
		default:
			System.out.println("Exit!!");
			break;
		}
		
		
		
		}
		catch (IOException e){
			System.out.println("Error!");
		}
		}	
	}
		
	
	public void start() {
		if (started==false){
		started =true;
		if (isEnoughFuel()) { // test fuel
			System.out.println("start - Машина едет!  " + "(топливо = " + fuel
					+ " л)");
			myEngine.onClutch();
			myEngine.onTransmission();
			myEngine.createEngineSpeed();
		} else
			System.out.println("Нет топлива! Машина стоит!");
		
		}	
	}

	@Override
	public void stop() {
		if(started ==true){
		myEngine.offClutch();
		myEngine.offTransmission();
		System.out.println("stop - Машина остановлена ");
		time+=0.002;
		speed=0;
		}
		

	}

	@Override
	public void brake() {
		if (started ==true){
		myEngine.endingTheWheels();
		myEngine.offTransmission();
		System.out.println("breake - Машина затормозила! ");
		time+=0.01;
		started =false;
		speed=0;
		}

	}

	@Override
	public void increaseSpeed() {
		if (started ==true){
		myEngine.increaseTransmission();
		System.out.println(myEngine.Transmission + " передача");
		myEngine.createEngineSpeed();
		time+=0.002;
		}
	}

	@Override
	public void decreaseSpeed() {
		if (started ==true){
			myEngine.decreaseTransmission();
		System.out.println(myEngine.Transmission + " передача");
		myEngine.createEngineSpeed();
		time+=0.002;
		}
	}

	@Override
	public float distanse() {
		distanse+=getTime()*(float)getSpeed();
		System.out.println("Дистанция " + distanse+"км");
		return distanse;
	}

	@Override
	public void testSpeed() {
		System.out.println(getSpeed());

	}

}
