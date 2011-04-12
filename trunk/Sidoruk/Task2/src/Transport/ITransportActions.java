package Transport;

/**
 * Iterface how contain basic transport actions.
 * @author Vitaliy Sidoruk
 *
 */

public interface ITransportActions
{
    /**
     * Start motion.
     */
    public void start();
    
    /**
     * Stop motion.
     */
    public void stop();
    
    /**
     * Increase speed.
     */
    public void onGas();
    
    /**
     * Decrease speed;
     */
    public void onBreak();
    
    /**
     * Current speed.
     */
    public int getSpeed();
}
