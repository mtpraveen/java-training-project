package Transport;

/**
 * Class how represented manual gearbox.
 * @author Vitaliy Sidoruk
 *
 */

public class GearBox {
    
	/**
	 * Maximal count of gears.
	 */
	int countGears = 5;
	
	/**
	 * Current gear.
	 */
	int currentGear = 0;
	
	/**
	 * Start gearbox.
	 */
	public void start()
	{
	    gearUp();
	}
	
	/**
	 * Stop gearbox.
	 */
	public void stop()
	{
	    currentGear = 0;
	}
	
	/**
	 * Get current gear.
     * @return the currentGear
     */
    public int getCurrentGear()
    {
        return currentGear;
    }

    /**
     * Set gear up.
     */
    public void gearUp() {
		if( currentGear < 5 ) {
			currentGear++;
		}
	}
	
    /**
     * Set gear down.
     */
	public void gearDown() {
		if( currentGear > 0 ) {
			currentGear--;
		}
	}
}
