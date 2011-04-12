package Transport;

/**
 * Class how represented human foots.
 * @author Vitaliy Sidoruk
 *
 */

public class Foots
{
    /**
     * Current rpm(rotations per minute).
     */
    private int rpm;
    
    /**
     * Maximal human foots rpm.
     */
    private final int rpmMax = 50;
    
    /**
     * Minimal human foots rpm.
     */
    private final int rpmMin = 5;
    
    /**
     * Start foot motion.
     */
    public void start()
    {
        rpm = rpmMin;
    }
    
    /**
     * Stop foot motion.
     */
    public void stop()
    {
        rpm = 0;
    }
    
    /**
     * Increase rpm.
     * @return current rpm
     */
    public int accelerate()
    {
        if(rpm < rpmMax)
        {
            return (rpm += 5);
        }
        return 0;
        
    }
    
    /**
     * Decrease rpm.
     * @return current rpm
     */
    public int brake()
    {
        if(rpm > rpmMin)
        {
            return (rpm -= 5);
        }
        return 0;
    }

    /**
     * Get current rpm.
     * @return the rpm
     */
    public int getRpm()
    {
        return rpm;
    }

    /**
     * Get maimal rpm.
     * @return the rpmMax
     */
    public int getRpmMax()
    {
        return rpmMax;
    }
    
    /**
     * Get minimal rpm.
     * @return the rpmMin
     */
    public int getRpmMin()
    {
        return rpmMin;
    }
}
