
public class Moto extends Velo
{
	private double volumeEngine;

	public Moto(String type, double maxSpeed,double volumeEngine)
	{
		super(type,maxSpeed);
		this.volumeEngine=volumeEngine;
	}
	public double getVolumeEngine() {
		return volumeEngine;
	}

	public void setVolumeEngine(double volumeEngine) {
		this.volumeEngine = volumeEngine;
	}
	
	
}
