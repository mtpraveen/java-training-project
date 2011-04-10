/**
 * 
 */
package trainings.transport;

/**
 * @author epam0006
 */

/**
 * Common interface for land transport
 */

public interface ITransport {
	/**
	 * Start the transport
	 * @return <code>true</code> if start was successful, in another case <code>false</code> 
	 */
	boolean start();
	
	/**
	 * Stop the transport
	 * @return <code>true</code> if start was successful, in another case <code>false</code> 
	 */
	boolean stop();
	
	/**
	 * Decrease transport's speed immediately 
	 */
	void brake();

	/**
	 * Increase transport's speed 
	 */
	void increaseSpeed();

	/**
	 * Decrease transport's speed
	 */
	void decreaseSpeed();
	
	/**
	 * Calculate passed distance
	 * @return distance in kilometers 
	 */
	double distance();
}
