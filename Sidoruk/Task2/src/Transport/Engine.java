package Transport;

/**
 * Class how represented engine.
 * @author Vitaliy Sidoruk
 *
 */

public class Engine
{
    /**
     * Current rpm(rotations per minute).
     */
    private int rpm;
    
    /**
     * Maximal engine rpm.
     */
    private final int rpmMax = 5000;
    
    /**
     * Minimal engine rpm.
     */
    private final int rpmMin = 1000;
    
    /**
     * Start engine.
     */
    public void start()
    {
        rpm = rpmMin;
    }
    
    /**
     * Stop engine.
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
            return (rpm += 1000);
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
            return (rpm -= 1000);
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
     * Get maximal rpm.
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
