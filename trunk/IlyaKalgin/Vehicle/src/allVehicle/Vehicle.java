/**
 *
 */
package allVehicle;

/**
 * @author Kalgin I.V.
 *
 */
public class Vehicle {


	public class Bicycle extends Vehicle {
		public static final int MAX_SPEEDbicycle=40;
		public Bicycle(){

		}
	}

	public class Moto extends Vehicle {
		public static final int MAX_SPEEDmoto=300;
		public Moto(){

		}
	}

	public class Car extends Vehicle {
		int speed=0;
		private String model;
		private Engine engine= new Engine();
		public static final int MAX_SPEEDcar=220;
		public double transfer;
		public double transferSpeed;
		public int transferManual=5;
		public boolean transferAutomatic = true;


		public Car(String model){
			this.model=model;
		}

		public Car() {
			// TODO Auto-generated constructor stub
		}

		public  void Start(){
			System.out.println(model+ "- Car has moved!!!");
		}

		public void Stop(){
			System.out.println(model+ "- Car has stoped!!!");
		}

		public void Brake(){
			System.out.println(model+ "- Car has started to brake!!!");
		}

		public void increaseSpeed(){
			for( int speed=0; speed<=MAX_SPEEDcar; speed+=5);
			System.out.println("Set of speed:"+model+speed+"km/h");
			engine.newTransfer(speed);
		}

		public void decreaseSpeed(){
			for( int speed=MAX_SPEEDcar; speed>=0; speed-=5);
			System.out.println("Set of speed:"+model+speed+"km/h");
			engine.newTransfer(speed);
		}




public class Engine {
	public void newTransfer(int speed) {
		transferSpeed=MAX_SPEEDcar/5;
		transfer=Math.round(speed/transferSpeed);
		System.out.println(model+" now transfer: "+transfer);
	}
}
	/**
	 * @param args
	 * @return 
	 */
	public  void main(String[] args) {
		// TODO Auto-generated method stub
		Car move = new Car();
		move.Start();
		move.increaseSpeed();
		move.Brake();
		move.decreaseSpeed();
		move.Stop();

	}

}
}