package Transport;

/**
 * Class how represented bicycle.
 * @author Vitaliy Sidoruk
 *
 */

public class Bicycle implements ITransportActions
{
    /**
     * Foots of bicyclist.
     */
    Foots foots;
        
    /**
     * Value for calculate speed.
     */
    float speedGear = 1.5F;
    
    /**
     * Bicycle wheels.
     */
    boolean[] wheels;
    
    /**
     * Start motion.
     */
    public void start()
    {
        foots = new Foots();
        wheels = new boolean[2];
        
        foots.start();
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
        foots.stop();
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
        foots.accelerate();
    }
    
    /**
     * Decrease speed;
     */
    public void onBreak()
    {
        foots.brake();
    }
    
    /**
     * Current speed.
     * @return current speed
     */
    public int getSpeed()
    {
        return (int)(foots.getRpm()*speedGear);
    }
}
