package Transport;

/**
 * Class how represented motorcycle.
 * @author Vitaliy Sidoruk
 *
 */

public class Motorcycle implements ITransportActions
{
	/**
    * Engine in motorcycle.
    */
    Engine engine;
    
    /**
     * Manual gearbox in motorcycle.
     */
    GearBox gearBox;
    
    /**
     * Value for calculate speed.
     */
    float speedGear = 1.5F;
    
    /**
     * Motorcycle wheels.
     */
    boolean[] wheels;
    
    /**
     * Start motion.
     */
    public void start()
    {
        engine = new Engine();
        gearBox = new GearBox();
        wheels = new boolean[2];
        
        engine.start();
        gearBox.start();
        for(int i = 0; i < wheels.length; i++)
        {
            wheels[i] = true;
        }
    }
    
    /**
     * Stop motion.
     */
    public void stop()
    {
        engine.stop();
        gearBox.stop();
        for(int i = 0; i < wheels.length; i++)
        {
            wheels[i] = false;
        }
    }
    
    /**
     * Increase speed.
     */
    public void onGas()
    {
        if(engine.accelerate() == engine.getRpmMax())
        {
            gearBox.gearUp();
        }
    }
    
    /**
     * Decrease speed;
     */
    public void onBreak()
    {
        if(engine.brake() == engine.getRpmMin())
        {
            gearBox.gearDown();
        }
    }
    
    /**
     * Current speed.
     * @return current speed
     */
    public int getSpeed()
    {
        return (int)(engine.getRpm()*gearBox.getCurrentGear()*speedGear);
    }
}
